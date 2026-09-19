package jp.ecuacion.app.qiitadataviewer.base.bl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import jp.ecuacion.app.qiitadataviewer.base.entity.*;
import jp.ecuacion.app.qiitadataviewer.base.record.*;
import jp.ecuacion.app.qiitadataviewer.base.repository.*;
import jp.ecuacion.splib.jpa.repository.SplibRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AccBaseBl extends SystemCommonBaseBl<Acc, Long> {

  @Autowired
  protected AccBaseRepository repo;

  @Autowired
  protected AccAdminBaseRepository accAdminRepo;

  @Autowired
  protected AccGeneralBaseRepository accGeneralRepo;

  @Autowired
  protected QiitaUserBaseRepository qiitaUserRepo;

  @Autowired
  protected QiitaGroupBaseRepository qiitaGroupRepo;

  @Autowired
  protected QiitaItemBaseRepository qiitaItemRepo;

  @Autowired
  protected QiitaTagBaseRepository qiitaTagRepo;

  @Autowired
  protected QiitaItemTagBaseRepository qiitaItemTagRepo;

  @Autowired
  protected QiitaItemTagVersionBaseRepository qiitaItemTagVersionRepo;

  @Override
  public SplibRepository<Acc, Long> getRepositoryForOptimisticLocking() {
    return repo;
  }

  public Acc findAndOptimisticLockingCheck(AccBaseRecord rec) {
    return findAndOptimisticLockingCheck(rec.getIdOfEntityDataType(), rec.getVersionOfEntityDataType());
  }

  /** Is a utility to insert or update an entity. */
  public Acc insertOrUpdate(AccBaseRecord rec, String... skipUpdateFields) {
    Acc e = null;
    boolean isInsert = rec.getId() == null || rec.getId().equals("");

    if (isInsert) {
      e = new Acc(rec);

    } else {
      e = findAndOptimisticLockingCheck(rec);
      e.update(rec, skipUpdateFields);
    }

    return repo.save(e);
  }

  private void duplicateCheck(boolean isCheckFromAllGroups, List<Acc> entityList, AccBaseRecord rec, String... targetItemPropertyPaths) {
    internalDuplicateCheck(isCheckFromAllGroups, entityList, rec, "acc", "id", targetItemPropertyPaths);
  }

  public void duplicateCheck(List<Acc> entityList, AccBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(false, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheck(AccBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(repo.findAll(), rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(List<Acc> entityList, AccBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(true, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(AccBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheckFromAllGroups(repo.findAllFromAllGroups(), rec, targetItemPropertyPaths);
  }

  public void naturalKeyDuplicateCheck(AccBaseRecord rec) {
    Optional<Acc> optional = repo.findByMailAddress(rec.getMailAddress());
    throwExceptionWhenDuplicated(optional.isPresent() && !optional.get().getId().equals(rec.getIdOfEntityDataType()), false, new String[] {"mailAddress"}, new String[] {rec.getItem("mailAddress").getItemNameKey()});
  }

  public void childExistenceCheckAccAdmin(Long id) {
    childExistenceCheckAccAdmin(id, (String) null);
  }

  public void childExistenceCheckAccAdmin(Long id, String messageId) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.accAdmin";
    internalChildExistenceCheck(accAdminRepo.findByAcc_Id(id), messageId, entityMsgIdPart);
  }

  public void childExistenceCheckAccAdmin(Long id, ChildExistenceCheckConditionBean... conditions) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.accAdmin";
    internalChildExistenceCheck(accAdminRepo.findByAcc_Id(id), entityMsgIdPart, conditions);
  }

  public void childExistenceCheckAccAdmin(AccBaseRecord rec, ChildExistenceCheckConditionBean[] conditions, String referingRecordDataLabel, String recordSpecifyingFieldName) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.accAdmin";
    internalChildExistenceCheck(accAdminRepo.findByAcc_Id(rec.getIdOfEntityDataType()), null, entityMsgIdPart, conditions, referingRecordDataLabel, recordSpecifyingFieldName);
  }

  public void childExistenceCheckAccGeneral(Long id) {
    childExistenceCheckAccGeneral(id, (String) null);
  }

  public void childExistenceCheckAccGeneral(Long id, String messageId) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.accGeneral";
    internalChildExistenceCheck(accGeneralRepo.findByAcc_Id(id), messageId, entityMsgIdPart);
  }

  public void childExistenceCheckAccGeneral(Long id, ChildExistenceCheckConditionBean... conditions) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.accGeneral";
    internalChildExistenceCheck(accGeneralRepo.findByAcc_Id(id), entityMsgIdPart, conditions);
  }

  public void childExistenceCheckAccGeneral(AccBaseRecord rec, ChildExistenceCheckConditionBean[] conditions, String referingRecordDataLabel, String recordSpecifyingFieldName) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.accGeneral";
    internalChildExistenceCheck(accGeneralRepo.findByAcc_Id(rec.getIdOfEntityDataType()), null, entityMsgIdPart, conditions, referingRecordDataLabel, recordSpecifyingFieldName);
  }

  public void childExistenceCheckQiitaUser(Long id) {
    childExistenceCheckQiitaUser(id, (String) null);
  }

  public void childExistenceCheckQiitaUser(Long id, String messageId) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaUser";
    internalChildExistenceCheck(qiitaUserRepo.findByAcc_Id(id), messageId, entityMsgIdPart);
  }

  public void childExistenceCheckQiitaUser(Long id, ChildExistenceCheckConditionBean... conditions) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaUser";
    internalChildExistenceCheck(qiitaUserRepo.findByAcc_Id(id), entityMsgIdPart, conditions);
  }

  public void childExistenceCheckQiitaUser(AccBaseRecord rec, ChildExistenceCheckConditionBean[] conditions, String referingRecordDataLabel, String recordSpecifyingFieldName) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaUser";
    internalChildExistenceCheck(qiitaUserRepo.findByAcc_Id(rec.getIdOfEntityDataType()), null, entityMsgIdPart, conditions, referingRecordDataLabel, recordSpecifyingFieldName);
  }

  public void childExistenceCheckQiitaGroup(Long id) {
    childExistenceCheckQiitaGroup(id, (String) null);
  }

  public void childExistenceCheckQiitaGroup(Long id, String messageId) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaGroup";
    internalChildExistenceCheck(qiitaGroupRepo.findByAcc_Id(id), messageId, entityMsgIdPart);
  }

  public void childExistenceCheckQiitaGroup(Long id, ChildExistenceCheckConditionBean... conditions) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaGroup";
    internalChildExistenceCheck(qiitaGroupRepo.findByAcc_Id(id), entityMsgIdPart, conditions);
  }

  public void childExistenceCheckQiitaGroup(AccBaseRecord rec, ChildExistenceCheckConditionBean[] conditions, String referingRecordDataLabel, String recordSpecifyingFieldName) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaGroup";
    internalChildExistenceCheck(qiitaGroupRepo.findByAcc_Id(rec.getIdOfEntityDataType()), null, entityMsgIdPart, conditions, referingRecordDataLabel, recordSpecifyingFieldName);
  }

  public void childExistenceCheckQiitaItem(Long id) {
    childExistenceCheckQiitaItem(id, (String) null);
  }

  public void childExistenceCheckQiitaItem(Long id, String messageId) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItem";
    internalChildExistenceCheck(qiitaItemRepo.findByAcc_Id(id), messageId, entityMsgIdPart);
  }

  public void childExistenceCheckQiitaItem(Long id, ChildExistenceCheckConditionBean... conditions) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItem";
    internalChildExistenceCheck(qiitaItemRepo.findByAcc_Id(id), entityMsgIdPart, conditions);
  }

  public void childExistenceCheckQiitaItem(AccBaseRecord rec, ChildExistenceCheckConditionBean[] conditions, String referingRecordDataLabel, String recordSpecifyingFieldName) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItem";
    internalChildExistenceCheck(qiitaItemRepo.findByAcc_Id(rec.getIdOfEntityDataType()), null, entityMsgIdPart, conditions, referingRecordDataLabel, recordSpecifyingFieldName);
  }

  public void childExistenceCheckQiitaTag(Long id) {
    childExistenceCheckQiitaTag(id, (String) null);
  }

  public void childExistenceCheckQiitaTag(Long id, String messageId) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaTag";
    internalChildExistenceCheck(qiitaTagRepo.findByAcc_Id(id), messageId, entityMsgIdPart);
  }

  public void childExistenceCheckQiitaTag(Long id, ChildExistenceCheckConditionBean... conditions) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaTag";
    internalChildExistenceCheck(qiitaTagRepo.findByAcc_Id(id), entityMsgIdPart, conditions);
  }

  public void childExistenceCheckQiitaTag(AccBaseRecord rec, ChildExistenceCheckConditionBean[] conditions, String referingRecordDataLabel, String recordSpecifyingFieldName) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaTag";
    internalChildExistenceCheck(qiitaTagRepo.findByAcc_Id(rec.getIdOfEntityDataType()), null, entityMsgIdPart, conditions, referingRecordDataLabel, recordSpecifyingFieldName);
  }

  public void childExistenceCheckQiitaItemTag(Long id) {
    childExistenceCheckQiitaItemTag(id, (String) null);
  }

  public void childExistenceCheckQiitaItemTag(Long id, String messageId) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItemTag";
    internalChildExistenceCheck(qiitaItemTagRepo.findByAcc_Id(id), messageId, entityMsgIdPart);
  }

  public void childExistenceCheckQiitaItemTag(Long id, ChildExistenceCheckConditionBean... conditions) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItemTag";
    internalChildExistenceCheck(qiitaItemTagRepo.findByAcc_Id(id), entityMsgIdPart, conditions);
  }

  public void childExistenceCheckQiitaItemTag(AccBaseRecord rec, ChildExistenceCheckConditionBean[] conditions, String referingRecordDataLabel, String recordSpecifyingFieldName) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItemTag";
    internalChildExistenceCheck(qiitaItemTagRepo.findByAcc_Id(rec.getIdOfEntityDataType()), null, entityMsgIdPart, conditions, referingRecordDataLabel, recordSpecifyingFieldName);
  }

  public void childExistenceCheckQiitaItemTagVersion(Long id) {
    childExistenceCheckQiitaItemTagVersion(id, (String) null);
  }

  public void childExistenceCheckQiitaItemTagVersion(Long id, String messageId) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItemTagVersion";
    internalChildExistenceCheck(qiitaItemTagVersionRepo.findByAcc_Id(id), messageId, entityMsgIdPart);
  }

  public void childExistenceCheckQiitaItemTagVersion(Long id, ChildExistenceCheckConditionBean... conditions) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItemTagVersion";
    internalChildExistenceCheck(qiitaItemTagVersionRepo.findByAcc_Id(id), entityMsgIdPart, conditions);
  }

  public void childExistenceCheckQiitaItemTagVersion(AccBaseRecord rec, ChildExistenceCheckConditionBean[] conditions, String referingRecordDataLabel, String recordSpecifyingFieldName) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItemTagVersion";
    internalChildExistenceCheck(qiitaItemTagVersionRepo.findByAcc_Id(rec.getIdOfEntityDataType()), null, entityMsgIdPart, conditions, referingRecordDataLabel, recordSpecifyingFieldName);
  }

  public void allChildrenExistenceChecks(Long id) {
    allChildrenExistenceChecks(id, null);
  }

  public void allChildrenExistenceChecks(Long id, String messageId, Class<?>... clses) {
    List<Class<?>> skipList = Arrays.asList(clses);

    if (!skipList.contains(AccAdmin.class)) childExistenceCheckAccAdmin(id, messageId);
    if (!skipList.contains(AccGeneral.class)) childExistenceCheckAccGeneral(id, messageId);
    if (!skipList.contains(QiitaUser.class)) childExistenceCheckQiitaUser(id, messageId);
    if (!skipList.contains(QiitaGroup.class)) childExistenceCheckQiitaGroup(id, messageId);
    if (!skipList.contains(QiitaItem.class)) childExistenceCheckQiitaItem(id, messageId);
    if (!skipList.contains(QiitaTag.class)) childExistenceCheckQiitaTag(id, messageId);
    if (!skipList.contains(QiitaItemTag.class)) childExistenceCheckQiitaItemTag(id, messageId);
    if (!skipList.contains(QiitaItemTagVersion.class)) childExistenceCheckQiitaItemTagVersion(id, messageId);
  }
}
