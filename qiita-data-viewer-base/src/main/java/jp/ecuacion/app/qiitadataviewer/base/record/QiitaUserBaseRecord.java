package jp.ecuacion.app.qiitadataviewer.base.record;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Locale;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaUser;
import jp.ecuacion.lib.core.annotation.ItemNameKeyClass;
import jp.ecuacion.lib.core.item.*;
import jp.ecuacion.lib.core.util.PropertiesFileUtil;
import jp.ecuacion.lib.core.util.StringUtil;
import jp.ecuacion.lib.validation.constraints.*;
import jp.ecuacion.splib.core.container.*;

@ItemNameKeyClass("qiitaUser")
public abstract class QiitaUserBaseRecord extends SystemCommonBaseRecord implements ItemContainer {

  @LongString
  protected String id;
  @Valid
  protected AccBaseRecord acc;
  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaUserId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  protected String userIdInQiitaWebsite;
  @IntegerString
  protected String permanentId;
  @SizeString(min = 1, max = 255)
  protected String name;
  @SizeString(min = 0, max = 65535)
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  protected String description;
  @SizeString(min = 1, max = 255)
  protected String location;
  @SizeString(min = 1, max = 255)
  protected String organization;
  @IntegerString
  protected String followeesCount;
  @IntegerString
  protected String followersCount;
  @IntegerString
  protected String itemsCount;
  @SizeString(min = 1, max = 500)
  @PatternWithDescription(regexp = "^[^'$%&\\(\\)\\^~,<>\\?]*$", description = "longUrl")
  protected String profileImageUrl;
  @SizeString(min = 1, max = 500)
  @PatternWithDescription(regexp = "^[^'$%&\\(\\)\\^~,<>\\?]*$", description = "longUrl")
  protected String websiteUrl;
  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaUserId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  protected String twitterScreenName;
  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaUserId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  protected String githubLoginName;
  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaUserId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  protected String facebookId;
  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaUserId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  protected String linkedinId;
  protected Boolean isTeamOnly;

  static {
    getStringLengthMap().put("id", null);
    getStringLengthMap().put("accId", null);
    getStringLengthMap().put("userIdInQiitaWebsite", 100);
    getStringLengthMap().put("permanentId", null);
    getStringLengthMap().put("name", 255);
    getStringLengthMap().put("description", 65535);
    getStringLengthMap().put("location", 255);
    getStringLengthMap().put("organization", 255);
    getStringLengthMap().put("followeesCount", null);
    getStringLengthMap().put("followersCount", null);
    getStringLengthMap().put("itemsCount", null);
    getStringLengthMap().put("profileImageUrl", 500);
    getStringLengthMap().put("websiteUrl", 500);
    getStringLengthMap().put("twitterScreenName", 100);
    getStringLengthMap().put("githubLoginName", 100);
    getStringLengthMap().put("facebookId", 100);
    getStringLengthMap().put("linkedinId", 100);
  }

  public QiitaUserBaseRecord() {
    this(3);
  }

  public QiitaUserBaseRecord(int count) {
    super();

    count--;

    if (count > 0) {
      acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
    }
  }

  public QiitaUserBaseRecord(QiitaUser e, DatetimeFormatParameters params) {
    this(e, params, 3);
  }

  public QiitaUserBaseRecord(QiitaUser e, DatetimeFormatParameters params, int count) {
    super(e, params);

    count--;

    this.id = (e.getId() == null) ? "" : Long.toString(e.getId());
    if (count > 0 && e.getAcc() != null) {
      this.acc = new AccBaseRecord(e.getAcc(), params, count) {public Item[] customizedItems() {return null;}};
    }
    this.userIdInQiitaWebsite = e.getUserIdInQiitaWebsite();
    this.permanentId = (e.getPermanentId() == null) ? "" : Integer.toString(e.getPermanentId());
    this.name = e.getName();
    this.description = e.getDescription();
    this.location = e.getLocation();
    this.organization = e.getOrganization();
    this.followeesCount = (e.getFolloweesCount() == null) ? "" : Integer.toString(e.getFolloweesCount());
    this.followersCount = (e.getFollowersCount() == null) ? "" : Integer.toString(e.getFollowersCount());
    this.itemsCount = (e.getItemsCount() == null) ? "" : Integer.toString(e.getItemsCount());
    this.profileImageUrl = e.getProfileImageUrl();
    this.websiteUrl = e.getWebsiteUrl();
    this.twitterScreenName = e.getTwitterScreenName();
    this.githubLoginName = e.getGithubLoginName();
    this.facebookId = e.getFacebookId();
    this.linkedinId = e.getLinkedinId();
    this.isTeamOnly = e.getIsTeamOnly();
    this.setIds(StringUtil.getSeparatedValuesString(new String[] {getId() == null ? "" : getId()}, ","));
    this.setOptimisticLockVersions(StringUtil.getSeparatedValuesString(new String[] {getVersion() == null ? "" : getVersion()}, ","));
  }

  public QiitaUserBaseRecord(QiitaUserBaseRecord rec) {
    this(rec, 3);
  }

