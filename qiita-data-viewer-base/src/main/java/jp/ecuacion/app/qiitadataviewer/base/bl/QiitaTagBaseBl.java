package jp.ecuacion.app.qiitadataviewer.base.bl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import jp.ecuacion.app.qiitadataviewer.base.entity.*;
import jp.ecuacion.app.qiitadataviewer.base.record.*;
import jp.ecuacion.app.qiitadataviewer.base.repository.*;
import jp.ecuacion.splib.jpa.repository.SplibRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class QiitaTagBaseBl extends SystemCommonBaseBl<QiitaTag, Long> {

  @Autowired
  protected QiitaTagBaseRepository repo;

  @Autowired
  protected QiitaItemTagBaseRepository qiitaItemTagRepo;

  @Override
  public SplibRepository<QiitaTag, Long> getRepositoryForOptimisticLocking() {
    return repo;
  }

  public QiitaTag findAndOptimisticLockingCheck(QiitaTagBaseRecord rec) {
    return findAndOptimisticLockingCheck(rec.getIdOfEntityDataType(), rec.getVersionOfEntityDataType());
  }

  /** Is a utility to insert or update an entity. */
  public QiitaTag insertOrUpdate(QiitaTagBaseRecord rec, Acc acc, String... skipUpdateFields) {
    QiitaTag e = null;
    boolean isInsert = rec.getId() == null || rec.getId().equals("");

    if (isInsert) {
      e = new QiitaTag(rec, acc);

    } else {
      e = findAndOptimisticLockingCheck(rec);
      e.update(rec, acc, skipUpdateFields);
    }

    return repo.save(e);
  }

  private void duplicateCheck(boolean isCheckFromAllGroups, List<QiitaTag> entityList, QiitaTagBaseRecord rec, String... targetItemPropertyPaths) {
    internalDuplicateCheck(isCheckFromAllGroups, entityList, rec, "qiitaTag", "id", targetItemPropertyPaths);
  }

  public void duplicateCheck(List<QiitaTag> entityList, QiitaTagBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(false, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheck(QiitaTagBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(repo.findAll(), rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(List<QiitaTag> entityList, QiitaTagBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(true, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(QiitaTagBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheckFromAllGroups(repo.findAllFromAllGroups(), rec, targetItemPropertyPaths);
  }

  public void naturalKeyDuplicateCheck(QiitaTagBaseRecord rec) {
    Optional<QiitaTag> optional = repo.findByName(rec.getName());
    throwExceptionWhenDuplicated(optional.isPresent() && !optional.get().getId().equals(rec.getIdOfEntityDataType()), false, new String[] {"name"}, new String[] {rec.getItem("name").getItemNameKey()});
  }

  public void childExistenceCheckQiitaItemTag(Long id) {
    childExistenceCheckQiitaItemTag(id, (String) null);
  }

  public void childExistenceCheckQiitaItemTag(Long id, String messageId) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItemTag";
    internalChildExistenceCheck(qiitaItemTagRepo.findByQiitaTag_Id(id), messageId, entityMsgIdPart);
  }

  public void childExistenceCheckQiitaItemTag(Long id, ChildExistenceCheckConditionBean... conditions) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItemTag";
    internalChildExistenceCheck(qiitaItemTagRepo.findByQiitaTag_Id(id), entityMsgIdPart, conditions);
  }

  public void childExistenceCheckQiitaItemTag(QiitaTagBaseRecord rec, ChildExistenceCheckConditionBean[] conditions, String referingRecordDataLabel, String recordSpecifyingFieldName) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItemTag";
    internalChildExistenceCheck(qiitaItemTagRepo.findByQiitaTag_Id(rec.getIdOfEntityDataType()), null, entityMsgIdPart, conditions, referingRecordDataLabel, recordSpecifyingFieldName);
  }

  public void allChildrenExistenceChecks(Long id) {
    allChildrenExistenceChecks(id, null);
  }

  public void allChildrenExistenceChecks(Long id, String messageId, Class<?>... clses) {
    List<Class<?>> skipList = Arrays.asList(clses);

    if (!skipList.contains(QiitaItemTag.class)) childExistenceCheckQiitaItemTag(id, messageId);
  }
}
