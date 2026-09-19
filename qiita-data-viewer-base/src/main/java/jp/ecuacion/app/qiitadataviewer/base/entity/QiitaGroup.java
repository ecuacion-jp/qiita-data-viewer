package jp.ecuacion.app.qiitadataviewer.base.entity;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.*;
import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.record.QiitaGroupBaseRecord;
import jp.ecuacion.lib.validation.constraints.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jspecify.annotations.NonNull;

@Entity
@Table(name = "QIITA_GROUP", uniqueConstraints = {@UniqueConstraint(columnNames = {"URL_NAME"})})
@Filter(name = "groupFilter")
public final class QiitaGroup extends SystemCommon implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID", nullable = false, columnDefinition = "bigserial")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QIITA_GROUP_ID_SEQ_GEN")
  @SequenceGenerator(name = "QIITA_GROUP_ID_SEQ_GEN", sequenceName = "QIITA_GROUP_ID_SEQ", allocationSize = 1)
  protected Long id;

  @NotNull
  @Valid
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "ACC_ID", referencedColumnName = "ID", nullable = false, columnDefinition = "bigint")
  private Acc acc = new Acc();

  @NotEmpty
  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "qiitaUserId")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  @Column(name = "URL_NAME", nullable = false, length = 100)
  protected String urlName;

  @NotEmpty
  @SizeString(min = 1, max = 255)
  @Column(name = "NAME", nullable = false, length = 255)
  protected String name;

  @SizeString(min = 0, max = 65535)
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  @Column(name = "DESCRIPTION", nullable = true, length = 65535)
  protected String description;

  @NotNull
  @Column(name = "IS_PRIVATE", nullable = false)
  protected Boolean isPrivate;

  @Column(name = "CREATED_AT", nullable = false, columnDefinition = "timestamp with time zone")
  protected OffsetDateTime createdAt;

  @Column(name = "UPDATED_AT", nullable = false, columnDefinition = "timestamp with time zone")
  protected OffsetDateTime updatedAt;

  // ID
  public static final String FIELD_ID = "id";
  public static final String FIELD_ACC_ID = "accId";
  public static final String FIELD_URL_NAME = "urlName";
  public static final String FIELD_NAME = "name";
  public static final String FIELD_DESCRIPTION = "description";
  public static final String FIELD_IS_PRIVATE = "isPrivate";
  public static final String FIELD_CREATED_AT = "createdAt";
  public static final String FIELD_UPDATED_AT = "updatedAt";

  @Override
  public String[] getFieldNameArr() {
    return new String[] {"id", "accId", "urlName", "name", "description", "isPrivate", "createdAt", "updatedAt", "createAccId", "createTime", "lstUpdAccId", "lstUpdTime", "delFlg", "version"};
  }

  /**Default constructor. */
  public QiitaGroup() {}

  /** A constructor with record argument */
  public QiitaGroup(QiitaGroupBaseRecord rec, OffsetDateTime createdAt, OffsetDateTime updatedAt, Acc acc) {
    super(rec);

    if (rec.getId() != null) setId(rec.getIdOfEntityDataType());
    if (acc != null) setAcc(acc);
    if (rec.getUrlName() != null) setUrlName(rec.getUrlName());
    if (rec.getName() != null) setName(rec.getName());
    if (rec.getDescription() != null) setDescription(rec.getDescription());
    if (rec.getIsPrivate() != null) setIsPrivate(rec.getIsPrivate());
    if (createdAt != null) setCreatedAt(createdAt);
    if (updatedAt != null) setUpdatedAt(updatedAt);
  }

  /**
   * Constructor that takes naturalKey as arguments.
   * Having both a naturalKey and surrogateKey constructor could cause conflicts, so only the naturalKey constructor is provided.
   * (The surrogateKey is not used on insert; on select it is retrieved via Entity.getPk(field);
   * on update the selected entity is reused, so there are few scenarios where passing it as a constructor argument is preferred.) 
   */
  public QiitaGroup(String urlName) {
    this();
    setUrlName(urlName);
  }

  public void update(QiitaGroupBaseRecord rec, OffsetDateTime createdAt, OffsetDateTime updatedAt, Acc acc, String... skipUpdateFields) {
    List<String> skipUpdateFieldList = Arrays.asList(skipUpdateFields);

    if (rec.getId() != null && !skipUpdateFieldList.contains(FIELD_ID)) setId(rec.getIdOfEntityDataType());
    if (acc != null) setAcc(acc);
    if (rec.getUrlName() != null && !skipUpdateFieldList.contains(FIELD_URL_NAME)) setUrlName(rec.getUrlName());
    if (rec.getName() != null && !skipUpdateFieldList.contains(FIELD_NAME)) setName(rec.getName());
    if (rec.getDescription() != null && !skipUpdateFieldList.contains(FIELD_DESCRIPTION)) setDescription(rec.getDescription());
    if (rec.getIsPrivate() != null && !skipUpdateFieldList.contains(FIELD_IS_PRIVATE)) setIsPrivate(rec.getIsPrivate());
    if (createdAt != null && !skipUpdateFieldList.contains(FIELD_CREATED_AT)) setCreatedAt(createdAt);
    if (updatedAt != null && !skipUpdateFieldList.contains(FIELD_UPDATED_AT)) setUpdatedAt(updatedAt);
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

  public String getUrlName() {
    return urlName;
  }

  public void setUrlName(String urlName) {
    this.urlName = urlName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getIsPrivate() {
    return isPrivate;
  }

  public void setIsPrivate(Boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  // getNaturalKeyFieldList()
  public List<String> getNaturalKeyFieldList() {
    List<String> rtnList = new ArrayList<>();
    rtnList.add("urlName");
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
