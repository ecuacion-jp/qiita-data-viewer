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

public class QiitaGroup {

  @JsonProperty("created_at")
  private @Nullable String createdAt;

  private @Nullable String description;

  private @Nullable String name;

  @JsonProperty("private")
  private boolean isPrivate;

  @JsonProperty("updated_at")
  private @Nullable String updatedAt;

  @JsonProperty("url_name")
  private @Nullable String urlName;

  public @Nullable String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(@Nullable String createdAt) {
    this.createdAt = createdAt;
  }

  public @Nullable String getDescription() {
    return description;
  }

  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  public @Nullable String getName() {
    return name;
  }

  public void setName(@Nullable String name) {
    this.name = name;
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  public void setPrivate(boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  public @Nullable String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(@Nullable String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public @Nullable String getUrlName() {
    return urlName;
  }

  public void setUrlName(@Nullable String urlName) {
    this.urlName = urlName;
  }
}
