package jp.ecuacion.app.qiitadataviewer.base.entity;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.record.QiitaItemTagBaseRecord;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jspecify.annotations.NonNull;

@Entity
@Table(name = "QIITA_ITEM_TAG", uniqueConstraints = {@UniqueConstraint(columnNames = {"QIITA_ITEM_ID", "QIITA_TAG_ID"})})
@Filter(name = "groupFilter")
public final class QiitaItemTag extends SystemCommon implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID", nullable = false, columnDefinition = "bigserial")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QIITA_ITEM_TAG_ID_SEQ_GEN")
  @SequenceGenerator(name = "QIITA_ITEM_TAG_ID_SEQ_GEN", sequenceName = "QIITA_ITEM_TAG_ID_SEQ", allocationSize = 1)
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
  @JoinColumn(name = "QIITA_ITEM_ID", referencedColumnName = "ID", nullable = false, columnDefinition = "bigint")
  private QiitaItem qiitaItem = new QiitaItem();

  @NotNull
  @Valid
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "QIITA_TAG_ID", referencedColumnName = "ID", nullable = false, columnDefinition = "bigint")
  private QiitaTag qiitaTag = new QiitaTag();

  // ID
  public static final String FIELD_ID = "id";
  public static final String FIELD_ACC_ID = "accId";
  public static final String FIELD_QIITA_ITEM_ID = "qiitaItemId";
  public static final String FIELD_QIITA_TAG_ID = "qiitaTagId";

  @Override
  public String[] getFieldNameArr() {
    return new String[] {"id", "accId", "qiitaItemId", "qiitaTagId", "createAccId", "createTime", "lstUpdAccId", "lstUpdTime", "delFlg", "version"};
  }

  /**Default constructor. */
  public QiitaItemTag() {}

  /** A constructor with record argument */
  public QiitaItemTag(QiitaItemTagBaseRecord rec, Acc acc, QiitaItem qiitaItem, QiitaTag qiitaTag) {
    super(rec);

    if (rec.getId() != null) setId(rec.getIdOfEntityDataType());
    if (acc != null) setAcc(acc);
    if (qiitaItem != null) setQiitaItem(qiitaItem);
    if (qiitaTag != null) setQiitaTag(qiitaTag);
  }

  /**
   * Constructor that takes naturalKey as arguments.
   * Having both a naturalKey and surrogateKey constructor could cause conflicts, so only the naturalKey constructor is provided.
   * (The surrogateKey is not used on insert; on select it is retrieved via Entity.getPk(field);
   * on update the selected entity is reused, so there are few scenarios where passing it as a constructor argument is preferred.) 
   */
  public QiitaItemTag(Long qiitaItemId, Long qiitaTagId) {
    this();
    setQiitaItemId(qiitaItemId);
    setQiitaTagId(qiitaTagId);
  }

  public void update(QiitaItemTagBaseRecord rec, Acc acc, QiitaItem qiitaItem, QiitaTag qiitaTag, String... skipUpdateFields) {
    List<String> skipUpdateFieldList = Arrays.asList(skipUpdateFields);

    if (rec.getId() != null && !skipUpdateFieldList.contains(FIELD_ID)) setId(rec.getIdOfEntityDataType());
    if (acc != null) setAcc(acc);
    if (qiitaItem != null) setQiitaItem(qiitaItem);
    if (qiitaTag != null) setQiitaTag(qiitaTag);
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

  public Long getQiitaItemId() {
    return qiitaItem == null ? null : qiitaItem.getId();
  }

  public void setQiitaItemId(Long qiitaItemId) {
    this.qiitaItem.setId(qiitaItemId);
  }

  public QiitaItem getQiitaItem() {
    return qiitaItem;
  }

  public void setQiitaItem(QiitaItem qiitaItem) {
    this.qiitaItem = qiitaItem;
  }

  public Long getQiitaTagId() {
    return qiitaTag == null ? null : qiitaTag.getId();
  }

  public void setQiitaTagId(Long qiitaTagId) {
    this.qiitaTag.setId(qiitaTagId);
  }

  public QiitaTag getQiitaTag() {
    return qiitaTag;
  }

  public void setQiitaTag(QiitaTag qiitaTag) {
    this.qiitaTag = qiitaTag;
  }

  // getNaturalKeyFieldList()
  public List<String> getNaturalKeyFieldList() {
    List<String> rtnList = new ArrayList<>();
    rtnList.add("qiitaItemId");
    rtnList.add("qiitaTagId");
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
