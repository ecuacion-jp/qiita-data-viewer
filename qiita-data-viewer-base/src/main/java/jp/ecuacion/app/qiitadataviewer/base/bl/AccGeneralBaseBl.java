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

public abstract class AccGeneralBaseBl extends SystemCommonBaseBl<AccGeneral, Long> {

  @Autowired
  protected AccGeneralBaseRepository repo;

  @Override
  public SplibRepository<AccGeneral, Long> getRepositoryForOptimisticLocking() {
    return repo;
  }

  public AccGeneral findAndOptimisticLockingCheck(AccGeneralBaseRecord rec) {
    return findAndOptimisticLockingCheck(rec.getAccIdOfEntityDataType(), rec.getVersionSnapshot() == null || rec.getVersionSnapshot().equals("") ? null : Long.valueOf(rec.getVersionSnapshot()));
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
