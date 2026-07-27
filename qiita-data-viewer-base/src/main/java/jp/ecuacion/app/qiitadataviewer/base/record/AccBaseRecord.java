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
import jp.ecuacion.app.qiitadataviewer.base.entity.Acc;
import jp.ecuacion.app.qiitadataviewer.base.enums.AccRoleEnum;
import jp.ecuacion.lib.core.annotation.ItemNameKeyClass;
import jp.ecuacion.lib.core.item.*;
import jp.ecuacion.lib.core.util.EnumUtil;
import jp.ecuacion.lib.core.util.PropertiesFileUtil;
import jp.ecuacion.lib.core.util.StringUtil;
import jp.ecuacion.lib.validation.constraints.*;
import jp.ecuacion.splib.core.container.*;

@ItemNameKeyClass("acc")
public abstract class AccBaseRecord extends SystemCommonBaseRecord implements ItemContainer {

  @LongString
  protected String id;
  @Valid
  protected AccAdminBaseRecord accAdmin;
  @Valid
  protected AccGeneralBaseRecord accGeneral;
  @SizeString(min = 1, max = 256)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9_\\\\-\\\\+\\\\.]*@[a-zA-Z0-9_\\\\-\\\\.]*$", description = "mailAddress")
  protected String mailAddress;
  @SizeString(min = 1, max = 30)
  @PatternWithDescription(regexp = "^[^'$%&\\\\(\\\\)=\\\\^~,<>/\\\\?]*$", description = "accName")
  @PatternWithDescription(regexp = "^[^!\\\"#\\\\$%&\\\\(\\\\)=\\\\^~\\\\\\\\\\\\|`\\\\[\\\\{;\\\\+:\\\\\\\\*\\\\]\\\\},<>/\\\\?]*$", description = "prohibitedChars")
  protected String name;
  protected Boolean isAdmin;
  protected String role;
  @SizeString(min = 60, max = 60)
  protected String hashedPassword;
  protected Boolean isValid;
  protected Boolean isAuthenticated;
  protected Boolean hasLoggedIn;

  static {
    getStringLengthMap().put("id", null);
    getStringLengthMap().put("mailAddress", 256);
    getStringLengthMap().put("name", 30);
    getStringLengthMap().put("hashedPassword", 60);
  }

  public AccBaseRecord() {
    this(3);
  }

  public AccBaseRecord(int count) {
    super();

    count--;

    if (count > 0) {
      accAdmin = new AccAdminBaseRecord(count) {public Item[] customizedItems() {return null;}};
      accGeneral = new AccGeneralBaseRecord(count) {public Item[] customizedItems() {return null;}};
    }
  }

  public AccBaseRecord(Acc e, DatetimeFormatParameters params) {
    this(e, params, 3);
  }

  public AccBaseRecord(Acc e, DatetimeFormatParameters params, int count) {
    super(e, params);

    count--;

    this.id = (e.getId() == null) ? "" : Long.toString(e.getId());
    this.mailAddress = e.getMailAddress();
    this.name = e.getName();
    this.isAdmin = e.getIsAdmin();
    this.role = (e.getRole() == null) ? "" : e.getRole().getCode();
    this.hashedPassword = e.getHashedPassword();
    this.isValid = e.getIsValid();
    this.isAuthenticated = e.getIsAuthenticated();
    this.hasLoggedIn = e.getHasLoggedIn();
    this.setIds(StringUtil.getSeparatedValuesString(new String[] {getId() == null ? "" : getId()}, ","));
    this.setOptimisticLockVersions(StringUtil.getSeparatedValuesString(new String[] {getVersion() == null ? "" : getVersion()}, ","));

    if (count > 0) {
      accAdmin = (e.getAccAdmin() == null) ? null : new AccAdminBaseRecord(e.getAccAdmin(), params, count) {public Item[] customizedItems() {return null;}};
      accGeneral = (e.getAccGeneral() == null) ? null : new AccGeneralBaseRecord(e.getAccGeneral(), params, count) {public Item[] customizedItems() {return null;}};
    }
  }

  public AccBaseRecord(AccBaseRecord rec) {
    this(rec, 3);
  }

  public AccBaseRecord(AccBaseRecord rec, int count) {
    super(rec);

    count--;

    this.id = rec.getId();
    this.mailAddress = rec.getMailAddress();
    this.name = rec.getName();
    this.isAdmin = rec.getIsAdmin();
    this.role = rec.getRole();
    this.hashedPassword = rec.getHashedPassword();
    this.isValid = rec.getIsValid();
    this.isAuthenticated = rec.getIsAuthenticated();
    this.hasLoggedIn = rec.getHasLoggedIn();
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

  public AccAdminBaseRecord getAccAdmin() {
    return accAdmin;
  }

  public void setAccAdmin(AccAdminBaseRecord accAdmin) {
    this.accAdmin = accAdmin;
  }

  public AccGeneralBaseRecord getAccGeneral() {
    return accGeneral;
  }

  public void setAccGeneral(AccGeneralBaseRecord accGeneral) {
    this.accGeneral = accGeneral;
  }

  // accessor:mailAddress
  public String getMailAddress() {
    return mailAddress;
  }

  public void setMailAddress(String mailAddress) {
    this.mailAddress = mailAddress;
  }

  // accessor:name
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // accessor:isAdmin
  public Boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  // accessor:role
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public AccRoleEnum getRoleOfEntityDataType() {
    return (getRole() == null || getRole().equals("")) ? null : EnumUtil.getEnumFromCode(AccRoleEnum.class, role);
  }

  // accessor:hashedPassword
  public String getHashedPassword() {
    return hashedPassword;
  }

  public void setHashedPassword(String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }

  // accessor:isValid
  public Boolean getIsValid() {
    return isValid;
  }

  public void setIsValid(Boolean isValid) {
    this.isValid = isValid;
  }

  // accessor:isAuthenticated
  public Boolean getIsAuthenticated() {
    return isAuthenticated;
  }

  public void setIsAuthenticated(Boolean isAuthenticated) {
    this.isAuthenticated = isAuthenticated;
  }

  // accessor:hasLoggedIn
  public Boolean getHasLoggedIn() {
    return hasLoggedIn;
  }

  public void setHasLoggedIn(Boolean hasLoggedIn) {
    this.hasLoggedIn = hasLoggedIn;
  }

  public List<String[]> getIsAdminList(Locale locale, String options) {
    return getBooleanDropdownList(locale, "isAdmin", options);
  }

  public String getIsAdminName(Locale locale) {
    return PropertiesFileUtil.getMessage(locale, "boolean.isAdmin." + isAdmin);
  }

  public List<String[]> getRoleList(Locale locale, String options) {
    return EnumUtil.getListForHtmlSelect(AccRoleEnum.class, locale, options);
  }

  public String getRoleName(Locale locale) {
    return EnumUtil.getEnumFromCode(AccRoleEnum.class, getRole()).getDisplayName(locale);
  }

  public List<String[]> getIsValidList(Locale locale, String options) {
    return getBooleanDropdownList(locale, "isValid", options);
  }

  public String getIsValidName(Locale locale) {
    return PropertiesFileUtil.getMessage(locale, "boolean.isValid." + isValid);
  }

  public List<String[]> getIsAuthenticatedList(Locale locale, String options) {
    return getBooleanDropdownList(locale, "isAuthenticated", options);
  }

  public String getIsAuthenticatedName(Locale locale) {
    return PropertiesFileUtil.getMessage(locale, "boolean.isAuthenticated." + isAuthenticated);
  }

  public List<String[]> getHasLoggedInList(Locale locale, String options) {
    return getBooleanDropdownList(locale, "hasLoggedIn", options);
  }

  public String getHasLoggedInName(Locale locale) {
    return PropertiesFileUtil.getMessage(locale, "boolean.hasLoggedIn." + hasLoggedIn);
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
