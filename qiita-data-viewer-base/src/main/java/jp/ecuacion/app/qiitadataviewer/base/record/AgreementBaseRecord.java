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

import java.time.*;
import java.time.format.DateTimeFormatter;
import jp.ecuacion.app.qiitadataviewer.base.entity.Agreement;
import jp.ecuacion.lib.core.annotation.ItemNameKeyClass;
import jp.ecuacion.lib.core.item.*;
import jp.ecuacion.lib.core.util.StringUtil;
import jp.ecuacion.lib.validation.constraints.*;
import jp.ecuacion.splib.core.container.*;

@ItemNameKeyClass("agreement")
public abstract class AgreementBaseRecord extends SystemCommonBaseRecord implements ItemContainer {

  @LongString
  protected String id;
  protected String uploadedDatetime;

  static {
    getStringLengthMap().put("id", null);
    getStringLengthMap().put("uploadedDatetime", null);
  }

  public AgreementBaseRecord() {
    super();
  }

  public AgreementBaseRecord(Agreement e, DatetimeFormatParameters params) {
    super(e, params);
    this.id = (e.getId() == null) ? "" : Long.toString(e.getId());
    this.uploadedDatetime = e.getUploadedDatetime() == null ? "" : e.getUploadedDatetime().withOffsetSameInstant(params.getZoneOffset()).format(DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
    this.setIds(StringUtil.getSeparatedValuesString(new String[] {getId() == null ? "" : getId()}, ","));
    this.setOptimisticLockVersions(StringUtil.getSeparatedValuesString(new String[] {getVersion() == null ? "" : getVersion()}, ","));
  }

  public AgreementBaseRecord(AgreementBaseRecord rec) {
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

  // accessor:uploadedDatetime
  public String getUploadedDatetime() {
    return uploadedDatetime;
  }

  public void setUploadedDatetime(String uploadedDatetime) {
    this.uploadedDatetime = uploadedDatetime;
  }

  public OffsetDateTime getUploadedDatetimeOfEntityDataType() {
    return (getUploadedDatetime() == null || getUploadedDatetime().equals("")) ? null : OffsetDateTime.parse(uploadedDatetime, DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
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