  public QiitaUserBaseRecord(QiitaUserBaseRecord rec, int count) {
    super(rec);

    count--;

    this.id = rec.getId();
    this.acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
    this.setAccId(rec.getAccId());
    this.userIdInQiitaWebsite = rec.getUserIdInQiitaWebsite();
    this.permanentId = rec.getPermanentId();
    this.name = rec.getName();
    this.description = rec.getDescription();
    this.location = rec.getLocation();
    this.organization = rec.getOrganization();
    this.followeesCount = rec.getFolloweesCount();
    this.followersCount = rec.getFollowersCount();
    this.itemsCount = rec.getItemsCount();
    this.profileImageUrl = rec.getProfileImageUrl();
    this.websiteUrl = rec.getWebsiteUrl();
    this.twitterScreenName = rec.getTwitterScreenName();
    this.githubLoginName = rec.getGithubLoginName();
    this.facebookId = rec.getFacebookId();
    this.linkedinId = rec.getLinkedinId();
    this.isTeamOnly = rec.getIsTeamOnly();
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

  // accessor:userIdInQiitaWebsite
  public String getUserIdInQiitaWebsite() {
    return userIdInQiitaWebsite;
  }

  public void setUserIdInQiitaWebsite(String userIdInQiitaWebsite) {
    this.userIdInQiitaWebsite = userIdInQiitaWebsite;
  }

  // accessor:permanentId
  public String getPermanentId() {
    return permanentId;
  }

  public void setPermanentId(String permanentId) {
    this.permanentId = permanentId;
  }

  public Integer getPermanentIdOfEntityDataType() {
    return (getPermanentId() == null || getPermanentId().equals("")) ? null : Integer.valueOf(permanentId.replaceAll(",", ""));
  }

  // accessor:name
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // accessor:description
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  // accessor:location
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  // accessor:organization
  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  // accessor:followeesCount
  public String getFolloweesCount() {
    return followeesCount;
  }

  public void setFolloweesCount(String followeesCount) {
    this.followeesCount = followeesCount;
  }

  public Integer getFolloweesCountOfEntityDataType() {
    return (getFolloweesCount() == null || getFolloweesCount().equals("")) ? null : Integer.valueOf(followeesCount.replaceAll(",", ""));
  }

  // accessor:followersCount
  public String getFollowersCount() {
    return followersCount;
  }

  public void setFollowersCount(String followersCount) {
    this.followersCount = followersCount;
  }

  public Integer getFollowersCountOfEntityDataType() {
    return (getFollowersCount() == null || getFollowersCount().equals("")) ? null : Integer.valueOf(followersCount.replaceAll(",", ""));
  }

  // accessor:itemsCount
  public String getItemsCount() {
    return itemsCount;
  }

  public void setItemsCount(String itemsCount) {
    this.itemsCount = itemsCount;
  }

  public Integer getItemsCountOfEntityDataType() {
    return (getItemsCount() == null || getItemsCount().equals("")) ? null : Integer.valueOf(itemsCount.replaceAll(",", ""));
  }

  // accessor:profileImageUrl
  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  // accessor:websiteUrl
  public String getWebsiteUrl() {
    return websiteUrl;
  }

  public void setWebsiteUrl(String websiteUrl) {
    this.websiteUrl = websiteUrl;
  }

  // accessor:twitterScreenName
  public String getTwitterScreenName() {
    return twitterScreenName;
  }

  public void setTwitterScreenName(String twitterScreenName) {
    this.twitterScreenName = twitterScreenName;
  }

  // accessor:githubLoginName
  public String getGithubLoginName() {
    return githubLoginName;
  }

  public void setGithubLoginName(String githubLoginName) {
    this.githubLoginName = githubLoginName;
  }

  // accessor:facebookId
  public String getFacebookId() {
    return facebookId;
  }

  public void setFacebookId(String facebookId) {
    this.facebookId = facebookId;
  }

  // accessor:linkedinId
  public String getLinkedinId() {
    return linkedinId;
  }

  public void setLinkedinId(String linkedinId) {
    this.linkedinId = linkedinId;
  }

  // accessor:isTeamOnly
  public Boolean getIsTeamOnly() {
    return isTeamOnly;
  }

  public void setIsTeamOnly(Boolean isTeamOnly) {
    this.isTeamOnly = isTeamOnly;
  }

  public List<String[]> getIsTeamOnlyList(Locale locale, String options) {
    return getBooleanDropdownList(locale, "isTeamOnly", options);
  }

  public String getIsTeamOnlyName(Locale locale) {
    return PropertiesFileUtil.getMessage(locale, "boolean.isTeamOnly." + isTeamOnly);
  }

  @Override
  public void setIds(String idCsv) {
    super.setIds(idCsv);
    String[] ids = idCsv.split(",", -1);
    if (ids.length < 1) return;

    setId(ids[0]);
  }

  @Override
  public void setOptimisticLockVersions(String verCsv) {
    super.setOptimisticLockVersions(verCsv);
    String[] vers = verCsv.split(",", -1);
    if (vers.length < 1) return;

    setVersion(vers[0]);
  }

}
