package jp.ecuacion.app.qiitadataviewer.base.record;

import jakarta.validation.Valid;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaGroup;
import jp.ecuacion.lib.core.annotation.ItemNameKeyClass;
import jp.ecuacion.lib.core.item.*;
import jp.ecuacion.lib.core.util.PropertiesFileUtil;
import jp.ecuacion.lib.core.util.StringUtil;
import jp.ecuacion.lib.validation.constraints.*;
import jp.ecuacion.splib.core.container.*;

@ItemNameKeyClass("qiitaGroup")
public abstract class QiitaGroupBaseRecord extends SystemCommonBaseRecord implements ItemContainer {

  @LongString
  protected String id;
  @Valid
  protected AccBaseRecord acc;
  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaUserId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  protected String urlName;
  @SizeString(min = 1, max = 255)
  protected String name;
  @SizeString(min = 0, max = 65535)
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  protected String description;
  protected Boolean isPrivate;
  protected String createdAt;
  protected String updatedAt;

  static {
    getStringLengthMap().put("id", null);
    getStringLengthMap().put("accId", null);
    getStringLengthMap().put("urlName", 100);
    getStringLengthMap().put("name", 255);
    getStringLengthMap().put("description", 65535);
    getStringLengthMap().put("createdAt", null);
    getStringLengthMap().put("updatedAt", null);
  }

  public QiitaGroupBaseRecord() {
    this(3);
  }

  public QiitaGroupBaseRecord(int count) {
    super();

    count--;

    if (count > 0) {
      acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
    }
  }

  public QiitaGroupBaseRecord(QiitaGroup e, DatetimeFormatParameters params) {
    this(e, params, 3);
  }

  public QiitaGroupBaseRecord(QiitaGroup e, DatetimeFormatParameters params, int count) {
    super(e, params);

    count--;

    this.id = (e.getId() == null) ? "" : Long.toString(e.getId());
    if (count > 0) {
      this.acc = new AccBaseRecord(e.getAcc(), params, count) {public Item[] customizedItems() {return null;}};
    }
    this.urlName = e.getUrlName();
    this.name = e.getName();
    this.description = e.getDescription();
    this.isPrivate = e.getIsPrivate();
    this.createdAt = e.getCreatedAt() == null ? "" : e.getCreatedAt().withOffsetSameInstant(params.getZoneOffset()).format(DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
    this.updatedAt = e.getUpdatedAt() == null ? "" : e.getUpdatedAt().withOffsetSameInstant(params.getZoneOffset()).format(DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
  }

  public QiitaGroupBaseRecord(QiitaGroupBaseRecord rec) {
    this(rec, 3);
  }

  public QiitaGroupBaseRecord(QiitaGroupBaseRecord rec, int count) {
    super(rec);

    count--;

    this.id = rec.getId();
    this.acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
    this.setAccId(rec.getAccId());
    this.urlName = rec.getUrlName();
    this.name = rec.getName();
    this.description = rec.getDescription();
    this.isPrivate = rec.getIsPrivate();
    this.createdAt = rec.getCreatedAt();
    this.updatedAt = rec.getUpdatedAt();
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

  // accessor:urlName
  public String getUrlName() {
    return urlName;
  }

  public void setUrlName(String urlName) {
    this.urlName = urlName;
  }

  // accessor:name
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // accessor:description
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  // accessor:isPrivate
  public Boolean getIsPrivate() {
    return isPrivate;
  }

  public void setIsPrivate(Boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  // accessor:createdAt
  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public OffsetDateTime getCreatedAtOfEntityDataType() {
    return (getCreatedAt() == null || getCreatedAt().equals("")) ? null : OffsetDateTime.parse(createdAt, DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
  }

  // accessor:updatedAt
  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public OffsetDateTime getUpdatedAtOfEntityDataType() {
    return (getUpdatedAt() == null || getUpdatedAt().equals("")) ? null : OffsetDateTime.parse(updatedAt, DateTimeFormatter.ofPattern(dateTimeFormatParams.getDateTimeFormat()));
  }

  public List<String[]> getIsPrivateList(Locale locale, String options) {
    return getBooleanDropdownList(locale, "isPrivate", options);
  }

  public String getIsPrivateName(Locale locale) {
    return PropertiesFileUtil.getMessage(locale, "boolean.isPrivate." + isPrivate);
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
