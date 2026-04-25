package jp.ecuacion.app.qiitadataviewer.base.record;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import jp.ecuacion.app.qiitadataviewer.base.entity.SystemCommon;
import jp.ecuacion.lib.core.util.PropertiesFileUtil;
import jp.ecuacion.lib.validation.constraints.*;
import jp.ecuacion.splib.core.container.*;
import jp.ecuacion.splib.core.record.SplibRecord;

public abstract class SystemCommonBaseRecord extends SplibRecord {

  @LongString
  protected String createAccId;
  protected String createAccName;
  protected String createTime;
  @LongString
  protected String lstUpdAccId;
  protected String lstUpdAccName;
  protected String lstUpdTime;
  protected Boolean delFlg;
  @LongString
  protected String version;

  static {
    getStringLengthMap().put("createAccId", null);
    getStringLengthMap().put("createAccName", 30);
    getStringLengthMap().put("createTime", null);
    getStringLengthMap().put("lstUpdAccId", null);
    getStringLengthMap().put("lstUpdAccName", 30);
    getStringLengthMap().put("lstUpdTime", null);
    getStringLengthMap().put("version", null);
  }

  public SystemCommonBaseRecord() {
    super();
  }

  public SystemCommonBaseRecord(SystemCommon e, DatetimeFormatParameters params) {
    super(params);
    this.createAccId = (e.getCreateAccId() == null) ? "" : Long.toString(e.getCreateAccId());
    this.createTime = e.getCreateTime() == null ? "" : e.getCreateTime().withOffsetSameInstant(params.getZoneOffset()).format(DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
    this.lstUpdAccId = (e.getLstUpdAccId() == null) ? "" : Long.toString(e.getLstUpdAccId());
    this.lstUpdTime = e.getLstUpdTime() == null ? "" : e.getLstUpdTime().withOffsetSameInstant(params.getZoneOffset()).format(DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
    this.delFlg = e.getDelFlg();
    this.version = (e.getVersion() == null) ? "" : Long.toString(e.getVersion());
  }

  public SystemCommonBaseRecord(SystemCommonBaseRecord rec) {
    super(rec.getDateTimeFormatParams());
  }

  // accessor:createAccId
  public String getCreateAccId() {
    return createAccId;
  }

  public void setCreateAccId(String createAccId) {
    this.createAccId = createAccId;
  }

  public Long getCreateAccIdOfEntityDataType() {
    return (getCreateAccId() == null || getCreateAccId().equals("")) ? null : Long.valueOf(createAccId.replaceAll(",", ""));
  }

  // accessor:createAccName
  public String getCreateAccName() {
    return createAccName;
  }

  public void setCreateAccName(String createAccName) {
    this.createAccName = createAccName;
  }

  // accessor:createTime
  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public OffsetDateTime getCreateTimeOfEntityDataType() {
    return (getCreateTime() == null || getCreateTime().equals("")) ? null : OffsetDateTime.parse(createTime, DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
  }

  // accessor:lstUpdAccId
  public String getLstUpdAccId() {
    return lstUpdAccId;
  }

  public void setLstUpdAccId(String lstUpdAccId) {
    this.lstUpdAccId = lstUpdAccId;
  }

  public Long getLstUpdAccIdOfEntityDataType() {
    return (getLstUpdAccId() == null || getLstUpdAccId().equals("")) ? null : Long.valueOf(lstUpdAccId.replaceAll(",", ""));
  }

  // accessor:lstUpdAccName
  public String getLstUpdAccName() {
    return lstUpdAccName;
  }

  public void setLstUpdAccName(String lstUpdAccName) {
    this.lstUpdAccName = lstUpdAccName;
  }

  // accessor:lstUpdTime
  public String getLstUpdTime() {
    return lstUpdTime;
  }

  public void setLstUpdTime(String lstUpdTime) {
    this.lstUpdTime = lstUpdTime;
  }

  public OffsetDateTime getLstUpdTimeOfEntityDataType() {
    return (getLstUpdTime() == null || getLstUpdTime().equals("")) ? null : OffsetDateTime.parse(lstUpdTime, DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
  }

  // accessor:delFlg
  public Boolean getDelFlg() {
    return delFlg;
  }

  public void setDelFlg(Boolean delFlg) {
    this.delFlg = delFlg;
  }

  // accessor:version
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Long getVersionOfEntityDataType() {
    return (getVersion() == null || getVersion().equals("")) ? null : Long.valueOf(version.replaceAll(",", ""));
  }

  public List<String[]> getDelFlgList(Locale locale, String options) {
    return getBooleanDropdownList(locale, "delFlg", options);
  }

  public String getDelFlgName(Locale locale) {
    return PropertiesFileUtil.getMessage(locale, "boolean.delFlg." + delFlg);
  }

}
