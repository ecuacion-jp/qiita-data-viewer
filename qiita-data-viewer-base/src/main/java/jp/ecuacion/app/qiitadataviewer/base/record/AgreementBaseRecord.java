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

  public String getIds() {
    return StringUtil.getSeparatedValuesString(new String[] {getId() == null ? "" : getId()}, "-");
  }

  public void setIds(String idCsv) {
    String[] ids = idCsv.split("-");
    if (ids.length < 1) return;

    setId(ids[0]);
  }

  public String getOptimisticLockVersions() {
    return StringUtil.getSeparatedValuesString(new String[] {getVersion() == null ? "" : getVersion()}, "-");
  }

  public void setOptimisticLockVersions(String versionCsv) {
    String[] versions = versionCsv.split("-");
    if (versions.length < 1) return;

    setVersion(versions[0]);
  }
}
