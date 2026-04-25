package jp.ecuacion.app.qiitadataviewer.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QiitaGroup {

  @JsonProperty("created_at")
  private String createdAt;

  private String description;

  private String name;

  @JsonProperty("private")
  private boolean isPrivate;

  @JsonProperty("updated_at")
  private String updatedAt;

  @JsonProperty("url_name")
  private String urlName;

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  public void setPrivate(boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getUrlName() {
    return urlName;
  }

  public void setUrlName(String urlName) {
    this.urlName = urlName;
  }
}
