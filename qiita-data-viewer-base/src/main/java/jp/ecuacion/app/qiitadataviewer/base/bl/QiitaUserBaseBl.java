package jp.ecuacion.app.qiitadataviewer.base.bl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import jp.ecuacion.app.qiitadataviewer.base.entity.*;
import jp.ecuacion.app.qiitadataviewer.base.record.*;
import jp.ecuacion.app.qiitadataviewer.base.repository.*;
import jp.ecuacion.splib.jpa.repository.SplibRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class QiitaUserBaseBl extends SystemCommonBaseBl<QiitaUser, Long> {

  @Autowired
  protected QiitaUserBaseRepository repo;

  @Autowired
  protected QiitaItemBaseRepository qiitaItemRepo;

  @Override
  public SplibRepository<QiitaUser, Long> getRepositoryForOptimisticLocking() {
    return repo;
  }

  public QiitaUser findAndOptimisticLockingCheck(QiitaUserBaseRecord rec) {
    return findAndOptimisticLockingCheck(rec.getIdOfEntityDataType(), rec.getVersionOfEntityDataType());
  }

  /** Is a utility to insert or update an entity. */
  public QiitaUser insertOrUpdate(QiitaUserBaseRecord rec, Acc acc, String... skipUpdateFields) {
    QiitaUser e = null;
    boolean isInsert = rec.getId() == null || rec.getId().equals("");

    if (isInsert) {
      e = new QiitaUser(rec, acc);

    } else {
      e = findAndOptimisticLockingCheck(rec);
      e.update(rec, acc, skipUpdateFields);
    }

    return repo.save(e);
  }

  private void duplicateCheck(boolean isCheckFromAllGroups, List<QiitaUser> entityList, QiitaUserBaseRecord rec, String... targetItemPropertyPaths) {
    internalDuplicateCheck(isCheckFromAllGroups, entityList, rec, "qiitaUser", "id", targetItemPropertyPaths);
  }

  public void duplicateCheck(List<QiitaUser> entityList, QiitaUserBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(false, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheck(QiitaUserBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(repo.findAll(), rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(List<QiitaUser> entityList, QiitaUserBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(true, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(QiitaUserBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheckFromAllGroups(repo.findAllFromAllGroups(), rec, targetItemPropertyPaths);
  }

  public void naturalKeyDuplicateCheck(QiitaUserBaseRecord rec) {
    Optional<QiitaUser> optional = repo.findByUserIdInQiitaWebsite(rec.getUserIdInQiitaWebsite());
    throwExceptionWhenDuplicated(optional.isPresent() && !optional.get().getId().equals(rec.getIdOfEntityDataType()), false, new String[] {"userIdInQiitaWebsite"}, new String[] {rec.getItem("userIdInQiitaWebsite").getItemNameKey()});
  }

  public void childExistenceCheckQiitaItem(Long id) {
    childExistenceCheckQiitaItem(id, (String) null);
  }

  public void childExistenceCheckQiitaItem(Long id, String messageId) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItem";
    internalChildExistenceCheck(qiitaItemRepo.findByQiitaUser_Id(id), messageId, entityMsgIdPart);
  }

  public void childExistenceCheckQiitaItem(Long id, ChildExistenceCheckConditionBean... conditions) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItem";
    internalChildExistenceCheck(qiitaItemRepo.findByQiitaUser_Id(id), entityMsgIdPart, conditions);
  }

  public void childExistenceCheckQiitaItem(QiitaUserBaseRecord rec, ChildExistenceCheckConditionBean[] conditions, String referingRecordDataLabel, String recordSpecifyingFieldName) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItem";
    internalChildExistenceCheck(qiitaItemRepo.findByQiitaUser_Id(rec.getIdOfEntityDataType()), null, entityMsgIdPart, conditions, referingRecordDataLabel, recordSpecifyingFieldName);
  }

  public void allChildrenExistenceChecks(Long id) {
    allChildrenExistenceChecks(id, null);
  }

  public void allChildrenExistenceChecks(Long id, String messageId, Class<?>... clses) {
    List<Class<?>> skipList = Arrays.asList(clses);

    if (!skipList.contains(QiitaItem.class)) childExistenceCheckQiitaItem(id, messageId);
  }
}
