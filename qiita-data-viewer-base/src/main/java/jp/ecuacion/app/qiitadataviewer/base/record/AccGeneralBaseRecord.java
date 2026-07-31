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

    if (count > 0 && e.getAcc() != null) {
      this.acc = new AccBaseRecord(e.getAcc(), params, count) {public Item[] customizedItems() {return null;}};
    }
    this.accessibleToAllApps = e.getAccessibleToAllApps();
    this.setIds(StringUtil.getSeparatedValuesString(new String[] {getAcc() == null || getAcc().getId() == null ? "" : getAcc().getId()}, ","));
    this.setOptimisticLockVersions(StringUtil.getSeparatedValuesString(new String[] {getVersion() == null ? "" : getVersion()}, ","));
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

  @Override
  public void setIds(String idCsv) {
    super.setIds(idCsv);
    String[] ids = idCsv.split(",", -1);
    if (ids.length < 1) return;

    if (getAcc() != null) {
      getAcc().setId(ids[0]);
    }
  }

  public String getVersionSnapshot() {
    return getSnapshotSegment(getOptimisticLockVersions(), 0);
  }

}
