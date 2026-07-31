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
package jp.ecuacion.app.qiitadataviewer.base.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.converter.AccRoleConverter;
import jp.ecuacion.app.qiitadataviewer.base.enums.AccRoleEnum;
import jp.ecuacion.app.qiitadataviewer.base.record.AccBaseRecord;
import jp.ecuacion.lib.validation.constraints.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.type.descriptor.java.*;
import org.jspecify.annotations.NonNull;

@Entity
@Table(name = "ACC", uniqueConstraints = {@UniqueConstraint(columnNames = {"MAIL_ADDRESS"})})
@FilterDef(name = "groupFilterAcc", 
    parameters = @ParamDef(name = "id", type = LongJavaType.class),
    defaultCondition = "ID = :id")
@Filter(name = "groupFilterAcc")
public final class Acc extends SystemCommon implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID", nullable = false, columnDefinition = "bigserial")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACC_ID_SEQ_GEN")
  @SequenceGenerator(name = "ACC_ID_SEQ_GEN", sequenceName = "ACC_ID_SEQ", allocationSize = 1)
  protected Long id;

  @OneToOne(cascade={CascadeType.DETACH, CascadeType.REMOVE}, mappedBy = "acc")
  @Filter(name = "softDeleteFilter")
  @Filter(name = "groupFilter")
  protected AccAdmin accAdmin;

  @OneToOne(cascade={CascadeType.DETACH, CascadeType.REMOVE}, mappedBy = "acc")
  @Filter(name = "softDeleteFilter")
  @Filter(name = "groupFilter")
  protected AccGeneral accGeneral;

  @NotEmpty
  @SizeString(min = 1, max = 256)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9_\\\\-\\\\+\\\\.]*@[a-zA-Z0-9_\\\\-\\\\.]*$", description = "mailAddress")
  @Column(name = "MAIL_ADDRESS", nullable = false, length = 256)
  protected String mailAddress;

  @NotEmpty
  @SizeString(min = 1, max = 30)
  @PatternWithDescription(regexp = "^[^'$%&\\\\(\\\\)=\\\\^~,<>/\\\\?]*$", description = "accName")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  @Column(name = "NAME", nullable = false, length = 30)
  protected String name;

  @NotNull
  @Column(name = "IS_ADMIN", nullable = false)
  protected Boolean isAdmin;

  @NotNull
  @Column(name = "ROLE", nullable = false, length = 1)
  @Convert(converter = AccRoleConverter.class)
  protected AccRoleEnum role;

  @SizeString(min = 60, max = 60)
  @Column(name = "HASHED_PASSWORD", nullable = true, length = 60)
  protected String hashedPassword;

  @NotNull
  @Column(name = "IS_VALID", nullable = false)
  protected Boolean isValid;

  @NotNull
  @Column(name = "IS_AUTHENTICATED", nullable = false)
  protected Boolean isAuthenticated;

  @NotNull
  @Column(name = "HAS_LOGGED_IN", nullable = false)
  protected Boolean hasLoggedIn;

  // ID
  public static final String FIELD_ID = "id";
  public static final String FIELD_MAIL_ADDRESS = "mailAddress";
  public static final String FIELD_NAME = "name";
  public static final String FIELD_IS_ADMIN = "isAdmin";
  public static final String FIELD_ROLE = "role";
  public static final String FIELD_HASHED_PASSWORD = "hashedPassword";
  public static final String FIELD_IS_VALID = "isValid";
  public static final String FIELD_IS_AUTHENTICATED = "isAuthenticated";
  public static final String FIELD_HAS_LOGGED_IN = "hasLoggedIn";

  @Override
  public String[] getFieldNameArr() {
    return new String[] {"id", "mailAddress", "name", "isAdmin", "role", "hashedPassword", "isValid", "isAuthenticated", "hasLoggedIn", "createAccId", "createTime", "lstUpdAccId", "lstUpdTime", "delFlg", "version"};
  }

  /**Default constructor. */
  public Acc() {}

  /** A constructor with record argument */
  public Acc(AccBaseRecord rec) {
    super(rec);

    if (rec.getId() != null) setId(rec.getIdOfEntityDataType());
    if (rec.getMailAddress() != null) setMailAddress(rec.getMailAddress());
    if (rec.getName() != null) setName(rec.getName());
    if (rec.getIsAdmin() != null) setIsAdmin(rec.getIsAdmin());
    if (rec.getRole() != null) setRole(rec.getRoleOfEntityDataType());
    if (rec.getHashedPassword() != null) setHashedPassword(rec.getHashedPassword());
    if (rec.getIsValid() != null) setIsValid(rec.getIsValid());
    if (rec.getIsAuthenticated() != null) setIsAuthenticated(rec.getIsAuthenticated());
    if (rec.getHasLoggedIn() != null) setHasLoggedIn(rec.getHasLoggedIn());
  }

  /**
   * Constructor that takes naturalKey as arguments.
   * Having both a naturalKey and surrogateKey constructor could cause conflicts, so only the naturalKey constructor is provided.
   * (The surrogateKey is not used on insert; on select it is retrieved via Entity.getPk(field);
   * on update the selected entity is reused, so there are few scenarios where passing it as a constructor argument is preferred.) 
   */
  public Acc(String mailAddress) {
    this();
    setMailAddress(mailAddress);
  }

  public void update(AccBaseRecord rec, String... skipUpdateFields) {
    List<String> skipUpdateFieldList = Arrays.asList(skipUpdateFields);

    if (rec.getId() != null && !skipUpdateFieldList.contains(FIELD_ID)) setId(rec.getIdOfEntityDataType());
    if (rec.getMailAddress() != null && !skipUpdateFieldList.contains(FIELD_MAIL_ADDRESS)) setMailAddress(rec.getMailAddress());
    if (rec.getName() != null && !skipUpdateFieldList.contains(FIELD_NAME)) setName(rec.getName());
    if (rec.getIsAdmin() != null && !skipUpdateFieldList.contains(FIELD_IS_ADMIN)) setIsAdmin(rec.getIsAdmin());
    if (rec.getRole() != null && !skipUpdateFieldList.contains(FIELD_ROLE)) setRole(rec.getRoleOfEntityDataType());
    if (rec.getHashedPassword() != null && !skipUpdateFieldList.contains(FIELD_HASHED_PASSWORD)) setHashedPassword(rec.getHashedPassword());
    if (rec.getIsValid() != null && !skipUpdateFieldList.contains(FIELD_IS_VALID)) setIsValid(rec.getIsValid());
    if (rec.getIsAuthenticated() != null && !skipUpdateFieldList.contains(FIELD_IS_AUTHENTICATED)) setIsAuthenticated(rec.getIsAuthenticated());
    if (rec.getHasLoggedIn() != null && !skipUpdateFieldList.contains(FIELD_HAS_LOGGED_IN)) setHasLoggedIn(rec.getHasLoggedIn());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public AccAdmin getAccAdmin() {
    return accAdmin;
  }

  public void setAccAdmin(AccAdmin accAdmin) {
    this.accAdmin = accAdmin;
  }

  public AccGeneral getAccGeneral() {
    return accGeneral;
  }

  public void setAccGeneral(AccGeneral accGeneral) {
    this.accGeneral = accGeneral;
  }

  public String getMailAddress() {
    return mailAddress;
  }

  public void setMailAddress(String mailAddress) {
    this.mailAddress = mailAddress;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  public AccRoleEnum getRole() {
    return role;
  }

  public void setRole(AccRoleEnum role) {
    this.role = role;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  public void setHashedPassword(String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }

  public Boolean getIsValid() {
    return isValid;
  }

  public void setIsValid(Boolean isValid) {
    this.isValid = isValid;
  }

  public Boolean getIsAuthenticated() {
    return isAuthenticated;
  }

  public void setIsAuthenticated(Boolean isAuthenticated) {
    this.isAuthenticated = isAuthenticated;
  }

  public Boolean getHasLoggedIn() {
    return hasLoggedIn;
  }

  public void setHasLoggedIn(Boolean hasLoggedIn) {
    this.hasLoggedIn = hasLoggedIn;
  }

  // getNaturalKeyFieldList()
  public List<String> getNaturalKeyFieldList() {
    List<String> rtnList = new ArrayList<>();
    rtnList.add("mailAddress");
    return rtnList;
  }

  // getSetOfUniqueConstraintFieldList()
  // Currently only naturalKey is effectively supported, so it is added to the Set and returned.
  // In the future, other unique keys should also be configurable (otherwise auto-deletion of soft-deleted records on insert would not work).
  @NonNull
  public Set<List<String>> getSetOfUniqueConstraintFieldList() {
    Set<List<String>> rtnSet = new HashSet<>();
    List<String> list = getNaturalKeyFieldList();
    if (list != null) {
      rtnSet.add(list);
    }

    return rtnSet;
  }

}
