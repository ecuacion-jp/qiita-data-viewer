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
import jp.ecuacion.splib.web.item.HtmlItemContainer;
import jp.ecuacion.splib.web.item.HtmlItemSelect;
import jp.ecuacion.splib.web.item.HtmlItemString;

public class AccGeneralSearchRecord extends AccGeneralRecord implements HtmlItemContainer {

  static final HtmlItem[] htmlItems =
      new HtmlItem[] {new HtmlItemString("acc.mailAddress"), new HtmlItemString("acc.name"),
          new HtmlItem("acc.role").notEmpty(),
          new HtmlItemSelect("acc.isValid").itemNameKey("acc.isValidOrNot")};

  public AccGeneralSearchRecord() {
    super();
  }

  public AccGeneralSearchRecord(AccGeneral e, DatetimeFormatParameters params) {
    super(e, params);
  }

  @Override
  public HtmlItem[] customizedItems() {
    return mergeHtmlItems(htmlItems,
        mergeHtmlItems(Constants.COMMON_HTML_ITEMS, Constants.ACC_COMMON_HTML_ITEMS));
  }
}
