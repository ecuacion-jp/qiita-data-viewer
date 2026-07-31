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
package jp.ecuacion.app.qiitadataviewer.web.record;

import jakarta.validation.constraints.Size;
import jp.ecuacion.app.qiitadataviewer.base.entity.AccAdmin;
import jp.ecuacion.app.qiitadataviewer.web.constant.Constants;
import jp.ecuacion.splib.core.container.DatetimeFormatParameters;
import jp.ecuacion.splib.web.item.HtmlItem;
import org.jspecify.annotations.Nullable;

/**
 * Used by the admin create/edit screen. {@code rawPassword} is a UI-only, transient field: it is
 * required (checked in the service, not here, since it's only required on insert) and hashed into
 * {@code acc.hashedPassword} on save. It is never populated when loading an existing record.
 */
public class AccAdminEditRecord extends AccAdminRecord {

  static final HtmlItem[] htmlItems =
      new HtmlItem[] {new HtmlItem("rawPassword").hideValue()};

  @Size(min = 8, max = 64)
  private @Nullable String rawPassword;

  public AccAdminEditRecord() {
    super();
  }

  public AccAdminEditRecord(AccAdmin e, DatetimeFormatParameters params) {
    super(e, params);
  }

  @Override
  public HtmlItem[] customizedItems() {
    return mergeHtmlItems(htmlItems,
        mergeHtmlItems(Constants.COMMON_HTML_ITEMS, Constants.ACC_COMMON_HTML_ITEMS));
  }

  public @Nullable String getRawPassword() {
    return rawPassword;
  }

  public void setRawPassword(@Nullable String rawPassword) {
    this.rawPassword = rawPassword;
  }
}
