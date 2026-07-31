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

import jp.ecuacion.app.qiitadataviewer.base.entity.AccGeneral;
import jp.ecuacion.app.qiitadataviewer.web.constant.Constants;
import jp.ecuacion.splib.core.container.DatetimeFormatParameters;
import jp.ecuacion.splib.web.item.HtmlItem;

/**
 * Used by the admin-only edit screen. Only {@code role} and {@code isValid} are actually
 * editable; every other field is rendered read-only in the template.
 */
public class AccGeneralEditRecord extends AccGeneralRecord {

  static final HtmlItem[] htmlItems = new HtmlItem[] {new HtmlItem("acc.role").notEmpty()};

  public AccGeneralEditRecord() {
    super();
  }

  public AccGeneralEditRecord(AccGeneral e, DatetimeFormatParameters params) {
    super(e, params);
  }

  @Override
  public HtmlItem[] customizedItems() {
    return mergeHtmlItems(htmlItems,
        mergeHtmlItems(Constants.COMMON_HTML_ITEMS, Constants.ACC_COMMON_HTML_ITEMS));
  }
}
