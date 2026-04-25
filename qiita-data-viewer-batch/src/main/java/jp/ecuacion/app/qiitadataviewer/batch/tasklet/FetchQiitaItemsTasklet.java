package jp.ecuacion.app.qiitadataviewer.batch.tasklet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import jp.ecuacion.app.qiitadataviewer.base.entity.Acc;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaGroup;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaItem;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaItemTag;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaItemTagVersion;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaTag;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaUser;
import jp.ecuacion.app.qiitadataviewer.batch.repository.AccRepository;
import jp.ecuacion.app.qiitadataviewer.batch.repository.QiitaGroupRepository;
import jp.ecuacion.app.qiitadataviewer.batch.repository.QiitaItemRepository;
import jp.ecuacion.app.qiitadataviewer.batch.repository.QiitaItemTagRepository;
import jp.ecuacion.app.qiitadataviewer.batch.repository.QiitaItemTagVersionRepository;
import jp.ecuacion.app.qiitadataviewer.batch.repository.QiitaTagRepository;
import jp.ecuacion.app.qiitadataviewer.batch.repository.QiitaUserRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Fetches Qiita items via API and stores them to DB. */
@Component
public class FetchQiitaItemsTasklet extends SystemCommonTasklet implements Tasklet {

  @Value("${qiita.access-token}")
  private String accessToken;

  @Value("${qiita.account.mail-address}")
  private String accountMailAddress;

  @Autowired
  private AccRepository accRepository;

  @Autowired
  private QiitaUserRepository qiitaUserRepository;

  @Autowired
  private QiitaGroupRepository qiitaGroupRepository;

  @Autowired
  private QiitaItemRepository qiitaItemRepository;

  @Autowired
  private QiitaTagRepository qiitaTagRepository;

  @Autowired
  private QiitaItemTagRepository qiitaItemTagRepository;

  @Autowired
  private QiitaItemTagVersionRepository qiitaItemTagVersionRepository;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {

    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    // Fetch all items of the authenticated user (paginated)
    final int perPage = 100;
    int page = 1;
    List<jp.ecuacion.app.qiitadataviewer.core.model.QiitaItem> allItems = new ArrayList<>();

    while (true) {
      String listUrl =
          "https://qiita.com/api/v2/authenticated_user/items?per_page=" + perPage + "&page=" + page;
      HttpResponse<String> listResponse =
          client.send(createRequest(listUrl), HttpResponse.BodyHandlers.ofString());

      if (listResponse.statusCode() != 200) {
        throw new IllegalStateException(
            "Failed to fetch item list (page=" + page + "): " + listResponse.statusCode());
      }

      List<jp.ecuacion.app.qiitadataviewer.core.model.QiitaItem> pageItems =
          mapper.readValue(listResponse.body(),
              new TypeReference<List<jp.ecuacion.app.qiitadataviewer.core.model.QiitaItem>>() {});
      allItems.addAll(pageItems);

      if (pageItems.size() < perPage) {
        break;
      }
      page++;
    }

    // Fetch detail for each item to get page_views_count
    List<jp.ecuacion.app.qiitadataviewer.core.model.QiitaItem> allItemsWithDetail =
        new ArrayList<>();
    for (jp.ecuacion.app.qiitadataviewer.core.model.QiitaItem item : allItems) {
      String detailUrl = "https://qiita.com/api/v2/items/" + item.getId();
      HttpResponse<String> detailResponse =
          client.send(createRequest(detailUrl), HttpResponse.BodyHandlers.ofString());

      if (detailResponse.statusCode() == 200) {
        @SuppressWarnings("null")
        jp.ecuacion.app.qiitadataviewer.core.model.QiitaItem detail = mapper.readValue(
            detailResponse.body(), jp.ecuacion.app.qiitadataviewer.core.model.QiitaItem.class);
        allItemsWithDetail.add(detail);
      }

      // Brief pause to avoid hitting API rate limits
      Thread.sleep(100);
    }

    // Load the Acc record for this batch
    Acc acc = accRepository.findByMailAddress(accountMailAddress).orElseThrow(
        () -> new IllegalStateException("Acc not found for mail address: " + accountMailAddress));

    // Save each item to DB
    for (jp.ecuacion.app.qiitadataviewer.core.model.QiitaItem apiItem : allItemsWithDetail) {
      saveItem(apiItem, acc);
    }

    return RepeatStatus.FINISHED;
  }

  private void saveItem(jp.ecuacion.app.qiitadataviewer.core.model.QiitaItem apiItem, Acc acc) {
    QiitaUser qiitaUser = upsertQiitaUser(apiItem.getUser(), acc);
    QiitaGroup qiitaGroup =
        apiItem.getGroup() != null ? upsertQiitaGroup(apiItem.getGroup(), acc) : null;
    QiitaItem qiitaItem = upsertQiitaItem(apiItem, acc, qiitaUser, qiitaGroup);

    if (apiItem.getTags() != null) {
      for (jp.ecuacion.app.qiitadataviewer.core.model.QiitaTag apiTag : apiItem.getTags()) {
        QiitaTag qiitaTag = upsertQiitaTag(apiTag, acc);
        QiitaItemTag qiitaItemTag = upsertQiitaItemTag(qiitaItem, qiitaTag, acc);
        upsertQiitaItemTagVersions(apiTag, qiitaItemTag, acc);
      }
    }
  }

