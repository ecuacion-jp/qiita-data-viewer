package jp.ecuacion.app.qiitadataviewer.base.entity;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.*;
import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.record.QiitaItemBaseRecord;
import jp.ecuacion.lib.validation.constraints.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jspecify.annotations.NonNull;

@Entity
@Table(name = "QIITA_ITEM", uniqueConstraints = {@UniqueConstraint(columnNames = {"ITEM_ID_IN_QIITA_WEBSITE"})})
@Filter(name = "groupFilter")
public final class QiitaItem extends SystemCommon implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID", nullable = false, columnDefinition = "bigserial")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QIITA_ITEM_ID_SEQ_GEN")
  @SequenceGenerator(name = "QIITA_ITEM_ID_SEQ_GEN", sequenceName = "QIITA_ITEM_ID_SEQ", allocationSize = 1)
  protected Long id;

  @NotNull
  @Valid
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "ACC_ID", referencedColumnName = "ID", nullable = false, columnDefinition = "bigint")
  private Acc acc = new Acc();

  @NotEmpty
  @SizeString(min = 20, max = 20)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaItemId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  @Column(name = "ITEM_ID_IN_QIITA_WEBSITE", nullable = false, length = 20)
  protected String itemIdInQiitaWebsite;

  @NotNull
  @Column(name = "COEDITING", nullable = false)
  protected Boolean coediting;

  @NotNull
  @Column(name = "COMMENTS_COUNT", nullable = false)
  protected Integer commentsCount;

  @Column(name = "CREATED_AT", nullable = false, columnDefinition = "timestamp with time zone")
  protected OffsetDateTime createdAt;

  @Valid
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "QIITA_GROUP_ID", referencedColumnName = "ID", nullable = true, columnDefinition = "bigint")
  private QiitaGroup qiitaGroup = new QiitaGroup();

  @NotNull
  @Column(name = "LIKES_COUNT", nullable = false)
  protected Integer likesCount;

  @NotNull
  @Column(name = "IS_PRIVATE", nullable = false)
  protected Boolean isPrivate;

  @NotNull
  @Column(name = "REACTIONS_COUNT", nullable = false)
  protected Integer reactionsCount;

  @NotNull
  @Column(name = "STOCKS_COUNT", nullable = false)
  protected Integer stocksCount;

  @NotEmpty
  @SizeString(min = 1, max = 255)
  @Column(name = "TITLE", nullable = false, length = 255)
  protected String title;

  @Column(name = "UPDATED_AT", nullable = false, columnDefinition = "timestamp with time zone")
  protected OffsetDateTime updatedAt;

  @NotEmpty
  @SizeString(min = 1, max = 500)
  @PatternWithDescription(regexp = "^[^'$%&\\(\\)\\^~,<>\\?]*$", description = "longUrl")
  @Column(name = "URL", nullable = false, length = 500)
  protected String url;

  @NotNull
  @Valid
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "QIITA_USER_ID", referencedColumnName = "ID", nullable = false, columnDefinition = "bigint")
  private QiitaUser qiitaUser = new QiitaUser();

  @Column(name = "PAGE_VIEWS_COUNT", nullable = true)
  protected Integer pageViewsCount;

  @SizeString(min = 1, max = 255)
  @Column(name = "TEAM_MEMBERSHIP_NAME", nullable = true, length = 255)
  protected String teamMembershipName;

  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaUserId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  @Column(name = "ORGANIZATION_URL_NAME", nullable = true, length = 100)
  protected String organizationUrlName;

  @NotNull
  @Column(name = "IS_SLIDE", nullable = false)
  protected Boolean isSlide;

  // ID
  public static final String FIELD_ID = "id";
  public static final String FIELD_ACC_ID = "accId";
  public static final String FIELD_ITEM_ID_IN_QIITA_WEBSITE = "itemIdInQiitaWebsite";
  public static final String FIELD_COEDITING = "coediting";
  public static final String FIELD_COMMENTS_COUNT = "commentsCount";
  public static final String FIELD_CREATED_AT = "createdAt";
  public static final String FIELD_QIITA_GROUP_ID = "qiitaGroupId";
  public static final String FIELD_LIKES_COUNT = "likesCount";
  public static final String FIELD_IS_PRIVATE = "isPrivate";
  public static final String FIELD_REACTIONS_COUNT = "reactionsCount";
  public static final String FIELD_STOCKS_COUNT = "stocksCount";
  public static final String FIELD_TITLE = "title";
  public static final String FIELD_UPDATED_AT = "updatedAt";
  public static final String FIELD_URL = "url";
  public static final String FIELD_QIITA_USER_ID = "qiitaUserId";
  public static final String FIELD_PAGE_VIEWS_COUNT = "pageViewsCount";
  public static final String FIELD_TEAM_MEMBERSHIP_NAME = "teamMembershipName";
  public static final String FIELD_ORGANIZATION_URL_NAME = "organizationUrlName";
  public static final String FIELD_IS_SLIDE = "isSlide";

  @Override
  public String[] getFieldNameArr() {
    return new String[] {"id", "accId", "itemIdInQiitaWebsite", "coediting", "commentsCount", "createdAt", "qiitaGroupId", "likesCount", "isPrivate", "reactionsCount", "stocksCount", "title", "updatedAt", "url", "qiitaUserId", "pageViewsCount", "teamMembershipName", "organizationUrlName", "isSlide", "createAccId", "createTime", "lstUpdAccId", "lstUpdTime", "delFlg", "version"};
  }

  /**Default constructor. */
  public QiitaItem() {}

  /** A constructor with record argument */
  public QiitaItem(QiitaItemBaseRecord rec, OffsetDateTime createdAt, OffsetDateTime updatedAt, Acc acc, QiitaGroup qiitaGroup, QiitaUser qiitaUser) {
    super(rec);

    if (rec.getId() != null) setId(rec.getIdOfEntityDataType());
    if (acc != null) setAcc(acc);
    if (rec.getItemIdInQiitaWebsite() != null) setItemIdInQiitaWebsite(rec.getItemIdInQiitaWebsite());
    if (rec.getCoediting() != null) setCoediting(rec.getCoediting());
    if (rec.getCommentsCount() != null) setCommentsCount(rec.getCommentsCountOfEntityDataType());
    if (createdAt != null) setCreatedAt(createdAt);
    if (qiitaGroup != null) setQiitaGroup(qiitaGroup);
    if (rec.getLikesCount() != null) setLikesCount(rec.getLikesCountOfEntityDataType());
    if (rec.getIsPrivate() != null) setIsPrivate(rec.getIsPrivate());
    if (rec.getReactionsCount() != null) setReactionsCount(rec.getReactionsCountOfEntityDataType());
    if (rec.getStocksCount() != null) setStocksCount(rec.getStocksCountOfEntityDataType());
    if (rec.getTitle() != null) setTitle(rec.getTitle());
    if (updatedAt != null) setUpdatedAt(updatedAt);
    if (rec.getUrl() != null) setUrl(rec.getUrl());
    if (qiitaUser != null) setQiitaUser(qiitaUser);
    if (rec.getPageViewsCount() != null) setPageViewsCount(rec.getPageViewsCountOfEntityDataType());
    if (rec.getTeamMembershipName() != null) setTeamMembershipName(rec.getTeamMembershipName());
    if (rec.getOrganizationUrlName() != null) setOrganizationUrlName(rec.getOrganizationUrlName());
    if (rec.getIsSlide() != null) setIsSlide(rec.getIsSlide());
  }

  /**
   * Constructor that takes naturalKey as arguments.
   * Having both a naturalKey and surrogateKey constructor could cause conflicts, so only the naturalKey constructor is provided.
   * (The surrogateKey is not used on insert; on select it is retrieved via Entity.getPk(field);
   * on update the selected entity is reused, so there are few scenarios where passing it as a constructor argument is preferred.) 
   */
  public QiitaItem(String itemIdInQiitaWebsite) {
    this();
    setItemIdInQiitaWebsite(itemIdInQiitaWebsite);
  }

  public void update(QiitaItemBaseRecord rec, OffsetDateTime createdAt, OffsetDateTime updatedAt, Acc acc, QiitaGroup qiitaGroup, QiitaUser qiitaUser, String... skipUpdateFields) {
    List<String> skipUpdateFieldList = Arrays.asList(skipUpdateFields);

    if (rec.getId() != null && !skipUpdateFieldList.contains(FIELD_ID)) setId(rec.getIdOfEntityDataType());
    if (acc != null) setAcc(acc);
    if (rec.getItemIdInQiitaWebsite() != null && !skipUpdateFieldList.contains(FIELD_ITEM_ID_IN_QIITA_WEBSITE)) setItemIdInQiitaWebsite(rec.getItemIdInQiitaWebsite());
    if (rec.getCoediting() != null && !skipUpdateFieldList.contains(FIELD_COEDITING)) setCoediting(rec.getCoediting());
    if (rec.getCommentsCount() != null && !skipUpdateFieldList.contains(FIELD_COMMENTS_COUNT)) setCommentsCount(rec.getCommentsCountOfEntityDataType());
    if (createdAt != null && !skipUpdateFieldList.contains(FIELD_CREATED_AT)) setCreatedAt(createdAt);
    if (qiitaGroup != null) setQiitaGroup(qiitaGroup);
    if (rec.getLikesCount() != null && !skipUpdateFieldList.contains(FIELD_LIKES_COUNT)) setLikesCount(rec.getLikesCountOfEntityDataType());
    if (rec.getIsPrivate() != null && !skipUpdateFieldList.contains(FIELD_IS_PRIVATE)) setIsPrivate(rec.getIsPrivate());
    if (rec.getReactionsCount() != null && !skipUpdateFieldList.contains(FIELD_REACTIONS_COUNT)) setReactionsCount(rec.getReactionsCountOfEntityDataType());
    if (rec.getStocksCount() != null && !skipUpdateFieldList.contains(FIELD_STOCKS_COUNT)) setStocksCount(rec.getStocksCountOfEntityDataType());
    if (rec.getTitle() != null && !skipUpdateFieldList.contains(FIELD_TITLE)) setTitle(rec.getTitle());
    if (updatedAt != null && !skipUpdateFieldList.contains(FIELD_UPDATED_AT)) setUpdatedAt(updatedAt);
    if (rec.getUrl() != null && !skipUpdateFieldList.contains(FIELD_URL)) setUrl(rec.getUrl());
    if (qiitaUser != null) setQiitaUser(qiitaUser);
    if (rec.getPageViewsCount() != null && !skipUpdateFieldList.contains(FIELD_PAGE_VIEWS_COUNT)) setPageViewsCount(rec.getPageViewsCountOfEntityDataType());
    if (rec.getTeamMembershipName() != null && !skipUpdateFieldList.contains(FIELD_TEAM_MEMBERSHIP_NAME)) setTeamMembershipName(rec.getTeamMembershipName());
    if (rec.getOrganizationUrlName() != null && !skipUpdateFieldList.contains(FIELD_ORGANIZATION_URL_NAME)) setOrganizationUrlName(rec.getOrganizationUrlName());
    if (rec.getIsSlide() != null && !skipUpdateFieldList.contains(FIELD_IS_SLIDE)) setIsSlide(rec.getIsSlide());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getAccId() {
    return acc == null ? null : acc.getId();
  }

  public void setAccId(Long accId) {
    this.acc.setId(accId);
  }

  public Acc getAcc() {
    return acc;
  }

  public void setAcc(Acc acc) {
    this.acc = acc;
  }

  public String getItemIdInQiitaWebsite() {
    return itemIdInQiitaWebsite;
  }

  public void setItemIdInQiitaWebsite(String itemIdInQiitaWebsite) {
    this.itemIdInQiitaWebsite = itemIdInQiitaWebsite;
  }

  public Boolean getCoediting() {
    return coediting;
  }

  public void setCoediting(Boolean coediting) {
    this.coediting = coediting;
  }

  public Integer getCommentsCount() {
    return commentsCount;
  }

  public void setCommentsCount(Integer commentsCount) {
    this.commentsCount = commentsCount;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Long getQiitaGroupId() {
    return qiitaGroup == null ? null : qiitaGroup.getId();
  }

  public void setQiitaGroupId(Long qiitaGroupId) {
    this.qiitaGroup.setId(qiitaGroupId);
  }

  public QiitaGroup getQiitaGroup() {
    return qiitaGroup;
  }

  public void setQiitaGroup(QiitaGroup qiitaGroup) {
    this.qiitaGroup = qiitaGroup;
  }

  public Integer getLikesCount() {
    return likesCount;
  }

  public void setLikesCount(Integer likesCount) {
    this.likesCount = likesCount;
  }

  public Boolean getIsPrivate() {
    return isPrivate;
  }

  public void setIsPrivate(Boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  public Integer getReactionsCount() {
    return reactionsCount;
  }

  public void setReactionsCount(Integer reactionsCount) {
    this.reactionsCount = reactionsCount;
  }

  public Integer getStocksCount() {
    return stocksCount;
  }

  public void setStocksCount(Integer stocksCount) {
    this.stocksCount = stocksCount;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Long getQiitaUserId() {
    return qiitaUser == null ? null : qiitaUser.getId();
  }

  public void setQiitaUserId(Long qiitaUserId) {
    this.qiitaUser.setId(qiitaUserId);
  }

  public QiitaUser getQiitaUser() {
    return qiitaUser;
  }

  public void setQiitaUser(QiitaUser qiitaUser) {
    this.qiitaUser = qiitaUser;
  }

  public Integer getPageViewsCount() {
    return pageViewsCount;
  }

  public void setPageViewsCount(Integer pageViewsCount) {
    this.pageViewsCount = pageViewsCount;
  }

  public String getTeamMembershipName() {
    return teamMembershipName;
  }

  public void setTeamMembershipName(String teamMembershipName) {
    this.teamMembershipName = teamMembershipName;
  }

  public String getOrganizationUrlName() {
    return organizationUrlName;
  }

  public void setOrganizationUrlName(String organizationUrlName) {
    this.organizationUrlName = organizationUrlName;
  }

  public Boolean getIsSlide() {
    return isSlide;
  }

  public void setIsSlide(Boolean isSlide) {
    this.isSlide = isSlide;
  }

  // getNaturalKeyFieldList()
  public List<String> getNaturalKeyFieldList() {
    List<String> rtnList = new ArrayList<>();
    rtnList.add("itemIdInQiitaWebsite");
    return rtnList;
  }

  // getSetOfUniqueConstraintFieldList()
  // Currently only naturalKey is effectively supported, so it is added to the Set and returned.
  // In the future, other unique keys should also be configurable (otherwise auto-deletion of soft-deleted records on insert would not work).
  @NonNull
  public Set<List<String>> getSetOfUniqueConstraintFieldList() {
    Set<List<String>> rtnSet = new HashSet<>();
    List<String> list = getNaturalKeyFieldList();
    if (list != null) {
      rtnSet.add(list);
    }

    return rtnSet;
  }

}
