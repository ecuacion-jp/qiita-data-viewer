package jp.ecuacion.app.qiitadataviewer.base.record;

import jakarta.validation.Valid;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaItem;
import jp.ecuacion.lib.core.annotation.ItemNameKeyClass;
import jp.ecuacion.lib.core.item.*;
import jp.ecuacion.lib.core.util.PropertiesFileUtil;
import jp.ecuacion.lib.core.util.StringUtil;
import jp.ecuacion.lib.validation.constraints.*;
import jp.ecuacion.splib.core.container.*;

@ItemNameKeyClass("qiitaItem")
public abstract class QiitaItemBaseRecord extends SystemCommonBaseRecord implements ItemContainer {

  @LongString
  protected String id;
  @Valid
  protected AccBaseRecord acc;
  @SizeString(min = 20, max = 20)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaItemId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  protected String itemIdInQiitaWebsite;
  protected Boolean coediting;
  @IntegerString
  protected String commentsCount;
  protected String createdAt;
  @Valid
  protected QiitaGroupBaseRecord qiitaGroup;
  @IntegerString
  protected String likesCount;
  protected Boolean isPrivate;
  @IntegerString
  protected String reactionsCount;
  @IntegerString
  protected String stocksCount;
  @SizeString(min = 1, max = 255)
  protected String title;
  protected String updatedAt;
  @SizeString(min = 1, max = 500)
  @PatternWithDescription(regexp = "^[^'$%&\\(\\)\\^~,<>\\?]*$", description = "longUrl")
  protected String url;
  @Valid
  protected QiitaUserBaseRecord qiitaUser;
  @IntegerString
  protected String pageViewsCount;
  @SizeString(min = 1, max = 255)
  protected String teamMembershipName;
  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaUserId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  protected String organizationUrlName;
  protected Boolean isSlide;

  static {
    getStringLengthMap().put("id", null);
    getStringLengthMap().put("accId", null);
    getStringLengthMap().put("itemIdInQiitaWebsite", 20);
    getStringLengthMap().put("commentsCount", null);
    getStringLengthMap().put("createdAt", null);
    getStringLengthMap().put("qiitaGroupId", null);
    getStringLengthMap().put("likesCount", null);
    getStringLengthMap().put("reactionsCount", null);
    getStringLengthMap().put("stocksCount", null);
    getStringLengthMap().put("title", 255);
    getStringLengthMap().put("updatedAt", null);
    getStringLengthMap().put("url", 500);
    getStringLengthMap().put("qiitaUserId", null);
    getStringLengthMap().put("pageViewsCount", null);
    getStringLengthMap().put("teamMembershipName", 255);
    getStringLengthMap().put("organizationUrlName", 100);
  }

  public QiitaItemBaseRecord() {
    this(3);
  }

  public QiitaItemBaseRecord(int count) {
    super();

    count--;

    if (count > 0) {
      acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
      qiitaGroup = new QiitaGroupBaseRecord(count) {public Item[] customizedItems() {return null;}};
      qiitaUser = new QiitaUserBaseRecord(count) {public Item[] customizedItems() {return null;}};
    }
  }

  public QiitaItemBaseRecord(QiitaItem e, DatetimeFormatParameters params) {
    this(e, params, 3);
  }

