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
package jp.ecuacion.app.qiitadataviewer.web.constant;

import jp.ecuacion.splib.web.item.HtmlItem;

/** App wide constants. */
public class Constants {

  public static final HtmlItem[] COMMON_HTML_ITEMS = new HtmlItem[] {
      new HtmlItem("createAccId").itemNameKey("SystemCommon.createAccName"),
      new HtmlItem("lstUpdAccId").itemNameKey("SystemCommon.lstUpdAccName"),
      new HtmlItem("createTime").itemNameKey("SystemCommon.createTime"),
      new HtmlItem("lstUpdTime").itemNameKey("SystemCommon.lstUpdTime"),
      new HtmlItem("createAccName").itemNameKey("SystemCommon.createAccName"),
      new HtmlItem("lstUpdAccName").itemNameKey("SystemCommon.lstUpdAccName"),
      };

  // For records with an "acc" field (AccGeneral, AccAdmin).
  public static final HtmlItem[] ACC_COMMON_HTML_ITEMS = new HtmlItem[] {
      new HtmlItem("acc.createTime").itemNameKey("SystemCommon.accCreateTime"),
      new HtmlItem("acc.lstUpdTime").itemNameKey("SystemCommon.accLstUpdTime"),
      new HtmlItem("acc.createAccName").itemNameKey("SystemCommon.accCreateAccName"),
      new HtmlItem("acc.lstUpdAccName").itemNameKey("SystemCommon.accLstUpdAccName"),
      };

  private Constants() {}
}