  private QiitaUser upsertQiitaUser(jp.ecuacion.app.qiitadataviewer.core.model.QiitaUser src,
      Acc acc) {
    QiitaUser entity = qiitaUserRepository.findByUserIdInQiitaWebsite(src.getId()).orElse(new QiitaUser());
    entity.setAcc(acc);
    entity.setUserIdInQiitaWebsite(src.getId());
    entity.setPermanentId(src.getPermanentId());
    entity.setName(src.getName());
    entity.setDescription(src.getDescription());
    entity.setLocation(src.getLocation());
    entity.setOrganization(src.getOrganization());
    entity.setFolloweesCount(src.getFolloweesCount());
    entity.setFollowersCount(src.getFollowersCount());
    entity.setItemsCount(src.getItemsCount());
    entity.setProfileImageUrl(src.getProfileImageUrl());
    entity.setWebsiteUrl(src.getWebsiteUrl());
    entity.setTwitterScreenName(src.getTwitterScreenName());
    entity.setGithubLoginName(src.getGithubLoginName());
    entity.setFacebookId(src.getFacebookId());
    entity.setLinkedinId(src.getLinkedinId());
    entity.setIsTeamOnly(src.isTeamOnly());
    entity.setCreateAccId(-1L);
    entity.setLstUpdAccId(-1L);
    return qiitaUserRepository.save(entity);
  }

  private QiitaGroup upsertQiitaGroup(jp.ecuacion.app.qiitadataviewer.core.model.QiitaGroup src,
      Acc acc) {
    QiitaGroup entity =
        qiitaGroupRepository.findByUrlName(src.getUrlName()).orElse(new QiitaGroup());
    entity.setAcc(acc);
    entity.setUrlName(src.getUrlName());
    entity.setName(src.getName());
    entity.setDescription(src.getDescription());
    entity.setIsPrivate(src.isPrivate());
    entity.setCreatedAt(OffsetDateTime.parse(src.getCreatedAt()));
    entity.setUpdatedAt(OffsetDateTime.parse(src.getUpdatedAt()));
    entity.setCreateAccId(-1L);
    entity.setLstUpdAccId(-1L);
    return qiitaGroupRepository.save(entity);
  }

  private QiitaItem upsertQiitaItem(jp.ecuacion.app.qiitadataviewer.core.model.QiitaItem src,
      Acc acc, QiitaUser qiitaUser, QiitaGroup qiitaGroup) {
    QiitaItem entity = qiitaItemRepository.findByItemIdInQiitaWebsite(src.getId()).orElse(new QiitaItem());
    entity.setAcc(acc);
    entity.setItemIdInQiitaWebsite(src.getId());
    entity.setCoediting(src.isCoediting());
    entity.setCommentsCount(src.getCommentsCount());
    entity.setCreatedAt(OffsetDateTime.parse(src.getCreatedAt()));
    entity.setQiitaGroup(qiitaGroup);
    entity.setLikesCount(src.getLikesCount());
    entity.setIsPrivate(src.isPrivate());
    entity.setReactionsCount(src.getReactionsCount());
    entity.setStocksCount(src.getStocksCount());
    entity.setTitle(src.getTitle());
    entity.setUpdatedAt(OffsetDateTime.parse(src.getUpdatedAt()));
    entity.setUrl(src.getUrl());
    entity.setQiitaUser(qiitaUser);
    entity.setPageViewsCount(src.getPageViewsCount());
    entity.setTeamMembershipName(
        src.getTeamMembership() != null ? src.getTeamMembership().getName() : null);
    entity.setOrganizationUrlName(src.getOrganizationUrlName());
    entity.setIsSlide(src.isSlide());
    entity.setCreateAccId(-1L);
    entity.setLstUpdAccId(-1L);
    return qiitaItemRepository.save(entity);
  }

  private QiitaTag upsertQiitaTag(jp.ecuacion.app.qiitadataviewer.core.model.QiitaTag src,
      Acc acc) {
    QiitaTag entity = qiitaTagRepository.findByName(src.getName()).orElse(new QiitaTag());
    entity.setAcc(acc);
    entity.setName(src.getName());
    entity.setCreateAccId(-1L);
    entity.setLstUpdAccId(-1L);
    return qiitaTagRepository.save(entity);
  }

  private QiitaItemTag upsertQiitaItemTag(QiitaItem qiitaItem, QiitaTag qiitaTag, Acc acc) {
    QiitaItemTag entity =
        qiitaItemTagRepository.findByQiitaItem_IdAndQiitaTag_Id(qiitaItem.getId(), qiitaTag.getId())
            .orElse(new QiitaItemTag());
    entity.setAcc(acc);
    entity.setQiitaItem(qiitaItem);
    entity.setQiitaTag(qiitaTag);
    entity.setCreateAccId(-1L);
    entity.setLstUpdAccId(-1L);
    return qiitaItemTagRepository.save(entity);
  }

  private void upsertQiitaItemTagVersions(
      jp.ecuacion.app.qiitadataviewer.core.model.QiitaTag apiTag, QiitaItemTag qiitaItemTag,
      Acc acc) {
    if (apiTag.getVersions() == null || apiTag.getVersions().isEmpty()) {
      return;
    }
    List<QiitaItemTagVersion> existing =
        qiitaItemTagVersionRepository.findByQiitaItemTag_Id(qiitaItemTag.getId());
    List<String> existingNames =
        existing.stream().map(QiitaItemTagVersion::getVersionName).toList();
    for (String version : apiTag.getVersions()) {
      if (!existingNames.contains(version)) {
        QiitaItemTagVersion entity = new QiitaItemTagVersion();
        entity.setAcc(acc);
        entity.setQiitaItemTag(qiitaItemTag);
        entity.setVersionName(version);
        entity.setCreateAccId(-1L);
        entity.setLstUpdAccId(-1L);
        qiitaItemTagVersionRepository.save(entity);
      }
    }
  }

  private HttpRequest createRequest(String url) {
    return HttpRequest.newBuilder().uri(URI.create(url))
        .header("Authorization", "Bearer " + accessToken).GET().build();
  }
}
