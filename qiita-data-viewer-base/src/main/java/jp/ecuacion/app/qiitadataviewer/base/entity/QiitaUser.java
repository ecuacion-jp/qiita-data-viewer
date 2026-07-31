/*
 * Copyright © 2012 ecuacion.jp (info@ecuacion.jp)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.ecuacion.app.qiitadataviewer.base.entity;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.record.QiitaUserBaseRecord;
import jp.ecuacion.lib.validation.constraints.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jspecify.annotations.NonNull;

@Entity
@Table(name = "QIITA_USER", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_ID_IN_QIITA_WEBSITE"})})
@Filter(name = "groupFilter")
public final class QiitaUser extends SystemCommon implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID", nullable = false, columnDefinition = "bigserial")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QIITA_USER_ID_SEQ_GEN")
  @SequenceGenerator(name = "QIITA_USER_ID_SEQ_GEN", sequenceName = "QIITA_USER_ID_SEQ", allocationSize = 1)
  protected Long id;

  @NotNull
  @Valid
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "ACC_ID", referencedColumnName = "ID", nullable = false, columnDefinition = "bigint")
  private Acc acc = new Acc();

  @NotEmpty
  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaUserId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  @Column(name = "USER_ID_IN_QIITA_WEBSITE", nullable = false, length = 100)
  protected String userIdInQiitaWebsite;

  @NotNull
  @Column(name = "PERMANENT_ID", nullable = false)
  protected Integer permanentId;

  @SizeString(min = 1, max = 255)
  @Column(name = "NAME", nullable = true, length = 255)
  protected String name;

  @SizeString(min = 0, max = 65535)
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  @Column(name = "DESCRIPTION", nullable = true, length = 65535)
  protected String description;

  @SizeString(min = 1, max = 255)
  @Column(name = "LOCATION", nullable = true, length = 255)
  protected String location;

  @SizeString(min = 1, max = 255)
  @Column(name = "ORGANIZATION", nullable = true, length = 255)
  protected String organization;

  @NotNull
  @Column(name = "FOLLOWEES_COUNT", nullable = false)
  protected Integer followeesCount;

  @NotNull
  @Column(name = "FOLLOWERS_COUNT", nullable = false)
  protected Integer followersCount;

  @NotNull
  @Column(name = "ITEMS_COUNT", nullable = false)
  protected Integer itemsCount;

  @SizeString(min = 1, max = 500)
  @PatternWithDescription(regexp = "^[^'$%&\\\\(\\\\)\\\\^~,<>\\\\?]*$", description = "longUrl")
  @Column(name = "PROFILE_IMAGE_URL", nullable = true, length = 500)
  protected String profileImageUrl;

  @SizeString(min = 1, max = 500)
  @PatternWithDescription(regexp = "^[^'$%&\\\\(\\\\)\\\\^~,<>\\\\?]*$", description = "longUrl")
  @Column(name = "WEBSITE_URL", nullable = true, length = 500)
  protected String websiteUrl;

  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaUserId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  @Column(name = "TWITTER_SCREEN_NAME", nullable = true, length = 100)
  protected String twitterScreenName;

  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaUserId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  @Column(name = "GITHUB_LOGIN_NAME", nullable = true, length = 100)
  protected String githubLoginName;

  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaUserId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  @Column(name = "FACEBOOK_ID", nullable = true, length = 100)
  protected String facebookId;

  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaUserId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  @Column(name = "LINKEDIN_ID", nullable = true, length = 100)
  protected String linkedinId;

  @NotNull
  @Column(name = "IS_TEAM_ONLY", nullable = false)
  protected Boolean isTeamOnly;

  // ID
  public static final String FIELD_ID = "id";
  public static final String FIELD_ACC_ID = "accId";
  public static final String FIELD_USER_ID_IN_QIITA_WEBSITE = "userIdInQiitaWebsite";
  public static final String FIELD_PERMANENT_ID = "permanentId";
  public static final String FIELD_NAME = "name";
  public static final String FIELD_DESCRIPTION = "description";
  public static final String FIELD_LOCATION = "location";
  public static final String FIELD_ORGANIZATION = "organization";
  public static final String FIELD_FOLLOWEES_COUNT = "followeesCount";
  public static final String FIELD_FOLLOWERS_COUNT = "followersCount";
  public static final String FIELD_ITEMS_COUNT = "itemsCount";
  public static final String FIELD_PROFILE_IMAGE_URL = "profileImageUrl";
  public static final String FIELD_WEBSITE_URL = "websiteUrl";
  public static final String FIELD_TWITTER_SCREEN_NAME = "twitterScreenName";
  public static final String FIELD_GITHUB_LOGIN_NAME = "githubLoginName";
  public static final String FIELD_FACEBOOK_ID = "facebookId";
  public static final String FIELD_LINKEDIN_ID = "linkedinId";
  public static final String FIELD_IS_TEAM_ONLY = "isTeamOnly";

  @Override
  public String[] getFieldNameArr() {
    return new String[] {"id", "accId", "userIdInQiitaWebsite", "permanentId", "name", "description", "location", "organization", "followeesCount", "followersCount", "itemsCount", "profileImageUrl", "websiteUrl", "twitterScreenName", "githubLoginName", "facebookId", "linkedinId", "isTeamOnly", "createAccId", "createTime", "lstUpdAccId", "lstUpdTime", "delFlg", "version"};
  }

  /**Default constructor. */
  public QiitaUser() {}

  /** A constructor with record argument */
  public QiitaUser(QiitaUserBaseRecord rec, Acc acc) {
    super(rec);

    if (rec.getId() != null) setId(rec.getIdOfEntityDataType());
    if (acc != null) setAcc(acc);
    if (rec.getUserIdInQiitaWebsite() != null) setUserIdInQiitaWebsite(rec.getUserIdInQiitaWebsite());
    if (rec.getPermanentId() != null) setPermanentId(rec.getPermanentIdOfEntityDataType());
    if (rec.getName() != null) setName(rec.getName());
    if (rec.getDescription() != null) setDescription(rec.getDescription());
    if (rec.getLocation() != null) setLocation(rec.getLocation());
    if (rec.getOrganization() != null) setOrganization(rec.getOrganization());
    if (rec.getFolloweesCount() != null) setFolloweesCount(rec.getFolloweesCountOfEntityDataType());
    if (rec.getFollowersCount() != null) setFollowersCount(rec.getFollowersCountOfEntityDataType());
    if (rec.getItemsCount() != null) setItemsCount(rec.getItemsCountOfEntityDataType());
    if (rec.getProfileImageUrl() != null) setProfileImageUrl(rec.getProfileImageUrl());
    if (rec.getWebsiteUrl() != null) setWebsiteUrl(rec.getWebsiteUrl());
    if (rec.getTwitterScreenName() != null) setTwitterScreenName(rec.getTwitterScreenName());
    if (rec.getGithubLoginName() != null) setGithubLoginName(rec.getGithubLoginName());
    if (rec.getFacebookId() != null) setFacebookId(rec.getFacebookId());
    if (rec.getLinkedinId() != null) setLinkedinId(rec.getLinkedinId());
    if (rec.getIsTeamOnly() != null) setIsTeamOnly(rec.getIsTeamOnly());
  }

  /**
   * Constructor that takes naturalKey as arguments.
   * Having both a naturalKey and surrogateKey constructor could cause conflicts, so only the naturalKey constructor is provided.
   * (The surrogateKey is not used on insert; on select it is retrieved via Entity.getPk(field);
   * on update the selected entity is reused, so there are few scenarios where passing it as a constructor argument is preferred.) 
   */
  public QiitaUser(String userIdInQiitaWebsite) {
    this();
    setUserIdInQiitaWebsite(userIdInQiitaWebsite);
  }

  public void update(QiitaUserBaseRecord rec, Acc acc, String... skipUpdateFields) {
    List<String> skipUpdateFieldList = Arrays.asList(skipUpdateFields);

    if (rec.getId() != null && !skipUpdateFieldList.contains(FIELD_ID)) setId(rec.getIdOfEntityDataType());
    if (acc != null) setAcc(acc);
    if (rec.getUserIdInQiitaWebsite() != null && !skipUpdateFieldList.contains(FIELD_USER_ID_IN_QIITA_WEBSITE)) setUserIdInQiitaWebsite(rec.getUserIdInQiitaWebsite());
    if (rec.getPermanentId() != null && !skipUpdateFieldList.contains(FIELD_PERMANENT_ID)) setPermanentId(rec.getPermanentIdOfEntityDataType());
    if (rec.getName() != null && !skipUpdateFieldList.contains(FIELD_NAME)) setName(rec.getName());
    if (rec.getDescription() != null && !skipUpdateFieldList.contains(FIELD_DESCRIPTION)) setDescription(rec.getDescription());
    if (rec.getLocation() != null && !skipUpdateFieldList.contains(FIELD_LOCATION)) setLocation(rec.getLocation());
    if (rec.getOrganization() != null && !skipUpdateFieldList.contains(FIELD_ORGANIZATION)) setOrganization(rec.getOrganization());
    if (rec.getFolloweesCount() != null && !skipUpdateFieldList.contains(FIELD_FOLLOWEES_COUNT)) setFolloweesCount(rec.getFolloweesCountOfEntityDataType());
    if (rec.getFollowersCount() != null && !skipUpdateFieldList.contains(FIELD_FOLLOWERS_COUNT)) setFollowersCount(rec.getFollowersCountOfEntityDataType());
    if (rec.getItemsCount() != null && !skipUpdateFieldList.contains(FIELD_ITEMS_COUNT)) setItemsCount(rec.getItemsCountOfEntityDataType());
    if (rec.getProfileImageUrl() != null && !skipUpdateFieldList.contains(FIELD_PROFILE_IMAGE_URL)) setProfileImageUrl(rec.getProfileImageUrl());
    if (rec.getWebsiteUrl() != null && !skipUpdateFieldList.contains(FIELD_WEBSITE_URL)) setWebsiteUrl(rec.getWebsiteUrl());
    if (rec.getTwitterScreenName() != null && !skipUpdateFieldList.contains(FIELD_TWITTER_SCREEN_NAME)) setTwitterScreenName(rec.getTwitterScreenName());
    if (rec.getGithubLoginName() != null && !skipUpdateFieldList.contains(FIELD_GITHUB_LOGIN_NAME)) setGithubLoginName(rec.getGithubLoginName());
    if (rec.getFacebookId() != null && !skipUpdateFieldList.contains(FIELD_FACEBOOK_ID)) setFacebookId(rec.getFacebookId());
    if (rec.getLinkedinId() != null && !skipUpdateFieldList.contains(FIELD_LINKEDIN_ID)) setLinkedinId(rec.getLinkedinId());
    if (rec.getIsTeamOnly() != null && !skipUpdateFieldList.contains(FIELD_IS_TEAM_ONLY)) setIsTeamOnly(rec.getIsTeamOnly());
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

  public String getUserIdInQiitaWebsite() {
    return userIdInQiitaWebsite;
  }

  public void setUserIdInQiitaWebsite(String userIdInQiitaWebsite) {
    this.userIdInQiitaWebsite = userIdInQiitaWebsite;
  }

  public Integer getPermanentId() {
    return permanentId;
  }

  public void setPermanentId(Integer permanentId) {
    this.permanentId = permanentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public Integer getFolloweesCount() {
    return followeesCount;
  }

  public void setFolloweesCount(Integer followeesCount) {
    this.followeesCount = followeesCount;
  }

  public Integer getFollowersCount() {
    return followersCount;
  }

  public void setFollowersCount(Integer followersCount) {
    this.followersCount = followersCount;
  }

  public Integer getItemsCount() {
    return itemsCount;
  }

  public void setItemsCount(Integer itemsCount) {
    this.itemsCount = itemsCount;
  }

  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  public String getWebsiteUrl() {
    return websiteUrl;
  }

  public void setWebsiteUrl(String websiteUrl) {
    this.websiteUrl = websiteUrl;
  }

  public String getTwitterScreenName() {
    return twitterScreenName;
  }

  public void setTwitterScreenName(String twitterScreenName) {
    this.twitterScreenName = twitterScreenName;
  }

  public String getGithubLoginName() {
    return githubLoginName;
  }

  public void setGithubLoginName(String githubLoginName) {
    this.githubLoginName = githubLoginName;
  }

  public String getFacebookId() {
    return facebookId;
  }

  public void setFacebookId(String facebookId) {
    this.facebookId = facebookId;
  }

  public String getLinkedinId() {
    return linkedinId;
  }

  public void setLinkedinId(String linkedinId) {
    this.linkedinId = linkedinId;
  }

  public Boolean getIsTeamOnly() {
    return isTeamOnly;
  }

  public void setIsTeamOnly(Boolean isTeamOnly) {
    this.isTeamOnly = isTeamOnly;
  }

  // getNaturalKeyFieldList()
  public List<String> getNaturalKeyFieldList() {
    List<String> rtnList = new ArrayList<>();
    rtnList.add("userIdInQiitaWebsite");
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
