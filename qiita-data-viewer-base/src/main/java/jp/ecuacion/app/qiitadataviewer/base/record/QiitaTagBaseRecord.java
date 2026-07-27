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
package jp.ecuacion.app.qiitadataviewer.base.record;

import jakarta.validation.Valid;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaTag;
import jp.ecuacion.lib.core.annotation.ItemNameKeyClass;
import jp.ecuacion.lib.core.item.*;
import jp.ecuacion.lib.core.util.StringUtil;
import jp.ecuacion.lib.validation.constraints.*;
import jp.ecuacion.splib.core.container.*;

@ItemNameKeyClass("qiitaTag")
public abstract class QiitaTagBaseRecord extends SystemCommonBaseRecord implements ItemContainer {

  @LongString
  protected String id;
  @Valid
  protected AccBaseRecord acc;
  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[^!\\\"#\\\\$%&\\\\(\\\\)=\\\\^~\\\\\\\\\\\\|`\\\\[\\\\{;\\\\+:\\\\\\\\*\\\\]\\\\},<>/\\\\?]*$", description = "prohibitedChars")
  protected String name;

  static {
    getStringLengthMap().put("id", null);
    getStringLengthMap().put("accId", null);
    getStringLengthMap().put("name", 100);
  }

  public QiitaTagBaseRecord() {
    this(3);
  }

  public QiitaTagBaseRecord(int count) {
    super();

    count--;

    if (count > 0) {
      acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
    }
  }

  public QiitaTagBaseRecord(QiitaTag e, DatetimeFormatParameters params) {
    this(e, params, 3);
  }

  public QiitaTagBaseRecord(QiitaTag e, DatetimeFormatParameters params, int count) {
    super(e, params);

    count--;

    this.id = (e.getId() == null) ? "" : Long.toString(e.getId());
    if (count > 0 && e.getAcc() != null) {
      this.acc = new AccBaseRecord(e.getAcc(), params, count) {public Item[] customizedItems() {return null;}};
    }
    this.name = e.getName();
    this.setIds(StringUtil.getSeparatedValuesString(new String[] {getId() == null ? "" : getId()}, ","));
    this.setOptimisticLockVersions(StringUtil.getSeparatedValuesString(new String[] {getVersion() == null ? "" : getVersion()}, ","));
  }

  public QiitaTagBaseRecord(QiitaTagBaseRecord rec) {
    this(rec, 3);
  }

  public QiitaTagBaseRecord(QiitaTagBaseRecord rec, int count) {
    super(rec);

    count--;

    this.id = rec.getId();
    this.acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
    this.setAccId(rec.getAccId());
    this.name = rec.getName();
  }

  // accessor:id
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getIdOfEntityDataType() {
    return (getId() == null || getId().equals("")) ? null : Long.valueOf(id.replaceAll(",", ""));
  }

  // accessor:accId
  public String getAccId() {
    return acc == null ? null : acc.getId();
  }

  public void setAccId(String accId) {
    this.acc.setId(accId);
  }

  public Long getAccIdOfEntityDataType() {
    return (getAccId() == null || getAccId().equals("")) ? null : getAcc().getIdOfEntityDataType();
  }

  public AccBaseRecord getAcc() {
    return acc;
  }

  public void setAcc(AccBaseRecord acc) {
    this.acc = acc;
  }

  // accessor:name
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setIds(String idCsv) {
    super.setIds(idCsv);
    String[] ids = idCsv.split(",", -1);
    if (ids.length < 1) return;

    setId(ids[0]);
  }

  public String getVersionSnapshot() {
    return getSnapshotSegment(getOptimisticLockVersions(), 0);
  }

}
