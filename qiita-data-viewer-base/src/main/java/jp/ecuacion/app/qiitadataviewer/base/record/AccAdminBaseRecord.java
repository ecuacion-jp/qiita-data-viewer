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
import jp.ecuacion.app.qiitadataviewer.base.entity.AccAdmin;
import jp.ecuacion.lib.core.annotation.ItemNameKeyClass;
import jp.ecuacion.lib.core.item.*;
import jp.ecuacion.lib.core.util.StringUtil;
import jp.ecuacion.splib.core.container.*;

@ItemNameKeyClass("accAdmin")
public abstract class AccAdminBaseRecord extends SystemCommonBaseRecord implements ItemContainer {

  @Valid
  protected AccBaseRecord acc;

  static {
    getStringLengthMap().put("accId", null);
  }

  public AccAdminBaseRecord() {
    this(3);
  }

  public AccAdminBaseRecord(int count) {
    super();

    count--;

    if (count > 0) {
      acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
    }
  }

  public AccAdminBaseRecord(AccAdmin e, DatetimeFormatParameters params) {
    this(e, params, 3);
  }

  public AccAdminBaseRecord(AccAdmin e, DatetimeFormatParameters params, int count) {
    super(e, params);

    count--;

    if (count > 0 && e.getAcc() != null) {
      this.acc = new AccBaseRecord(e.getAcc(), params, count) {public Item[] customizedItems() {return null;}};
    }
    this.setIds(StringUtil.getSeparatedValuesString(new String[] {getAcc().getId() == null ? "" : getAcc().getId()}, ","));
    this.setOptimisticLockVersions(StringUtil.getSeparatedValuesString(new String[] {getVersion() == null ? "" : getVersion()}, ","));
  }

  public AccAdminBaseRecord(AccAdminBaseRecord rec) {
    this(rec, 3);
  }

  public AccAdminBaseRecord(AccAdminBaseRecord rec, int count) {
    super(rec);

    count--;

    this.acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
    this.setAccId(rec.getAccId());
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

  @Override
  public void setIds(String idCsv) {
    super.setIds(idCsv);
    String[] ids = idCsv.split(",", -1);
    if (ids.length < 1) return;

    getAcc().setId(ids[0]);
  }

  public String getVersionSnapshot() {
    return getSnapshotSegment(getOptimisticLockVersions(), 0);
  }

}
