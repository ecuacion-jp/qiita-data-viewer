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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import jp.ecuacion.app.qiitadataviewer.base.entity.*;
import jp.ecuacion.app.qiitadataviewer.base.record.*;
import jp.ecuacion.app.qiitadataviewer.base.repository.*;
import jp.ecuacion.splib.jpa.repository.SplibRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class QiitaItemTagBaseBl extends SystemCommonBaseBl<QiitaItemTag, Long> {

  @Autowired
  protected QiitaItemTagBaseRepository repo;

  @Autowired
  protected QiitaItemTagVersionBaseRepository qiitaItemTagVersionRepo;

  @Override
  public SplibRepository<QiitaItemTag, Long> getRepositoryForOptimisticLocking() {
    return repo;
  }

  public QiitaItemTag findAndOptimisticLockingCheck(QiitaItemTagBaseRecord rec) {
    return findAndOptimisticLockingCheck(rec.getIdOfEntityDataType(), rec.getVersionSnapshot() == null || rec.getVersionSnapshot().equals("") ? null : Long.valueOf(rec.getVersionSnapshot()));
  }

  /** Is a utility to insert or update an entity. */
  public QiitaItemTag insertOrUpdate(QiitaItemTagBaseRecord rec, Acc acc, QiitaItem qiitaItem, QiitaTag qiitaTag, String... skipUpdateFields) {
    QiitaItemTag e = null;
    boolean isInsert = rec.getId() == null || rec.getId().equals("");

    if (isInsert) {
      e = new QiitaItemTag(rec, acc, qiitaItem, qiitaTag);

    } else {
      e = findAndOptimisticLockingCheck(rec);
      e.update(rec, acc, qiitaItem, qiitaTag, skipUpdateFields);
    }

    return repo.save(e);
  }

  private void duplicateCheck(boolean isCheckFromAllGroups, List<QiitaItemTag> entityList, QiitaItemTagBaseRecord rec, String... targetItemPropertyPaths) {
    internalDuplicateCheck(isCheckFromAllGroups, entityList, rec, "qiitaItemTag", "id", targetItemPropertyPaths);
  }

  public void duplicateCheck(List<QiitaItemTag> entityList, QiitaItemTagBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(false, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheck(QiitaItemTagBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(repo.findAll(), rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(List<QiitaItemTag> entityList, QiitaItemTagBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(true, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(QiitaItemTagBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheckFromAllGroups(repo.findAllFromAllGroups(), rec, targetItemPropertyPaths);
  }

  public void naturalKeyDuplicateCheck(QiitaItemTagBaseRecord rec) {
    Optional<QiitaItemTag> optional = repo.findByQiitaItem_IdAndQiitaTag_Id(rec.getQiitaItem().getIdOfEntityDataType(), rec.getQiitaTag().getIdOfEntityDataType());
    throwExceptionWhenDuplicated(optional.isPresent() && !optional.get().getId().equals(rec.getIdOfEntityDataType()), false, new String[] {"qiitaItem.id", "qiitaTag.id"}, new String[] {rec.getItem("qiitaItem.id").getItemNameKey(), rec.getItem("qiitaTag.id").getItemNameKey()});
  }

  public void childExistenceCheckQiitaItemTagVersion(Long id) {
    childExistenceCheckQiitaItemTagVersion(id, (String) null);
  }

  public void childExistenceCheckQiitaItemTagVersion(Long id, String messageId) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItemTagVersion";
    internalChildExistenceCheck(qiitaItemTagVersionRepo.findByQiitaItemTag_Id(id), messageId, entityMsgIdPart);
  }

  public void childExistenceCheckQiitaItemTagVersion(Long id, ChildExistenceCheckConditionBean... conditions) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItemTagVersion";
    internalChildExistenceCheck(qiitaItemTagVersionRepo.findByQiitaItemTag_Id(id), entityMsgIdPart, conditions);
  }

  public void childExistenceCheckQiitaItemTagVersion(QiitaItemTagBaseRecord rec, ChildExistenceCheckConditionBean[] conditions, String referingRecordDataLabel, String recordSpecifyingFieldName) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItemTagVersion";
    internalChildExistenceCheck(qiitaItemTagVersionRepo.findByQiitaItemTag_Id(rec.getIdOfEntityDataType()), null, entityMsgIdPart, conditions, referingRecordDataLabel, recordSpecifyingFieldName);
  }

  public void allChildrenExistenceChecks(Long id) {
    allChildrenExistenceChecks(id, null);
  }

  public void allChildrenExistenceChecks(Long id, String messageId, Class<?>... clses) {
    List<Class<?>> skipList = Arrays.asList(clses);

    if (!skipList.contains(QiitaItemTagVersion.class)) childExistenceCheckQiitaItemTagVersion(id, messageId);
  }
}
