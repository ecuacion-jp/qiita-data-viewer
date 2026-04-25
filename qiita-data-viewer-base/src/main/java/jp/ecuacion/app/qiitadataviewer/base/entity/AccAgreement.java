package jp.ecuacion.app.qiitadataviewer.base.entity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.record.AccAgreementBaseRecord;
import org.hibernate.annotations.Filter;

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

  /**defaultコンストラクタ */
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
