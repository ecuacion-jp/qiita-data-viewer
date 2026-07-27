/*
 * Copyright © 2012 ecuacion.jp (info@ecuacion.jp)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.ecuacion.app.qiitadataviewer.base.bl;

import java.util.List;
import jp.ecuacion.app.qiitadataviewer.base.entity.*;
import jp.ecuacion.app.qiitadataviewer.base.record.*;
import jp.ecuacion.app.qiitadataviewer.base.repository.*;
import jp.ecuacion.splib.jpa.repository.SplibRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AccAdminBaseBl extends SystemCommonBaseBl<AccAdmin, Long> {

  @Autowired
  protected AccAdminBaseRepository repo;

  @Override
  public SplibRepository<AccAdmin, Long> getRepositoryForOptimisticLocking() {
    return repo;
  }

  public AccAdmin findAndOptimisticLockingCheck(AccAdminBaseRecord rec) {
    return findAndOptimisticLockingCheck(rec.getAccIdOfEntityDataType(), rec.getVersionOfEntityDataType());
  }

  /** Is a utility to insert or update an entity. */
  public AccAdmin insertOrUpdate(AccAdminBaseRecord rec, Acc acc, String... skipUpdateFields) {
    AccAdmin e = null;
    boolean isInsert = rec.getAccId() == null || rec.getAccId().equals("");

    if (isInsert) {
      e = new AccAdmin(rec, acc);

    } else {
      e = findAndOptimisticLockingCheck(rec);
      e.update(rec, acc, skipUpdateFields);
    }

    return repo.save(e);
  }

  private void duplicateCheck(boolean isCheckFromAllGroups, List<AccAdmin> entityList, AccAdminBaseRecord rec, String... targetItemPropertyPaths) {
    internalDuplicateCheck(isCheckFromAllGroups, entityList, rec, "accAdmin", "id", targetItemPropertyPaths);
  }

  public void duplicateCheck(List<AccAdmin> entityList, AccAdminBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(false, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheck(AccAdminBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(repo.findAll(), rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(List<AccAdmin> entityList, AccAdminBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(true, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(AccAdminBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheckFromAllGroups(repo.findAllFromAllGroups(), rec, targetItemPropertyPaths);
  }

}
