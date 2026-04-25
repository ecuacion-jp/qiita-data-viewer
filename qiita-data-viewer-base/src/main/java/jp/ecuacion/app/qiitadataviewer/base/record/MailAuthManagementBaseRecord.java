package jp.ecuacion.app.qiitadataviewer.base.record;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import jp.ecuacion.app.qiitadataviewer.base.entity.MailAuthManagement;
import jp.ecuacion.app.qiitadataviewer.base.enums.MailAuthKindEnum;
import jp.ecuacion.lib.core.annotation.ItemNameKeyClass;
import jp.ecuacion.lib.core.item.*;
import jp.ecuacion.lib.core.util.EnumUtil;
import jp.ecuacion.lib.core.util.PropertiesFileUtil;
import jp.ecuacion.lib.core.util.StringUtil;
import jp.ecuacion.lib.validation.constraints.*;
import jp.ecuacion.splib.core.container.*;

@ItemNameKeyClass("mailAuthManagement")
public abstract class MailAuthManagementBaseRecord extends SystemCommonBaseRecord implements ItemContainer {

  @LongString
  protected String id;
  @LongString
  protected String accId;
  protected String authKind;
  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "code")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  protected String authCode;
  protected Boolean isValid;
  protected Boolean hasUsed;
  @SizeString(min = 1, max = 256)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9_\\-\\+\\.]*@[a-zA-Z0-9_\\-\\.]*$", description = "mailAddress")
  protected String newMailAddress;
  @SizeString(min = 60, max = 60)
  protected String newPassword;
  protected String mailSentDatetime;

  static {
    getStringLengthMap().put("id", null);
    getStringLengthMap().put("accId", null);
    getStringLengthMap().put("authCode", 100);
    getStringLengthMap().put("newMailAddress", 256);
    getStringLengthMap().put("newPassword", 60);
    getStringLengthMap().put("mailSentDatetime", null);
  }

  public MailAuthManagementBaseRecord() {
    super();
  }

  public MailAuthManagementBaseRecord(MailAuthManagement e, DatetimeFormatParameters params) {
    super(e, params);
    this.id = (e.getId() == null) ? "" : Long.toString(e.getId());
    this.accId = (e.getAccId() == null) ? "" : Long.toString(e.getAccId());
    this.authKind = (e.getAuthKind() == null) ? "" : e.getAuthKind().getCode();
    this.authCode = e.getAuthCode();
    this.isValid = e.getIsValid();
    this.hasUsed = e.getHasUsed();
    this.newMailAddress = e.getNewMailAddress();
    this.newPassword = e.getNewPassword();
    this.mailSentDatetime = e.getMailSentDatetime() == null ? "" : e.getMailSentDatetime().withOffsetSameInstant(params.getZoneOffset()).format(DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
  }

  public MailAuthManagementBaseRecord(MailAuthManagementBaseRecord rec) {
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

  // accessor:authKind
  public String getAuthKind() {
    return authKind;
  }

  public void setAuthKind(String authKind) {
    this.authKind = authKind;
  }

  public MailAuthKindEnum getAuthKindOfEntityDataType() {
    return (getAuthKind() == null || getAuthKind().equals("")) ? null : EnumUtil.getEnumFromCode(MailAuthKindEnum.class, authKind);
  }

  // accessor:authCode
  public String getAuthCode() {
    return authCode;
  }

  public void setAuthCode(String authCode) {
    this.authCode = authCode;
  }

  // accessor:isValid
  public Boolean getIsValid() {
    return isValid;
  }

  public void setIsValid(Boolean isValid) {
    this.isValid = isValid;
  }

  // accessor:hasUsed
  public Boolean getHasUsed() {
    return hasUsed;
  }

  public void setHasUsed(Boolean hasUsed) {
    this.hasUsed = hasUsed;
  }

  // accessor:newMailAddress
  public String getNewMailAddress() {
    return newMailAddress;
  }

  public void setNewMailAddress(String newMailAddress) {
    this.newMailAddress = newMailAddress;
  }

  // accessor:newPassword
  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  // accessor:mailSentDatetime
  public String getMailSentDatetime() {
    return mailSentDatetime;
  }

  public void setMailSentDatetime(String mailSentDatetime) {
    this.mailSentDatetime = mailSentDatetime;
  }

  public OffsetDateTime getMailSentDatetimeOfEntityDataType() {
    return (getMailSentDatetime() == null || getMailSentDatetime().equals("")) ? null : OffsetDateTime.parse(mailSentDatetime, DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
  }

  public List<String[]> getAuthKindList(Locale locale, String options) {
    return EnumUtil.getListForHtmlSelect(MailAuthKindEnum.class, locale, options);
  }

  public String getAuthKindName(Locale locale) {
    return EnumUtil.getEnumFromCode(MailAuthKindEnum.class, getAuthKind()).getDisplayName(locale);
  }

  public List<String[]> getIsValidList(Locale locale, String options) {
    return getBooleanDropdownList(locale, "isValid", options);
  }

  public String getIsValidName(Locale locale) {
    return PropertiesFileUtil.getMessage(locale, "boolean.isValid." + isValid);
  }

  public List<String[]> getHasUsedList(Locale locale, String options) {
    return getBooleanDropdownList(locale, "hasUsed", options);
  }

  public String getHasUsedName(Locale locale) {
    return PropertiesFileUtil.getMessage(locale, "boolean.hasUsed." + hasUsed);
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
