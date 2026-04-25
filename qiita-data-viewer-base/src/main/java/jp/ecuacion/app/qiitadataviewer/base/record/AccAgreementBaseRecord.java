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
