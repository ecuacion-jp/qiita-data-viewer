package jp.ecuacion.app.qiitadataviewer.base.bl;

import java.util.List;
import jp.ecuacion.app.qiitadataviewer.base.entity.*;
import jp.ecuacion.app.qiitadataviewer.base.record.*;
import jp.ecuacion.app.qiitadataviewer.base.repository.*;
import jp.ecuacion.splib.jpa.repository.SplibRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AccAgreementBaseBl extends SystemCommonBaseBl<AccAgreement, Long> {

  @Autowired
  protected AccAgreementBaseRepository repo;

  @Override
  public SplibRepository<AccAgreement, Long> getRepositoryForOptimisticLocking() {
    return repo;
  }

  public AccAgreement findAndOptimisticLockingCheck(AccAgreementBaseRecord rec) {
    return findAndOptimisticLockingCheck(rec.getIdOfEntityDataType(), rec.getVersionOfEntityDataType());
  }

  /** Is a utility to insert or update an entity. */
  public AccAgreement insertOrUpdate(AccAgreementBaseRecord rec, String... skipUpdateFields) {
    AccAgreement e = null;
    boolean isInsert = rec.getId() == null || rec.getId().equals("");

    if (isInsert) {
      e = new AccAgreement(rec);

    } else {
      e = findAndOptimisticLockingCheck(rec);
      e.update(rec, skipUpdateFields);
    }

    return repo.save(e);
  }

  private void duplicateCheck(boolean isCheckFromAllGroups, List<AccAgreement> entityList, AccAgreementBaseRecord rec, String... targetItemPropertyPaths) {
    internalDuplicateCheck(isCheckFromAllGroups, entityList, rec, "accAgreement", "id", targetItemPropertyPaths);
  }

  public void duplicateCheck(List<AccAgreement> entityList, AccAgreementBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(false, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheck(AccAgreementBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(repo.findAll(), rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(List<AccAgreement> entityList, AccAgreementBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(true, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(AccAgreementBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheckFromAllGroups(repo.findAllFromAllGroups(), rec, targetItemPropertyPaths);
  }

}
