package jp.ecuacion.app.qiitadataviewer.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class QiitaItem {

  @JsonProperty("rendered_body")
  private String renderedBody;

  private String body;

  private boolean coediting;

  @JsonProperty("comments_count")
  private int commentsCount;

  @JsonProperty("created_at")
  private String createdAt;

  private QiitaGroup group;

  private String id;

  @JsonProperty("likes_count")
  private int likesCount;

  @JsonProperty("private")
  private boolean isPrivate;

  @JsonProperty("reactions_count")
  private int reactionsCount;

  @JsonProperty("stocks_count")
  private int stocksCount;

  private List<QiitaTag> tags;

  private String title;

  @JsonProperty("updated_at")
  private String updatedAt;

  private String url;

  private QiitaUser user;

  @JsonProperty("page_views_count")
  private Integer pageViewsCount;

  @JsonProperty("team_membership")
  private QiitaTeamMembership teamMembership;

  @JsonProperty("organization_url_name")
  private String organizationUrlName;

  private boolean slide;

  public String getRenderedBody() {
    return renderedBody;
  }

  public void setRenderedBody(String renderedBody) {
    this.renderedBody = renderedBody;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
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

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public QiitaGroup getGroup() {
    return group;
  }

  public void setGroup(QiitaGroup group) {
    this.group = group;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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

  public List<QiitaTag> getTags() {
    return tags;
  }

  public void setTags(List<QiitaTag> tags) {
    this.tags = tags;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public QiitaUser getUser() {
    return user;
  }

  public void setUser(QiitaUser user) {
    this.user = user;
  }

  public Integer getPageViewsCount() {
    return pageViewsCount;
  }

  public void setPageViewsCount(Integer pageViewsCount) {
    this.pageViewsCount = pageViewsCount;
  }

  public QiitaTeamMembership getTeamMembership() {
    return teamMembership;
  }

  public void setTeamMembership(QiitaTeamMembership teamMembership) {
    this.teamMembership = teamMembership;
  }

  public String getOrganizationUrlName() {
    return organizationUrlName;
  }

  public void setOrganizationUrlName(String organizationUrlName) {
    this.organizationUrlName = organizationUrlName;
  }

  public boolean isSlide() {
    return slide;
  }

  public void setSlide(boolean slide) {
    this.slide = slide;
  }
}
