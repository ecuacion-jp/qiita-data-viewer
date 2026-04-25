package jp.ecuacion.app.qiitadataviewer.base.entity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.record.AccAdminBaseRecord;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ACC_ADMIN")
@Filter(name = "groupFilter")
public final class AccAdmin extends SystemCommon implements Serializable {

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

  // ID
  public static final String FIELD_ACC_ID = "accId";

  @Override
  public String[] getFieldNameArr() {
    return new String[] {"accId", "createAccId", "createTime", "lstUpdAccId", "lstUpdTime", "delFlg", "version"};
  }

  /**defaultコンストラクタ */
  public AccAdmin() {}

  /** A constructor with record argument */
  public AccAdmin(AccAdminBaseRecord rec, Acc acc) {
    super(rec);

    if (acc != null) setAcc(acc);
  }

  public void update(AccAdminBaseRecord rec, Acc acc, String... skipUpdateFields) {
    if (acc != null) setAcc(acc);
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
