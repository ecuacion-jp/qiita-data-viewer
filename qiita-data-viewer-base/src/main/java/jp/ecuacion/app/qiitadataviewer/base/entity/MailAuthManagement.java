package jp.ecuacion.app.qiitadataviewer.base.entity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.*;
import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.converter.MailAuthKindConverter;
import jp.ecuacion.app.qiitadataviewer.base.enums.MailAuthKindEnum;
import jp.ecuacion.app.qiitadataviewer.base.record.MailAuthManagementBaseRecord;
import jp.ecuacion.lib.validation.constraints.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "MAIL_AUTH_MANAGEMENT")
@Filter(name = "groupFilter")
public final class MailAuthManagement extends SystemCommon implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID", nullable = false, columnDefinition = "bigserial")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MAIL_AUTH_MANAGEMENT_ID_SEQ_GEN")
  @SequenceGenerator(name = "MAIL_AUTH_MANAGEMENT_ID_SEQ_GEN", sequenceName = "MAIL_AUTH_MANAGEMENT_ID_SEQ", allocationSize = 1)
  protected Long id;

  @NotNull
  @Column(name = "ACC_ID", nullable = false)
  protected Long accId;

  @NotNull
  @Column(name = "AUTH_KIND", nullable = false, length = 2)
  @Convert(converter = MailAuthKindConverter.class)
  protected MailAuthKindEnum authKind;

  @NotEmpty
  @SizeString(min = 1, max = 100)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "code")
  @PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
  @Column(name = "AUTH_CODE", nullable = false, length = 100)
  protected String authCode;

  @NotNull
  @Column(name = "IS_VALID", nullable = false)
  protected Boolean isValid;

  @NotNull
  @Column(name = "HAS_USED", nullable = false)
  protected Boolean hasUsed;

  @SizeString(min = 1, max = 256)
  @PatternWithDescription(regexp = "^[a-zA-Z0-9_\\-\\+\\.]*@[a-zA-Z0-9_\\-\\.]*$", description = "mailAddress")
  @Column(name = "NEW_MAIL_ADDRESS", nullable = true, length = 256)
  protected String newMailAddress;

  @SizeString(min = 60, max = 60)
  @Column(name = "NEW_PASSWORD", nullable = true, length = 60)
  protected String newPassword;

  @Column(name = "MAIL_SENT_DATETIME", nullable = false, columnDefinition = "timestamp with time zone")
  protected OffsetDateTime mailSentDatetime;

  // ID
  public static final String FIELD_ID = "id";
  public static final String FIELD_ACC_ID = "accId";
  public static final String FIELD_AUTH_KIND = "authKind";
  public static final String FIELD_AUTH_CODE = "authCode";
  public static final String FIELD_IS_VALID = "isValid";
  public static final String FIELD_HAS_USED = "hasUsed";
  public static final String FIELD_NEW_MAIL_ADDRESS = "newMailAddress";
  public static final String FIELD_NEW_PASSWORD = "newPassword";
  public static final String FIELD_MAIL_SENT_DATETIME = "mailSentDatetime";

  @Override
  public String[] getFieldNameArr() {
    return new String[] {"id", "accId", "authKind", "authCode", "isValid", "hasUsed", "newMailAddress", "newPassword", "mailSentDatetime", "createAccId", "createTime", "lstUpdAccId", "lstUpdTime", "delFlg", "version"};
  }

  /**defaultコンストラクタ */
  public MailAuthManagement() {}

  /** A constructor with record argument */
  public MailAuthManagement(MailAuthManagementBaseRecord rec, OffsetDateTime mailSentDatetime) {
    super(rec);

    if (rec.getId() != null) setId(rec.getIdOfEntityDataType());
    if (rec.getAccId() != null) setAccId(rec.getAccIdOfEntityDataType());
    if (rec.getAuthKind() != null) setAuthKind(rec.getAuthKindOfEntityDataType());
    if (rec.getAuthCode() != null) setAuthCode(rec.getAuthCode());
    if (rec.getIsValid() != null) setIsValid(rec.getIsValid());
    if (rec.getHasUsed() != null) setHasUsed(rec.getHasUsed());
    if (rec.getNewMailAddress() != null) setNewMailAddress(rec.getNewMailAddress());
    if (rec.getNewPassword() != null) setNewPassword(rec.getNewPassword());
    if (mailSentDatetime != null) setMailSentDatetime(mailSentDatetime);
  }

  public void update(MailAuthManagementBaseRecord rec, OffsetDateTime mailSentDatetime, String... skipUpdateFields) {
    List<String> skipUpdateFieldList = Arrays.asList(skipUpdateFields);

    if (rec.getId() != null && !skipUpdateFieldList.contains(FIELD_ID)) setId(rec.getIdOfEntityDataType());
    if (rec.getAccId() != null && !skipUpdateFieldList.contains(FIELD_ACC_ID)) setAccId(rec.getAccIdOfEntityDataType());
    if (rec.getAuthKind() != null && !skipUpdateFieldList.contains(FIELD_AUTH_KIND)) setAuthKind(rec.getAuthKindOfEntityDataType());
    if (rec.getAuthCode() != null && !skipUpdateFieldList.contains(FIELD_AUTH_CODE)) setAuthCode(rec.getAuthCode());
    if (rec.getIsValid() != null && !skipUpdateFieldList.contains(FIELD_IS_VALID)) setIsValid(rec.getIsValid());
    if (rec.getHasUsed() != null && !skipUpdateFieldList.contains(FIELD_HAS_USED)) setHasUsed(rec.getHasUsed());
    if (rec.getNewMailAddress() != null && !skipUpdateFieldList.contains(FIELD_NEW_MAIL_ADDRESS)) setNewMailAddress(rec.getNewMailAddress());
    if (rec.getNewPassword() != null && !skipUpdateFieldList.contains(FIELD_NEW_PASSWORD)) setNewPassword(rec.getNewPassword());
    if (mailSentDatetime != null && !skipUpdateFieldList.contains(FIELD_MAIL_SENT_DATETIME)) setMailSentDatetime(mailSentDatetime);
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

  public MailAuthKindEnum getAuthKind() {
    return authKind;
  }

  public void setAuthKind(MailAuthKindEnum authKind) {
    this.authKind = authKind;
  }

  public String getAuthCode() {
    return authCode;
  }

  public void setAuthCode(String authCode) {
    this.authCode = authCode;
  }

  public Boolean getIsValid() {
    return isValid;
  }

  public void setIsValid(Boolean isValid) {
    this.isValid = isValid;
  }

  public Boolean getHasUsed() {
    return hasUsed;
  }

  public void setHasUsed(Boolean hasUsed) {
    this.hasUsed = hasUsed;
  }

  public String getNewMailAddress() {
    return newMailAddress;
  }

  public void setNewMailAddress(String newMailAddress) {
    this.newMailAddress = newMailAddress;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public OffsetDateTime getMailSentDatetime() {
    return mailSentDatetime;
  }

  public void setMailSentDatetime(OffsetDateTime mailSentDatetime) {
    this.mailSentDatetime = mailSentDatetime;
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