  public QiitaItemBaseRecord(QiitaItem e, DatetimeFormatParameters params, int count) {
    super(e, params);

    count--;

    this.id = (e.getId() == null) ? "" : Long.toString(e.getId());
    if (count > 0) {
      this.acc = new AccBaseRecord(e.getAcc(), params, count) {public Item[] customizedItems() {return null;}};
    }
    this.itemIdInQiitaWebsite = e.getItemIdInQiitaWebsite();
    this.coediting = e.getCoediting();
    this.commentsCount = (e.getCommentsCount() == null) ? "" : Integer.toString(e.getCommentsCount());
    this.createdAt = e.getCreatedAt() == null ? "" : e.getCreatedAt().withOffsetSameInstant(params.getZoneOffset()).format(DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
    if (count > 0) {
      this.qiitaGroup = new QiitaGroupBaseRecord(e.getQiitaGroup(), params, count) {public Item[] customizedItems() {return null;}};
    }
    this.likesCount = (e.getLikesCount() == null) ? "" : Integer.toString(e.getLikesCount());
    this.isPrivate = e.getIsPrivate();
    this.reactionsCount = (e.getReactionsCount() == null) ? "" : Integer.toString(e.getReactionsCount());
    this.stocksCount = (e.getStocksCount() == null) ? "" : Integer.toString(e.getStocksCount());
    this.title = e.getTitle();
    this.updatedAt = e.getUpdatedAt() == null ? "" : e.getUpdatedAt().withOffsetSameInstant(params.getZoneOffset()).format(DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
    this.url = e.getUrl();
    if (count > 0) {
      this.qiitaUser = new QiitaUserBaseRecord(e.getQiitaUser(), params, count) {public Item[] customizedItems() {return null;}};
    }
    this.pageViewsCount = (e.getPageViewsCount() == null) ? "" : Integer.toString(e.getPageViewsCount());
    this.teamMembershipName = e.getTeamMembershipName();
    this.organizationUrlName = e.getOrganizationUrlName();
    this.isSlide = e.getIsSlide();
  }

  public QiitaItemBaseRecord(QiitaItemBaseRecord rec) {
    this(rec, 3);
  }

  public QiitaItemBaseRecord(QiitaItemBaseRecord rec, int count) {
    super(rec);

    count--;

    this.id = rec.getId();
    this.acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
    this.setAccId(rec.getAccId());
    this.itemIdInQiitaWebsite = rec.getItemIdInQiitaWebsite();
    this.coediting = rec.getCoediting();
    this.commentsCount = rec.getCommentsCount();
    this.createdAt = rec.getCreatedAt();
    this.qiitaGroup = new QiitaGroupBaseRecord(count) {public Item[] customizedItems() {return null;}};
    this.setQiitaGroupId(rec.getQiitaGroupId());
    this.likesCount = rec.getLikesCount();
    this.isPrivate = rec.getIsPrivate();
    this.reactionsCount = rec.getReactionsCount();
    this.stocksCount = rec.getStocksCount();
    this.title = rec.getTitle();
    this.updatedAt = rec.getUpdatedAt();
    this.url = rec.getUrl();
    this.qiitaUser = new QiitaUserBaseRecord(count) {public Item[] customizedItems() {return null;}};
    this.setQiitaUserId(rec.getQiitaUserId());
    this.pageViewsCount = rec.getPageViewsCount();
    this.teamMembershipName = rec.getTeamMembershipName();
    this.organizationUrlName = rec.getOrganizationUrlName();
    this.isSlide = rec.getIsSlide();
  }

  // accessor:id
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getIdOfEntityDataType() {
    return (getId() == null || getId().equals("")) ? null : Long.valueOf(id.replaceAll(",", ""));
  }

  // accessor:accId
  public String getAccId() {
    return acc == null ? null : acc.getId();
  }

  public void setAccId(String accId) {
    this.acc.setId(accId);
  }

  public Long getAccIdOfEntityDataType() {
    return (getAccId() == null || getAccId().equals("")) ? null : getAcc().getIdOfEntityDataType();
  }

  public AccBaseRecord getAcc() {
    return acc;
  }

  public void setAcc(AccBaseRecord acc) {
    this.acc = acc;
  }

  // accessor:itemIdInQiitaWebsite
  public String getItemIdInQiitaWebsite() {
    return itemIdInQiitaWebsite;
  }

  public void setItemIdInQiitaWebsite(String itemIdInQiitaWebsite) {
    this.itemIdInQiitaWebsite = itemIdInQiitaWebsite;
  }

  // accessor:coediting
  public Boolean getCoediting() {
    return coediting;
  }

  public void setCoediting(Boolean coediting) {
    this.coediting = coediting;
  }

  // accessor:commentsCount
  public String getCommentsCount() {
    return commentsCount;
  }

  public void setCommentsCount(String commentsCount) {
    this.commentsCount = commentsCount;
  }

  public Integer getCommentsCountOfEntityDataType() {
    return (getCommentsCount() == null || getCommentsCount().equals("")) ? null : Integer.valueOf(commentsCount.replaceAll(",", ""));
  }

  // accessor:createdAt
  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public OffsetDateTime getCreatedAtOfEntityDataType() {
    return (getCreatedAt() == null || getCreatedAt().equals("")) ? null : OffsetDateTime.parse(createdAt, DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
  }

  // accessor:qiitaGroupId
  public String getQiitaGroupId() {
    return qiitaGroup == null ? null : qiitaGroup.getId();
  }

  public void setQiitaGroupId(String qiitaGroupId) {
    this.qiitaGroup.setId(qiitaGroupId);
  }

  public Long getQiitaGroupIdOfEntityDataType() {
    return (getQiitaGroupId() == null || getQiitaGroupId().equals("")) ? null : getQiitaGroup().getIdOfEntityDataType();
  }

  public QiitaGroupBaseRecord getQiitaGroup() {
    return qiitaGroup;
  }

  public void setQiitaGroup(QiitaGroupBaseRecord qiitaGroup) {
    this.qiitaGroup = qiitaGroup;
  }

  // accessor:likesCount
  public String getLikesCount() {
    return likesCount;
  }

  public void setLikesCount(String likesCount) {
    this.likesCount = likesCount;
  }

  public Integer getLikesCountOfEntityDataType() {
    return (getLikesCount() == null || getLikesCount().equals("")) ? null : Integer.valueOf(likesCount.replaceAll(",", ""));
  }

  // accessor:isPrivate
  public Boolean getIsPrivate() {
    return isPrivate;
  }

  public void setIsPrivate(Boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  // accessor:reactionsCount
  public String getReactionsCount() {
    return reactionsCount;
  }

  public void setReactionsCount(String reactionsCount) {
    this.reactionsCount = reactionsCount;
  }

  public Integer getReactionsCountOfEntityDataType() {
    return (getReactionsCount() == null || getReactionsCount().equals("")) ? null : Integer.valueOf(reactionsCount.replaceAll(",", ""));
  }

  // accessor:stocksCount
  public String getStocksCount() {
    return stocksCount;
  }

  public void setStocksCount(String stocksCount) {
    this.stocksCount = stocksCount;
  }

  public Integer getStocksCountOfEntityDataType() {
    return (getStocksCount() == null || getStocksCount().equals("")) ? null : Integer.valueOf(stocksCount.replaceAll(",", ""));
  }

  // accessor:title
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  // accessor:updatedAt
  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public OffsetDateTime getUpdatedAtOfEntityDataType() {
    return (getUpdatedAt() == null || getUpdatedAt().equals("")) ? null : OffsetDateTime.parse(updatedAt, DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
  }

  // accessor:url
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  // accessor:qiitaUserId
  public String getQiitaUserId() {
    return qiitaUser == null ? null : qiitaUser.getId();
  }

  public void setQiitaUserId(String qiitaUserId) {
    this.qiitaUser.setId(qiitaUserId);
  }

  public Long getQiitaUserIdOfEntityDataType() {
    return (getQiitaUserId() == null || getQiitaUserId().equals("")) ? null : getQiitaUser().getIdOfEntityDataType();
  }

  public QiitaUserBaseRecord getQiitaUser() {
    return qiitaUser;
  }

  public void setQiitaUser(QiitaUserBaseRecord qiitaUser) {
    this.qiitaUser = qiitaUser;
  }

  // accessor:pageViewsCount
  public String getPageViewsCount() {
    return pageViewsCount;
  }

  public void setPageViewsCount(String pageViewsCount) {
    this.pageViewsCount = pageViewsCount;
  }

  public Integer getPageViewsCountOfEntityDataType() {
    return (getPageViewsCount() == null || getPageViewsCount().equals("")) ? null : Integer.valueOf(pageViewsCount.replaceAll(",", ""));
  }

  // accessor:teamMembershipName
  public String getTeamMembershipName() {
    return teamMembershipName;
  }

  public void setTeamMembershipName(String teamMembershipName) {
    this.teamMembershipName = teamMembershipName;
  }

  // accessor:organizationUrlName
  public String getOrganizationUrlName() {
    return organizationUrlName;
  }

  public void setOrganizationUrlName(String organizationUrlName) {
    this.organizationUrlName = organizationUrlName;
  }

  // accessor:isSlide
  public Boolean getIsSlide() {
    return isSlide;
  }

  public void setIsSlide(Boolean isSlide) {
    this.isSlide = isSlide;
  }

  public List<String[]> getCoeditingList(Locale locale, String options) {
    return getBooleanDropdownList(locale, "coediting", options);
  }

  public String getCoeditingName(Locale locale) {
    return PropertiesFileUtil.getMessage(locale, "boolean.coediting." + coediting);
  }

  public List<String[]> getIsPrivateList(Locale locale, String options) {
    return getBooleanDropdownList(locale, "isPrivate", options);
  }

  public String getIsPrivateName(Locale locale) {
    return PropertiesFileUtil.getMessage(locale, "boolean.isPrivate." + isPrivate);
  }

  public List<String[]> getIsSlideList(Locale locale, String options) {
    return getBooleanDropdownList(locale, "isSlide", options);
  }

  public String getIsSlideName(Locale locale) {
    return PropertiesFileUtil.getMessage(locale, "boolean.isSlide." + isSlide);
  }

  public String getIds() {
    return StringUtil.getSeparatedValuesString(new String[] {getId() == null ? "" : getId(), getQiitaGroup() == null || getQiitaGroup().getId() == null? "" : getQiitaGroup().getId(), getQiitaUser() == null || getQiitaUser().getId() == null? "" : getQiitaUser().getId()}, "-");
  }

  public void setIds(String idCsv) {
    String[] ids = idCsv.split("-");
    if (ids.length < 3) return;

    setId(ids[0]);
    getQiitaGroup().setId(ids[1]);
    getQiitaUser().setId(ids[2]);
  }

  public String getOptimisticLockVersions() {
    return StringUtil.getSeparatedValuesString(new String[] {getVersion() == null ? "" : getVersion(), getQiitaGroup() == null || getQiitaGroup().getVersion() == null ? "" : getQiitaGroup().getVersion(), getQiitaUser() == null || getQiitaUser().getVersion() == null ? "" : getQiitaUser().getVersion()}, "-");
  }

  public void setOptimisticLockVersions(String versionCsv) {
    String[] versions = versionCsv.split("-");
    if (versions.length < 3) return;

    setVersion(versions[0]);
    getQiitaGroup().setVersion(versions[1]);
    getQiitaUser().setVersion(versions[2]);
  }
}
