package jp.ecuacion.app.qiitadataviewer.base.bl;

import java.time.*;
import java.util.List;
import jp.ecuacion.app.qiitadataviewer.base.entity.*;
import jp.ecuacion.app.qiitadataviewer.base.record.*;
import jp.ecuacion.app.qiitadataviewer.base.repository.*;
import jp.ecuacion.splib.jpa.repository.SplibRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MailAuthManagementBaseBl extends SystemCommonBaseBl<MailAuthManagement, Long> {

  @Autowired
  protected MailAuthManagementBaseRepository repo;

  @Override
  public SplibRepository<MailAuthManagement, Long> getRepositoryForOptimisticLocking() {
    return repo;
  }

  public MailAuthManagement findAndOptimisticLockingCheck(MailAuthManagementBaseRecord rec) {
    return findAndOptimisticLockingCheck(rec.getIdOfEntityDataType(), rec.getVersionOfEntityDataType());
  }

  /** Is a utility to insert or update an entity. */
  public MailAuthManagement insertOrUpdate(MailAuthManagementBaseRecord rec, OffsetDateTime mailSentDatetime, String... skipUpdateFields) {
    MailAuthManagement e = null;
    boolean isInsert = rec.getId() == null || rec.getId().equals("");

    if (isInsert) {
      e = new MailAuthManagement(rec, mailSentDatetime);

    } else {
      e = findAndOptimisticLockingCheck(rec);
      e.update(rec, mailSentDatetime, skipUpdateFields);
    }

    return repo.save(e);
  }

  private void duplicateCheck(boolean isCheckFromAllGroups, List<MailAuthManagement> entityList, MailAuthManagementBaseRecord rec, String... targetItemPropertyPaths) {
    internalDuplicateCheck(isCheckFromAllGroups, entityList, rec, "mailAuthManagement", "id", targetItemPropertyPaths);
  }

  public void duplicateCheck(List<MailAuthManagement> entityList, MailAuthManagementBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(false, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheck(MailAuthManagementBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(repo.findAll(), rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(List<MailAuthManagement> entityList, MailAuthManagementBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(true, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(MailAuthManagementBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheckFromAllGroups(repo.findAllFromAllGroups(), rec, targetItemPropertyPaths);
  }

}
