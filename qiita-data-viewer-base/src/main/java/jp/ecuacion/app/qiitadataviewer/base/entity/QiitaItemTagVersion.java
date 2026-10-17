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
import jp.ecuacion.app.qiitadataviewer.base.record.QiitaItemTagVersionBaseRecord;
import jp.ecuacion.lib.validation.constraints.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jspecify.annotations.NonNull;

@Entity
@Table(name = "QIITA_ITEM_TAG_VERSION")
@Filter(name = "groupFilter")
public final class QiitaItemTagVersion extends SystemCommon implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID", nullable = false, columnDefinition = "bigserial")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QIITA_ITEM_TAG_VERSION_ID_SEQ_GEN")
  @SequenceGenerator(name = "QIITA_ITEM_TAG_VERSION_ID_SEQ_GEN", sequenceName = "QIITA_ITEM_TAG_VERSION_ID_SEQ", allocationSize = 1)
  protected Long id;

  @NotNull
  @Valid
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "ACC_ID", referencedColumnName = "ID", nullable = false, columnDefinition = "bigint")
  private Acc acc = new Acc();

  @NotNull
  @Valid
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "QIITA_ITEM_TAG_ID", referencedColumnName = "ID", nullable = false, columnDefinition = "bigint")
  private QiitaItemTag qiitaItemTag = new QiitaItemTag();

  @SizeString(min = 0, max = 50)
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  @Column(name = "VERSION_NAME", nullable = true, length = 50)
  protected String versionName;

  // ID
  public static final String FIELD_ID = "id";
  public static final String FIELD_ACC_ID = "accId";
  public static final String FIELD_QIITA_ITEM_TAG_ID = "qiitaItemTagId";
  public static final String FIELD_VERSION_NAME = "versionName";

  @Override
  public String[] getFieldNameArr() {
    return new String[] {"id", "accId", "qiitaItemTagId", "versionName", "createAccId", "createTime", "lstUpdAccId", "lstUpdTime", "delFlg", "version"};
  }

  /**Default constructor. */
  public QiitaItemTagVersion() {}

  /** A constructor with record argument */
  public QiitaItemTagVersion(QiitaItemTagVersionBaseRecord rec, Acc acc, QiitaItemTag qiitaItemTag) {
    super(rec);

    if (rec.getId() != null) setId(rec.getIdOfEntityDataType());
    if (acc != null) setAcc(acc);
    if (qiitaItemTag != null) setQiitaItemTag(qiitaItemTag);
    if (rec.getVersionName() != null) setVersionName(rec.getVersionName());
  }

  public void update(QiitaItemTagVersionBaseRecord rec, Acc acc, QiitaItemTag qiitaItemTag, String... skipUpdateFields) {
    List<String> skipUpdateFieldList = Arrays.asList(skipUpdateFields);

    if (rec.getId() != null && !skipUpdateFieldList.contains(FIELD_ID)) setId(rec.getIdOfEntityDataType());
    if (acc != null) setAcc(acc);
    if (qiitaItemTag != null) setQiitaItemTag(qiitaItemTag);
    if (rec.getVersionName() != null && !skipUpdateFieldList.contains(FIELD_VERSION_NAME)) setVersionName(rec.getVersionName());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Long getQiitaItemTagId() {
    return qiitaItemTag == null ? null : qiitaItemTag.getId();
  }

  public void setQiitaItemTagId(Long qiitaItemTagId) {
    this.qiitaItemTag.setId(qiitaItemTagId);
  }

  public QiitaItemTag getQiitaItemTag() {
    return qiitaItemTag;
  }

  public void setQiitaItemTag(QiitaItemTag qiitaItemTag) {
    this.qiitaItemTag = qiitaItemTag;
  }

  public String getVersionName() {
    return versionName;
  }

  public void setVersionName(String versionName) {
    this.versionName = versionName;
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
