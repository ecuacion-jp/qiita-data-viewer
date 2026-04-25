package jp.ecuacion.app.qiitadataviewer.base.entity;
import jakarta.annotation.Nonnull;
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

  /**defaultコンストラクタ */
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
