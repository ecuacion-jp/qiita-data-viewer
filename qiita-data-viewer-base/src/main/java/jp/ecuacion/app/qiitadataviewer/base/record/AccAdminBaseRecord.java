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
    this.setIds(StringUtil.getSeparatedValuesString(new String[] {getAcc() == null || getAcc().getId() == null ? "" : getAcc().getId()}, ","));
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

    if (getAcc() != null) {
      getAcc().setId(ids[0]);
    }
  }

  @Override
  public void setOptimisticLockVersions(String verCsv) {
    super.setOptimisticLockVersions(verCsv);
    String[] vers = verCsv.split(",", -1);
    if (vers.length < 1) return;

    setVersion(vers[0]);
  }

}
