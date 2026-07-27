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
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaItemTag;
import jp.ecuacion.lib.core.annotation.ItemNameKeyClass;
import jp.ecuacion.lib.core.item.*;
import jp.ecuacion.lib.core.util.StringUtil;
import jp.ecuacion.lib.validation.constraints.*;
import jp.ecuacion.splib.core.container.*;

@ItemNameKeyClass("qiitaItemTag")
public abstract class QiitaItemTagBaseRecord extends SystemCommonBaseRecord implements ItemContainer {

  @LongString
  protected String id;
  @Valid
  protected AccBaseRecord acc;
  @Valid
  protected QiitaItemBaseRecord qiitaItem;
  @Valid
  protected QiitaTagBaseRecord qiitaTag;

  static {
    getStringLengthMap().put("id", null);
    getStringLengthMap().put("accId", null);
    getStringLengthMap().put("qiitaItemId", null);
    getStringLengthMap().put("qiitaTagId", null);
  }

  public QiitaItemTagBaseRecord() {
    this(3);
  }

  public QiitaItemTagBaseRecord(int count) {
    super();

    count--;

    if (count > 0) {
      acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
      qiitaItem = new QiitaItemBaseRecord(count) {public Item[] customizedItems() {return null;}};
      qiitaTag = new QiitaTagBaseRecord(count) {public Item[] customizedItems() {return null;}};
    }
  }

  public QiitaItemTagBaseRecord(QiitaItemTag e, DatetimeFormatParameters params) {
    this(e, params, 3);
  }

  public QiitaItemTagBaseRecord(QiitaItemTag e, DatetimeFormatParameters params, int count) {
    super(e, params);

    count--;

    this.id = (e.getId() == null) ? "" : Long.toString(e.getId());
    if (count > 0 && e.getAcc() != null) {
      this.acc = new AccBaseRecord(e.getAcc(), params, count) {public Item[] customizedItems() {return null;}};
    }
    if (count > 0 && e.getQiitaItem() != null) {
      this.qiitaItem = new QiitaItemBaseRecord(e.getQiitaItem(), params, count) {public Item[] customizedItems() {return null;}};
    }
    if (count > 0 && e.getQiitaTag() != null) {
      this.qiitaTag = new QiitaTagBaseRecord(e.getQiitaTag(), params, count) {public Item[] customizedItems() {return null;}};
    }
    this.setIds(StringUtil.getSeparatedValuesString(new String[] {getId() == null ? "" : getId(), getQiitaItem() == null || getQiitaItem().getId() == null ? "" : getQiitaItem().getId(), getQiitaTag() == null || getQiitaTag().getId() == null ? "" : getQiitaTag().getId()}, ","));
    this.setOptimisticLockVersions(StringUtil.getSeparatedValuesString(new String[] {getVersion() == null ? "" : getVersion(), getQiitaItem() == null || getQiitaItem().getVersion() == null ? "" : getQiitaItem().getVersion(), getQiitaTag() == null || getQiitaTag().getVersion() == null ? "" : getQiitaTag().getVersion()}, ","));
  }

  public QiitaItemTagBaseRecord(QiitaItemTagBaseRecord rec) {
    this(rec, 3);
  }

  public QiitaItemTagBaseRecord(QiitaItemTagBaseRecord rec, int count) {
    super(rec);

    count--;

    this.id = rec.getId();
    this.acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
    this.setAccId(rec.getAccId());
    this.qiitaItem = new QiitaItemBaseRecord(count) {public Item[] customizedItems() {return null;}};
    this.setQiitaItemId(rec.getQiitaItemId());
    this.qiitaTag = new QiitaTagBaseRecord(count) {public Item[] customizedItems() {return null;}};
    this.setQiitaTagId(rec.getQiitaTagId());
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

  // accessor:qiitaItemId
  public String getQiitaItemId() {
    return qiitaItem == null ? null : qiitaItem.getId();
  }

  public void setQiitaItemId(String qiitaItemId) {
    this.qiitaItem.setId(qiitaItemId);
  }

  public Long getQiitaItemIdOfEntityDataType() {
    return (getQiitaItemId() == null || getQiitaItemId().equals("")) ? null : getQiitaItem().getIdOfEntityDataType();
  }

  public QiitaItemBaseRecord getQiitaItem() {
    return qiitaItem;
  }

  public void setQiitaItem(QiitaItemBaseRecord qiitaItem) {
    this.qiitaItem = qiitaItem;
  }

  // accessor:qiitaTagId
  public String getQiitaTagId() {
    return qiitaTag == null ? null : qiitaTag.getId();
  }

  public void setQiitaTagId(String qiitaTagId) {
    this.qiitaTag.setId(qiitaTagId);
  }

  public Long getQiitaTagIdOfEntityDataType() {
    return (getQiitaTagId() == null || getQiitaTagId().equals("")) ? null : getQiitaTag().getIdOfEntityDataType();
  }

  public QiitaTagBaseRecord getQiitaTag() {
    return qiitaTag;
  }

  public void setQiitaTag(QiitaTagBaseRecord qiitaTag) {
    this.qiitaTag = qiitaTag;
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

  public String getQiitaItemIdSnapshot() {
    return getSnapshotSegment(getIds(), 1);
  }

  public String getQiitaItemVersionSnapshot() {
    return getSnapshotSegment(getOptimisticLockVersions(), 1);
  }

  public String getQiitaTagIdSnapshot() {
    return getSnapshotSegment(getIds(), 2);
  }

  public String getQiitaTagVersionSnapshot() {
    return getSnapshotSegment(getOptimisticLockVersions(), 2);
  }

}
