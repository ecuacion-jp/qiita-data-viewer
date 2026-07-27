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

import jp.ecuacion.app.qiitadataviewer.base.entity.AccAgreement;
import jp.ecuacion.lib.core.annotation.ItemNameKeyClass;
import jp.ecuacion.lib.core.item.*;
import jp.ecuacion.lib.core.util.StringUtil;
import jp.ecuacion.lib.validation.constraints.*;
import jp.ecuacion.splib.core.container.*;

@ItemNameKeyClass("accAgreement")
public abstract class AccAgreementBaseRecord extends SystemCommonBaseRecord implements ItemContainer {

  @LongString
  protected String id;
  @LongString
  protected String accId;
  @LongString
  protected String agreementId;

  static {
    getStringLengthMap().put("id", null);
    getStringLengthMap().put("accId", null);
    getStringLengthMap().put("agreementId", null);
  }

  public AccAgreementBaseRecord() {
    super();
  }

  public AccAgreementBaseRecord(AccAgreement e, DatetimeFormatParameters params) {
    super(e, params);
    this.id = (e.getId() == null) ? "" : Long.toString(e.getId());
    this.accId = (e.getAccId() == null) ? "" : Long.toString(e.getAccId());
    this.agreementId = (e.getAgreementId() == null) ? "" : Long.toString(e.getAgreementId());
    this.setIds(StringUtil.getSeparatedValuesString(new String[] {getId() == null ? "" : getId()}, ","));
    this.setOptimisticLockVersions(StringUtil.getSeparatedValuesString(new String[] {getVersion() == null ? "" : getVersion()}, ","));
  }

  public AccAgreementBaseRecord(AccAgreementBaseRecord rec) {
    super(rec);
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
    return accId;
  }

  public void setAccId(String accId) {
    this.accId = accId;
  }

  public Long getAccIdOfEntityDataType() {
    return (getAccId() == null || getAccId().equals("")) ? null : Long.valueOf(accId.replaceAll(",", ""));
  }

  // accessor:agreementId
  public String getAgreementId() {
    return agreementId;
  }

  public void setAgreementId(String agreementId) {
    this.agreementId = agreementId;
  }

  public Long getAgreementIdOfEntityDataType() {
    return (getAgreementId() == null || getAgreementId().equals("")) ? null : Long.valueOf(agreementId.replaceAll(",", ""));
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
