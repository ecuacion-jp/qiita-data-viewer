package jp.ecuacion.app.qiitadataviewer.base.bl;

import java.util.List;
import jp.ecuacion.app.qiitadataviewer.base.entity.*;
import jp.ecuacion.app.qiitadataviewer.base.record.*;
import jp.ecuacion.app.qiitadataviewer.base.repository.*;
import jp.ecuacion.splib.jpa.repository.SplibRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class QiitaItemTagVersionBaseBl extends SystemCommonBaseBl<QiitaItemTagVersion, Long> {

  @Autowired
  protected QiitaItemTagVersionBaseRepository repo;

  @Override
  public SplibRepository<QiitaItemTagVersion, Long> getRepositoryForOptimisticLocking() {
    return repo;
  }

  public QiitaItemTagVersion findAndOptimisticLockingCheck(QiitaItemTagVersionBaseRecord rec) {
    return findAndOptimisticLockingCheck(rec.getIdOfEntityDataType(), rec.getVersionOfEntityDataType());
  }

  /** Is a utility to insert or update an entity. */
  public QiitaItemTagVersion insertOrUpdate(QiitaItemTagVersionBaseRecord rec, Acc acc, QiitaItemTag qiitaItemTag, String... skipUpdateFields) {
    QiitaItemTagVersion e = null;
    boolean isInsert = rec.getId() == null || rec.getId().equals("");

    if (isInsert) {
      e = new QiitaItemTagVersion(rec, acc, qiitaItemTag);

    } else {
      e = findAndOptimisticLockingCheck(rec);
      e.update(rec, acc, qiitaItemTag, skipUpdateFields);
    }

    return repo.save(e);
  }

  private void duplicateCheck(boolean isCheckFromAllGroups, List<QiitaItemTagVersion> entityList, QiitaItemTagVersionBaseRecord rec, String... targetItemPropertyPaths) {
    internalDuplicateCheck(isCheckFromAllGroups, entityList, rec, "qiitaItemTagVersion", "id", targetItemPropertyPaths);
  }

  public void duplicateCheck(List<QiitaItemTagVersion> entityList, QiitaItemTagVersionBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(false, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheck(QiitaItemTagVersionBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(repo.findAll(), rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(List<QiitaItemTagVersion> entityList, QiitaItemTagVersionBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(true, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(QiitaItemTagVersionBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheckFromAllGroups(repo.findAllFromAllGroups(), rec, targetItemPropertyPaths);
  }

}
