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
package jp.ecuacion.app.qiitadataviewer.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

public class QiitaUser {

  private @Nullable String description;

  @JsonProperty("facebook_id")
  private @Nullable String facebookId;

  @JsonProperty("followees_count")
  private int followeesCount;

  @JsonProperty("followers_count")
  private int followersCount;

  @JsonProperty("github_login_name")
  private @Nullable String githubLoginName;

  private @Nullable String id;

  @JsonProperty("items_count")
  private int itemsCount;

  @JsonProperty("linkedin_id")
  private @Nullable String linkedinId;

  private @Nullable String location;

  private @Nullable String name;

  private @Nullable String organization;

  @JsonProperty("permanent_id")
  private int permanentId;

  @JsonProperty("profile_image_url")
  private @Nullable String profileImageUrl;

  @JsonProperty("team_only")
  private boolean teamOnly;

  @JsonProperty("twitter_screen_name")
  private @Nullable String twitterScreenName;

  @JsonProperty("website_url")
  private @Nullable String websiteUrl;

  public @Nullable String getDescription() {
    return description;
  }

  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  public @Nullable String getFacebookId() {
    return facebookId;
  }

  public void setFacebookId(@Nullable String facebookId) {
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

  public @Nullable String getGithubLoginName() {
    return githubLoginName;
  }

  public void setGithubLoginName(@Nullable String githubLoginName) {
    this.githubLoginName = githubLoginName;
  }

  public @Nullable String getId() {
    return id;
  }

  public void setId(@Nullable String id) {
    this.id = id;
  }

  public int getItemsCount() {
    return itemsCount;
  }

  public void setItemsCount(int itemsCount) {
    this.itemsCount = itemsCount;
  }

  public @Nullable String getLinkedinId() {
    return linkedinId;
  }

  public void setLinkedinId(@Nullable String linkedinId) {
    this.linkedinId = linkedinId;
  }

  public @Nullable String getLocation() {
    return location;
  }

  public void setLocation(@Nullable String location) {
    this.location = location;
  }

  public @Nullable String getName() {
    return name;
  }

  public void setName(@Nullable String name) {
    this.name = name;
  }

  public @Nullable String getOrganization() {
    return organization;
  }

  public void setOrganization(@Nullable String organization) {
    this.organization = organization;
  }

  public int getPermanentId() {
    return permanentId;
  }

  public void setPermanentId(int permanentId) {
    this.permanentId = permanentId;
  }

  public @Nullable String getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(@Nullable String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  public boolean isTeamOnly() {
    return teamOnly;
  }

  public void setTeamOnly(boolean teamOnly) {
    this.teamOnly = teamOnly;
  }

  public @Nullable String getTwitterScreenName() {
    return twitterScreenName;
  }

  public void setTwitterScreenName(@Nullable String twitterScreenName) {
    this.twitterScreenName = twitterScreenName;
  }

  public @Nullable String getWebsiteUrl() {
    return websiteUrl;
  }

  public void setWebsiteUrl(@Nullable String websiteUrl) {
    this.websiteUrl = websiteUrl;
  }
}
