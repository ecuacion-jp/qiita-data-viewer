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
import jp.ecuacion.app.qiitadataviewer.base.record.AccAgreementBaseRecord;
import org.hibernate.annotations.Filter;
import org.jspecify.annotations.NonNull;

@Entity
@Table(name = "ACC_AGREEMENT")
@Filter(name = "groupFilter")
public final class AccAgreement extends SystemCommon implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID", nullable = false, columnDefinition = "bigserial")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACC_AGREEMENT_ID_SEQ_GEN")
  @SequenceGenerator(name = "ACC_AGREEMENT_ID_SEQ_GEN", sequenceName = "ACC_AGREEMENT_ID_SEQ", allocationSize = 1)
  protected Long id;

  @NotNull
  @Column(name = "ACC_ID", nullable = false)
  protected Long accId;

  @NotNull
  @Column(name = "AGREEMENT_ID", nullable = false)
  protected Long agreementId;

  // ID
  public static final String FIELD_ID = "id";
  public static final String FIELD_ACC_ID = "accId";
  public static final String FIELD_AGREEMENT_ID = "agreementId";

  @Override
  public String[] getFieldNameArr() {
    return new String[] {"id", "accId", "agreementId", "createAccId", "createTime", "lstUpdAccId", "lstUpdTime", "delFlg", "version"};
  }

  /**Default constructor. */
  public AccAgreement() {}

  /** A constructor with record argument */
  public AccAgreement(AccAgreementBaseRecord rec) {
    super(rec);

    if (rec.getId() != null) setId(rec.getIdOfEntityDataType());
    if (rec.getAccId() != null) setAccId(rec.getAccIdOfEntityDataType());
    if (rec.getAgreementId() != null) setAgreementId(rec.getAgreementIdOfEntityDataType());
  }

  public void update(AccAgreementBaseRecord rec, String... skipUpdateFields) {
    List<String> skipUpdateFieldList = Arrays.asList(skipUpdateFields);

    if (rec.getId() != null && !skipUpdateFieldList.contains(FIELD_ID)) setId(rec.getIdOfEntityDataType());
    if (rec.getAccId() != null && !skipUpdateFieldList.contains(FIELD_ACC_ID)) setAccId(rec.getAccIdOfEntityDataType());
    if (rec.getAgreementId() != null && !skipUpdateFieldList.contains(FIELD_AGREEMENT_ID)) setAgreementId(rec.getAgreementIdOfEntityDataType());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getAccId() {
    return accId;
  }

  public void setAccId(Long accId) {
    this.accId = accId;
  }

  public Long getAgreementId() {
    return agreementId;
  }

  public void setAgreementId(Long agreementId) {
    this.agreementId = agreementId;
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
