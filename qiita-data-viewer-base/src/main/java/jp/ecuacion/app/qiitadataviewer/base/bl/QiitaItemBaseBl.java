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

import java.time.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import jp.ecuacion.app.qiitadataviewer.base.entity.*;
import jp.ecuacion.app.qiitadataviewer.base.record.*;
import jp.ecuacion.app.qiitadataviewer.base.repository.*;
import jp.ecuacion.splib.jpa.repository.SplibRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class QiitaItemBaseBl extends SystemCommonBaseBl<QiitaItem, Long> {

  @Autowired
  protected QiitaItemBaseRepository repo;

  @Autowired
  protected QiitaItemTagBaseRepository qiitaItemTagRepo;

  @Override
  public SplibRepository<QiitaItem, Long> getRepositoryForOptimisticLocking() {
    return repo;
  }

  public QiitaItem findAndOptimisticLockingCheck(QiitaItemBaseRecord rec) {
    return findAndOptimisticLockingCheck(rec.getIdOfEntityDataType(), rec.getVersionSnapshot() == null || rec.getVersionSnapshot().equals("") ? null : Long.valueOf(rec.getVersionSnapshot()));
  }

  /** Is a utility to insert or update an entity. */
  public QiitaItem insertOrUpdate(QiitaItemBaseRecord rec, OffsetDateTime createdAt, OffsetDateTime updatedAt, Acc acc, QiitaGroup qiitaGroup, QiitaUser qiitaUser, String... skipUpdateFields) {
    QiitaItem e = null;
    boolean isInsert = rec.getId() == null || rec.getId().equals("");

    if (isInsert) {
      e = new QiitaItem(rec, createdAt, updatedAt, acc, qiitaGroup, qiitaUser);

    } else {
      e = findAndOptimisticLockingCheck(rec);
      e.update(rec, createdAt, updatedAt, acc, qiitaGroup, qiitaUser, skipUpdateFields);
    }

    return repo.save(e);
  }

  private void duplicateCheck(boolean isCheckFromAllGroups, List<QiitaItem> entityList, QiitaItemBaseRecord rec, String... targetItemPropertyPaths) {
    internalDuplicateCheck(isCheckFromAllGroups, entityList, rec, "qiitaItem", "id", targetItemPropertyPaths);
  }

  public void duplicateCheck(List<QiitaItem> entityList, QiitaItemBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(false, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheck(QiitaItemBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(repo.findAll(), rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(List<QiitaItem> entityList, QiitaItemBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(true, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(QiitaItemBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheckFromAllGroups(repo.findAllFromAllGroups(), rec, targetItemPropertyPaths);
  }

  public void naturalKeyDuplicateCheck(QiitaItemBaseRecord rec) {
    Optional<QiitaItem> optional = repo.findByItemIdInQiitaWebsite(rec.getItemIdInQiitaWebsite());
    throwExceptionWhenDuplicated(optional.isPresent() && !optional.get().getId().equals(rec.getIdOfEntityDataType()), false, new String[] {"itemIdInQiitaWebsite"}, new String[] {rec.getItem("itemIdInQiitaWebsite").getItemNameKey()});
  }

  public void childExistenceCheckQiitaItemTag(Long id) {
    childExistenceCheckQiitaItemTag(id, (String) null);
  }

  public void childExistenceCheckQiitaItemTag(Long id, String messageId) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItemTag";
    internalChildExistenceCheck(qiitaItemTagRepo.findByQiitaItem_Id(id), messageId, entityMsgIdPart);
  }

  public void childExistenceCheckQiitaItemTag(Long id, ChildExistenceCheckConditionBean... conditions) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItemTag";
    internalChildExistenceCheck(qiitaItemTagRepo.findByQiitaItem_Id(id), entityMsgIdPart, conditions);
  }

  public void childExistenceCheckQiitaItemTag(QiitaItemBaseRecord rec, ChildExistenceCheckConditionBean[] conditions, String referingRecordDataLabel, String recordSpecifyingFieldName) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItemTag";
    internalChildExistenceCheck(qiitaItemTagRepo.findByQiitaItem_Id(rec.getIdOfEntityDataType()), null, entityMsgIdPart, conditions, referingRecordDataLabel, recordSpecifyingFieldName);
  }

  public void allChildrenExistenceChecks(Long id) {
    allChildrenExistenceChecks(id, null);
  }

  public void allChildrenExistenceChecks(Long id, String messageId, Class<?>... clses) {
    List<Class<?>> skipList = Arrays.asList(clses);

    if (!skipList.contains(QiitaItemTag.class)) childExistenceCheckQiitaItemTag(id, messageId);
  }
}
