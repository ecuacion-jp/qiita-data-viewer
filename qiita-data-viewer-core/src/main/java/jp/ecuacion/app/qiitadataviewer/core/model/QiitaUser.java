package jp.ecuacion.app.qiitadataviewer.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QiitaUser {

  private String description;

  @JsonProperty("facebook_id")
  private String facebookId;

  @JsonProperty("followees_count")
  private int followeesCount;

  @JsonProperty("followers_count")
  private int followersCount;

  @JsonProperty("github_login_name")
  private String githubLoginName;

  private String id;

  @JsonProperty("items_count")
  private int itemsCount;

  @JsonProperty("linkedin_id")
  private String linkedinId;

  private String location;

  private String name;

  private String organization;

  @JsonProperty("permanent_id")
  private int permanentId;

  @JsonProperty("profile_image_url")
  private String profileImageUrl;

  @JsonProperty("team_only")
  private boolean teamOnly;

  @JsonProperty("twitter_screen_name")
  private String twitterScreenName;

  @JsonProperty("website_url")
  private String websiteUrl;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFacebookId() {
    return facebookId;
  }

  public void setFacebookId(String facebookId) {
    this.facebookId = facebookId;
  }

  public int getFolloweesCount() {
    return followeesCount;
  }

  public void setFolloweesCount(int followeesCount) {
    this.followeesCount = followeesCount;
  }

  public int getFollowersCount() {
    return followersCount;
  }

  public void setFollowersCount(int followersCount) {
    this.followersCount = followersCount;
  }

  public String getGithubLoginName() {
    return githubLoginName;
  }

  public void setGithubLoginName(String githubLoginName) {
    this.githubLoginName = githubLoginName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getItemsCount() {
    return itemsCount;
  }

  public void setItemsCount(int itemsCount) {
    this.itemsCount = itemsCount;
  }

  public String getLinkedinId() {
    return linkedinId;
  }

  public void setLinkedinId(String linkedinId) {
    this.linkedinId = linkedinId;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public int getPermanentId() {
    return permanentId;
  }

  public void setPermanentId(int permanentId) {
    this.permanentId = permanentId;
  }

  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  public boolean isTeamOnly() {
    return teamOnly;
  }

  public void setTeamOnly(boolean teamOnly) {
    this.teamOnly = teamOnly;
  }

  public String getTwitterScreenName() {
    return twitterScreenName;
  }

  public void setTwitterScreenName(String twitterScreenName) {
    this.twitterScreenName = twitterScreenName;
  }

  public String getWebsiteUrl() {
    return websiteUrl;
  }

  public void setWebsiteUrl(String websiteUrl) {
    this.websiteUrl = websiteUrl;
  }
}
