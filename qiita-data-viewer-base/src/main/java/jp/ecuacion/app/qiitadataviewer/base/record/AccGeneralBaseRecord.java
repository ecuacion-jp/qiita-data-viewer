package jp.ecuacion.app.qiitadataviewer.base.record;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Locale;
import jp.ecuacion.app.qiitadataviewer.base.entity.AccGeneral;
import jp.ecuacion.lib.core.annotation.ItemNameKeyClass;
import jp.ecuacion.lib.core.item.*;
import jp.ecuacion.lib.core.util.PropertiesFileUtil;
import jp.ecuacion.lib.core.util.StringUtil;
import jp.ecuacion.splib.core.container.*;

@ItemNameKeyClass("accGeneral")
public abstract class AccGeneralBaseRecord extends SystemCommonBaseRecord implements ItemContainer {

  @Valid
  protected AccBaseRecord acc;
  protected Boolean accessibleToAllApps;

  static {
    getStringLengthMap().put("accId", null);
  }

  public AccGeneralBaseRecord() {
    this(3);
  }

  public AccGeneralBaseRecord(int count) {
    super();

    count--;

    if (count > 0) {
      acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
    }
  }

  public AccGeneralBaseRecord(AccGeneral e, DatetimeFormatParameters params) {
    this(e, params, 3);
  }

  public AccGeneralBaseRecord(AccGeneral e, DatetimeFormatParameters params, int count) {
    super(e, params);

    count--;

    if (count > 0) {
      this.acc = new AccBaseRecord(e.getAcc(), params, count) {public Item[] customizedItems() {return null;}};
    }
    this.accessibleToAllApps = e.getAccessibleToAllApps();
  }

  public AccGeneralBaseRecord(AccGeneralBaseRecord rec) {
    this(rec, 3);
  }

  public AccGeneralBaseRecord(AccGeneralBaseRecord rec, int count) {
    super(rec);

    count--;

    this.acc = new AccBaseRecord(count) {public Item[] customizedItems() {return null;}};
    this.setAccId(rec.getAccId());
    this.accessibleToAllApps = rec.getAccessibleToAllApps();
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

  // accessor:accessibleToAllApps
  public Boolean getAccessibleToAllApps() {
    return accessibleToAllApps;
  }

  public void setAccessibleToAllApps(Boolean accessibleToAllApps) {
    this.accessibleToAllApps = accessibleToAllApps;
  }

  public List<String[]> getAccessibleToAllAppsList(Locale locale, String options) {
    return getBooleanDropdownList(locale, "accessibleToAllApps", options);
  }

  public String getAccessibleToAllAppsName(Locale locale) {
    return PropertiesFileUtil.getMessage(locale, "boolean.accessibleToAllApps." + accessibleToAllApps);
  }

  public String getIds() {
    return StringUtil.getSeparatedValuesString(new String[] {getAcc().getId() == null ? "" : getAcc().getId()}, "-");
  }

  public void setIds(String idCsv) {
    String[] ids = idCsv.split("-");
    if (ids.length < 1) return;

    getAcc().setId(ids[0]);
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
