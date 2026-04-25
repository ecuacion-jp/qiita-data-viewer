package jp.ecuacion.app.qiitadataviewer.base.record;

import jakarta.validation.Valid;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaItemTagVersion;
import jp.ecuacion.lib.core.annotation.ItemNameKeyClass;
import jp.ecuacion.lib.core.item.*;
import jp.ecuacion.lib.core.util.StringUtil;
import jp.ecuacion.lib.validation.constraints.*;
import jp.ecuacion.splib.core.container.*;

@ItemNameKeyClass("qiitaItemTagVersion")
public abstract class QiitaItemTagVersionBaseRecord extends SystemCommonBaseRecord implements ItemContainer {

  @LongString
  protected String id;
  @Valid
  protected AccBaseRecord acc;
  @Valid
  protected QiitaItemTagBaseRecord qiitaItemTag;
  @SizeString(min = 0, max = 50)
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  protected String versionName;

  static {
    getStringLengthMap().put("id", null);
    getStringLengthMap().put("accId", null);
    getStringLengthMap().put("qiitaItemTagId", null);
    getStringLengthMap().put("versionName", 50);
  }

  public QiitaItemTagVersionBaseRecord() {
    this(3);
  }

  public QiitaItemTagVersionBaseRecord(int count) {
    super();

    count--;

    if (count > 0) {
      acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
      qiitaItemTag = new QiitaItemTagBaseRecord(count) {public Item[] customizedItems() {return null;}};
    }
  }

  public QiitaItemTagVersionBaseRecord(QiitaItemTagVersion e, DatetimeFormatParameters params) {
    this(e, params, 3);
  }

  public QiitaItemTagVersionBaseRecord(QiitaItemTagVersion e, DatetimeFormatParameters params, int count) {
    super(e, params);

    count--;

    this.id = (e.getId() == null) ? "" : Long.toString(e.getId());
    if (count > 0) {
      this.acc = new AccBaseRecord(e.getAcc(), params, count) {public Item[] customizedItems() {return null;}};
    }
    if (count > 0) {
      this.qiitaItemTag = new QiitaItemTagBaseRecord(e.getQiitaItemTag(), params, count) {public Item[] customizedItems() {return null;}};
    }
    this.versionName = e.getVersionName();
  }

  public QiitaItemTagVersionBaseRecord(QiitaItemTagVersionBaseRecord rec) {
    this(rec, 3);
  }

  public QiitaItemTagVersionBaseRecord(QiitaItemTagVersionBaseRecord rec, int count) {
    super(rec);

    count--;

    this.id = rec.getId();
    this.acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
    this.setAccId(rec.getAccId());
    this.qiitaItemTag = new QiitaItemTagBaseRecord(count) {public Item[] customizedItems() {return null;}};
    this.setQiitaItemTagId(rec.getQiitaItemTagId());
    this.versionName = rec.getVersionName();
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

  // accessor:qiitaItemTagId
  public String getQiitaItemTagId() {
    return qiitaItemTag == null ? null : qiitaItemTag.getId();
  }

  public void setQiitaItemTagId(String qiitaItemTagId) {
    this.qiitaItemTag.setId(qiitaItemTagId);
  }

  public Long getQiitaItemTagIdOfEntityDataType() {
    return (getQiitaItemTagId() == null || getQiitaItemTagId().equals("")) ? null : getQiitaItemTag().getIdOfEntityDataType();
  }

  public QiitaItemTagBaseRecord getQiitaItemTag() {
    return qiitaItemTag;
  }

  public void setQiitaItemTag(QiitaItemTagBaseRecord qiitaItemTag) {
    this.qiitaItemTag = qiitaItemTag;
  }

  // accessor:versionName
  public String getVersionName() {
    return versionName;
  }

  public void setVersionName(String versionName) {
    this.versionName = versionName;
  }

  public String getIds() {
    return StringUtil.getSeparatedValuesString(new String[] {getId() == null ? "" : getId(), getQiitaItemTag() == null || getQiitaItemTag().getId() == null? "" : getQiitaItemTag().getId()}, "-");
  }

  public void setIds(String idCsv) {
    String[] ids = idCsv.split("-");
    if (ids.length < 2) return;

    setId(ids[0]);
    getQiitaItemTag().setId(ids[1]);
  }

  public String getOptimisticLockVersions() {
    return StringUtil.getSeparatedValuesString(new String[] {getVersion() == null ? "" : getVersion(), getQiitaItemTag() == null || getQiitaItemTag().getVersion() == null ? "" : getQiitaItemTag().getVersion()}, "-");
  }

  public void setOptimisticLockVersions(String versionCsv) {
    String[] versions = versionCsv.split("-");
    if (versions.length < 2) return;

    setVersion(versions[0]);
    getQiitaItemTag().setVersion(versions[1]);
  }
}
