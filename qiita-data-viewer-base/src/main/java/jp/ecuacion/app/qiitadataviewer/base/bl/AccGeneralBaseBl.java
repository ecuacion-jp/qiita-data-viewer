package jp.ecuacion.app.qiitadataviewer.base.bl;

import java.util.List;
import jp.ecuacion.app.qiitadataviewer.base.entity.*;
import jp.ecuacion.app.qiitadataviewer.base.record.*;
import jp.ecuacion.app.qiitadataviewer.base.repository.*;
import jp.ecuacion.splib.jpa.repository.SplibRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AccGeneralBaseBl extends SystemCommonBaseBl<AccGeneral, Long> {

  @Autowired
  protected AccGeneralBaseRepository repo;

  @Override
  public SplibRepository<AccGeneral, Long> getRepositoryForOptimisticLocking() {
    return repo;
  }

  public AccGeneral findAndOptimisticLockingCheck(AccGeneralBaseRecord rec) {
    return findAndOptimisticLockingCheck(rec.getAccIdOfEntityDataType(), rec.getVersionOfEntityDataType());
  }

  /** Is a utility to insert or update an entity. */
  public AccGeneral insertOrUpdate(AccGeneralBaseRecord rec, Acc acc, String... skipUpdateFields) {
    AccGeneral e = null;
    boolean isInsert = rec.getAccId() == null || rec.getAccId().equals("");

    if (isInsert) {
      e = new AccGeneral(rec, acc);

    } else {
      e = findAndOptimisticLockingCheck(rec);
      e.update(rec, acc, skipUpdateFields);
    }

    return repo.save(e);
  }

  private void duplicateCheck(boolean isCheckFromAllGroups, List<AccGeneral> entityList, AccGeneralBaseRecord rec, String... targetItemPropertyPaths) {
    internalDuplicateCheck(isCheckFromAllGroups, entityList, rec, "accGeneral", "id", targetItemPropertyPaths);
  }

  public void duplicateCheck(List<AccGeneral> entityList, AccGeneralBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(false, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheck(AccGeneralBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(repo.findAll(), rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(List<AccGeneral> entityList, AccGeneralBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(true, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(AccGeneralBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheckFromAllGroups(repo.findAllFromAllGroups(), rec, targetItemPropertyPaths);
  }

}
