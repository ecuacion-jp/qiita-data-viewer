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
import java.io.Serializable;
import java.time.*;
import jp.ecuacion.app.qiitadataviewer.base.record.SystemCommonBaseRecord;
import jp.ecuacion.splib.jpa.entity.SplibEntity;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.type.descriptor.java.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.*;

@FilterDef(name = "groupFilter", 
    parameters = @ParamDef(name = "accId", type = LongJavaType.class),
    defaultCondition = "ACC_ID = :accId")
@FilterDef(name = "softDeleteFilter", defaultCondition = "DEL_FLG = false")
@Filter(name = "softDeleteFilter")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class SystemCommon extends SplibEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @CreatedBy
  @Column(name = "CREATE_ACC_ID", nullable = false)
  protected Long createAccId;

  @CreatedDate
  @Column(name = "CREATE_TIME", nullable = false, columnDefinition = "timestamp with time zone")
  protected OffsetDateTime createTime;

  @LastModifiedBy
  @Column(name = "LST_UPD_ACC_ID", nullable = false)
  protected Long lstUpdAccId;

  @LastModifiedDate
  @Column(name = "LST_UPD_TIME", nullable = false, columnDefinition = "timestamp with time zone")
  protected OffsetDateTime lstUpdTime;

  @Column(name = "DEL_FLG", nullable = false)
  protected Boolean delFlg;

  @Version
  @Column(name = "VERSION", nullable = false)
  protected Long version;

  // ID
  public static final String FIELD_CREATE_ACC_ID = "createAccId";
  public static final String FIELD_CREATE_TIME = "createTime";
  public static final String FIELD_LST_UPD_ACC_ID = "lstUpdAccId";
  public static final String FIELD_LST_UPD_TIME = "lstUpdTime";
  public static final String FIELD_DEL_FLG = "delFlg";
  public static final String FIELD_VERSION = "version";

  /**Default constructor. */
  public SystemCommon() {}

  /** A constructor with record argument */
  public SystemCommon(SystemCommonBaseRecord rec) {
    super();

    if (rec.getCreateAccId() != null) setCreateAccId(rec.getCreateAccIdOfEntityDataType());
    if (createTime != null) setCreateTime(createTime);
    if (rec.getLstUpdAccId() != null) setLstUpdAccId(rec.getLstUpdAccIdOfEntityDataType());
    if (lstUpdTime != null) setLstUpdTime(lstUpdTime);
    if (rec.getDelFlg() != null) setDelFlg(rec.getDelFlg());
    if (rec.getVersion() != null) setVersion(rec.getVersionOfEntityDataType());
  }

  public Long getCreateAccId() {
    return createAccId;
  }

  public void setCreateAccId(Long createAccId) {
    this.createAccId = createAccId;
  }

  public OffsetDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(OffsetDateTime createTime) {
    this.createTime = createTime;
  }

  public Long getLstUpdAccId() {
    return lstUpdAccId;
  }

  public void setLstUpdAccId(Long lstUpdAccId) {
    this.lstUpdAccId = lstUpdAccId;
  }

  public OffsetDateTime getLstUpdTime() {
    return lstUpdTime;
  }

  public void setLstUpdTime(OffsetDateTime lstUpdTime) {
    this.lstUpdTime = lstUpdTime;
  }

  public Boolean getDelFlg() {
    return delFlg;
  }

  public void setDelFlg(Boolean delFlg) {
    this.delFlg = delFlg;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  @PrePersist
  public void preInsert() {
    createTime = OffsetDateTime.now();
    lstUpdTime = OffsetDateTime.now();
    if (delFlg == null) delFlg = false;
    if (version == null) version = 1L;
  }

  @PreUpdate
  public void preUpdate() {
    lstUpdTime = OffsetDateTime.now();
  }

  public boolean hasSoftDeleteField() {
    return true;
  }
}