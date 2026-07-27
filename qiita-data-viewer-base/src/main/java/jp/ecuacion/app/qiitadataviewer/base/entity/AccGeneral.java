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
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.record.AccGeneralBaseRecord;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jspecify.annotations.NonNull;

@Entity
@Table(name = "ACC_GENERAL")
@Filter(name = "groupFilter")
public final class AccGeneral extends SystemCommon implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  @Valid
  @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "ACC_ID", referencedColumnName = "ID", nullable = false, columnDefinition = "bigint")
  @MapsId
  private Acc acc = new Acc();

  @NotNull
  @Id
  @Column(name = "ACC_ID", nullable = false)
  protected Long accId;

  @NotNull
  @Column(name = "ACCESSIBLE_TO_ALL_APPS", nullable = false)
  protected Boolean accessibleToAllApps;

  // ID
  public static final String FIELD_ACC_ID = "accId";
  public static final String FIELD_ACCESSIBLE_TO_ALL_APPS = "accessibleToAllApps";

  @Override
  public String[] getFieldNameArr() {
    return new String[] {"accId", "accessibleToAllApps", "createAccId", "createTime", "lstUpdAccId", "lstUpdTime", "delFlg", "version"};
  }

  /**Default constructor. */
  public AccGeneral() {}

  /** A constructor with record argument */
  public AccGeneral(AccGeneralBaseRecord rec, Acc acc) {
    super(rec);

    if (acc != null) setAcc(acc);
    if (rec.getAccessibleToAllApps() != null) setAccessibleToAllApps(rec.getAccessibleToAllApps());
  }

  public void update(AccGeneralBaseRecord rec, Acc acc, String... skipUpdateFields) {
    List<String> skipUpdateFieldList = Arrays.asList(skipUpdateFields);

    if (acc != null) setAcc(acc);
    if (rec.getAccessibleToAllApps() != null && !skipUpdateFieldList.contains(FIELD_ACCESSIBLE_TO_ALL_APPS)) setAccessibleToAllApps(rec.getAccessibleToAllApps());
  }

  public Long getAccId() {
    return acc == null ? null : acc.getId();
  }

  public void setAccId(Long accId) {
    this.acc.setId(accId);
  }

  public Acc getAcc() {
    return acc;
  }

  public void setAcc(Acc acc) {
    this.acc = acc;
  }

  public Boolean getAccessibleToAllApps() {
    return accessibleToAllApps;
  }

  public void setAccessibleToAllApps(Boolean accessibleToAllApps) {
    this.accessibleToAllApps = accessibleToAllApps;
  }

  // getNaturalKeyFieldList()
  public List<String> getNaturalKeyFieldList() {
    return null;
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
