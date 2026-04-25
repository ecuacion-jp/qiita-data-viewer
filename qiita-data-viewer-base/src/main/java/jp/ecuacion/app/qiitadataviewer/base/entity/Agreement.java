package jp.ecuacion.app.qiitadataviewer.base.entity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.*;
import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.record.AgreementBaseRecord;

@Entity
@Table(name = "AGREEMENT")
public final class Agreement extends SystemCommon implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID", nullable = false, columnDefinition = "bigserial")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AGREEMENT_ID_SEQ_GEN")
  @SequenceGenerator(name = "AGREEMENT_ID_SEQ_GEN", sequenceName = "AGREEMENT_ID_SEQ", allocationSize = 1)
  protected Long id;

  @Column(name = "UPLOADED_DATETIME", nullable = false, columnDefinition = "timestamp with time zone")
  protected OffsetDateTime uploadedDatetime;

  // ID
  public static final String FIELD_ID = "id";
  public static final String FIELD_UPLOADED_DATETIME = "uploadedDatetime";

  @Override
  public String[] getFieldNameArr() {
    return new String[] {"id", "uploadedDatetime", "createAccId", "createTime", "lstUpdAccId", "lstUpdTime", "delFlg", "version"};
  }

  /**defaultコンストラクタ */
  public Agreement() {}

  /** A constructor with record argument */
  public Agreement(AgreementBaseRecord rec, OffsetDateTime uploadedDatetime) {
    super(rec);

    if (rec.getId() != null) setId(rec.getIdOfEntityDataType());
    if (uploadedDatetime != null) setUploadedDatetime(uploadedDatetime);
  }

  public void update(AgreementBaseRecord rec, OffsetDateTime uploadedDatetime, String... skipUpdateFields) {
    List<String> skipUpdateFieldList = Arrays.asList(skipUpdateFields);

    if (rec.getId() != null && !skipUpdateFieldList.contains(FIELD_ID)) setId(rec.getIdOfEntityDataType());
    if (uploadedDatetime != null && !skipUpdateFieldList.contains(FIELD_UPLOADED_DATETIME)) setUploadedDatetime(uploadedDatetime);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OffsetDateTime getUploadedDatetime() {
    return uploadedDatetime;
  }

  public void setUploadedDatetime(OffsetDateTime uploadedDatetime) {
    this.uploadedDatetime = uploadedDatetime;
  }

  // getNaturalKeyFieldList()
  public List<String> getNaturalKeyFieldList() {
    return null;
  }

  // getSetOfUniqueConstraintFieldList()
  // 今は実質naturalKeyしかないのでそれをSetに入れて返す。
  // 将来的には他のunique keyも設定できるようにする。（でないとinsert時に論理削除済レコードが残っていた場合の自動削除ができない）
  @Nonnull
  public Set<List<String>> getSetOfUniqueConstraintFieldList() {
    Set<List<String>> rtnSet = new HashSet<>();
    List<String> list = getNaturalKeyFieldList();
    if (list != null) {
      rtnSet.add(list);
    }

    return rtnSet;
  }

}
