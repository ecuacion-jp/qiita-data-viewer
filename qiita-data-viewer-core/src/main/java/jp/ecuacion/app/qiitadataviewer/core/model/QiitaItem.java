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
import java.util.List;
import org.jspecify.annotations.Nullable;

public class QiitaItem {

  @JsonProperty("rendered_body")
  private @Nullable String renderedBody;

  private @Nullable String body;

  private boolean coediting;

  @JsonProperty("comments_count")
  private int commentsCount;

  @JsonProperty("created_at")
  private @Nullable String createdAt;

  private @Nullable QiitaGroup group;

  private @Nullable String id;

  @JsonProperty("likes_count")
  private int likesCount;

  @JsonProperty("private")
  private boolean isPrivate;

  @JsonProperty("reactions_count")
  private int reactionsCount;

  @JsonProperty("stocks_count")
  private int stocksCount;

  private @Nullable List<QiitaTag> tags;

  private @Nullable String title;

  @JsonProperty("updated_at")
  private @Nullable String updatedAt;

  private @Nullable String url;

  private @Nullable QiitaUser user;

  @JsonProperty("page_views_count")
  private @Nullable Integer pageViewsCount;

  @JsonProperty("team_membership")
  private @Nullable QiitaTeamMembership teamMembership;

  @JsonProperty("organization_url_name")
  private @Nullable String organizationUrlName;

  private boolean slide;

  public @Nullable String getRenderedBody() {
    return renderedBody;
  }

  public void setRenderedBody(@Nullable String renderedBody) {
    this.renderedBody = renderedBody;
  }

  public @Nullable String getBody() {
    return body;
  }

  public void setBody(@Nullable String body) {
    this.body = body;
  }

  public boolean isCoediting() {
    return coediting;
  }

  public void setCoediting(boolean coediting) {
    this.coediting = coediting;
  }

  public int getCommentsCount() {
    return commentsCount;
  }

  public void setCommentsCount(int commentsCount) {
    this.commentsCount = commentsCount;
  }

  public @Nullable String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(@Nullable String createdAt) {
    this.createdAt = createdAt;
  }

  public @Nullable QiitaGroup getGroup() {
    return group;
  }

  public void setGroup(@Nullable QiitaGroup group) {
    this.group = group;
  }

  public @Nullable String getId() {
    return id;
  }

  public void setId(@Nullable String id) {
    this.id = id;
  }

  public int getLikesCount() {
    return likesCount;
  }

  public void setLikesCount(int likesCount) {
    this.likesCount = likesCount;
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  public void setPrivate(boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  public int getReactionsCount() {
    return reactionsCount;
  }

  public void setReactionsCount(int reactionsCount) {
    this.reactionsCount = reactionsCount;
  }

  public int getStocksCount() {
    return stocksCount;
  }

  public void setStocksCount(int stocksCount) {
    this.stocksCount = stocksCount;
  }

  public @Nullable List<QiitaTag> getTags() {
    return tags;
  }

  public void setTags(@Nullable List<QiitaTag> tags) {
    this.tags = tags;
  }

  public @Nullable String getTitle() {
    return title;
  }

  public void setTitle(@Nullable String title) {
    this.title = title;
  }

  public @Nullable String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(@Nullable String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public @Nullable String getUrl() {
    return url;
  }

  public void setUrl(@Nullable String url) {
    this.url = url;
  }

  public @Nullable QiitaUser getUser() {
    return user;
  }

  public void setUser(@Nullable QiitaUser user) {
    this.user = user;
  }

  public @Nullable Integer getPageViewsCount() {
    return pageViewsCount;
  }

  public void setPageViewsCount(@Nullable Integer pageViewsCount) {
    this.pageViewsCount = pageViewsCount;
  }

  public @Nullable QiitaTeamMembership getTeamMembership() {
    return teamMembership;
  }

  public void setTeamMembership(@Nullable QiitaTeamMembership teamMembership) {
    this.teamMembership = teamMembership;
  }

  public @Nullable String getOrganizationUrlName() {
    return organizationUrlName;
  }

  public void setOrganizationUrlName(@Nullable String organizationUrlName) {
    this.organizationUrlName = organizationUrlName;
  }

  public boolean isSlide() {
    return slide;
  }

  public void setSlide(boolean slide) {
    this.slide = slide;
  }
}
