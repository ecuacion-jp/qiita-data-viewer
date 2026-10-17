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

public abstract class QiitaGroupBaseBl extends SystemCommonBaseBl<QiitaGroup, Long> {

  @Autowired
  protected QiitaGroupBaseRepository repo;

  @Autowired
  protected QiitaItemBaseRepository qiitaItemRepo;

  @Override
  public SplibRepository<QiitaGroup, Long> getRepositoryForOptimisticLocking() {
    return repo;
  }

  public QiitaGroup findAndOptimisticLockingCheck(QiitaGroupBaseRecord rec) {
    return findAndOptimisticLockingCheck(rec.getIdOfEntityDataType(), rec.getVersionOfEntityDataType());
  }

  /** Is a utility to insert or update an entity. */
  public QiitaGroup insertOrUpdate(QiitaGroupBaseRecord rec, OffsetDateTime createdAt, OffsetDateTime updatedAt, Acc acc, String... skipUpdateFields) {
    QiitaGroup e = null;
    boolean isInsert = rec.getId() == null || rec.getId().equals("");

    if (isInsert) {
      e = new QiitaGroup(rec, createdAt, updatedAt, acc);

    } else {
      e = findAndOptimisticLockingCheck(rec);
      e.update(rec, createdAt, updatedAt, acc, skipUpdateFields);
    }

    return repo.save(e);
  }

  private void duplicateCheck(boolean isCheckFromAllGroups, List<QiitaGroup> entityList, QiitaGroupBaseRecord rec, String... targetItemPropertyPaths) {
    internalDuplicateCheck(isCheckFromAllGroups, entityList, rec, "qiitaGroup", "id", targetItemPropertyPaths);
  }

  public void duplicateCheck(List<QiitaGroup> entityList, QiitaGroupBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(false, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheck(QiitaGroupBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(repo.findAll(), rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(List<QiitaGroup> entityList, QiitaGroupBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheck(true, entityList, rec, targetItemPropertyPaths);
  }

  public void duplicateCheckFromAllGroups(QiitaGroupBaseRecord rec, String... targetItemPropertyPaths) {
    duplicateCheckFromAllGroups(repo.findAllFromAllGroups(), rec, targetItemPropertyPaths);
  }

  public void naturalKeyDuplicateCheck(QiitaGroupBaseRecord rec) {
    Optional<QiitaGroup> optional = repo.findByUrlName(rec.getUrlName());
    throwExceptionWhenDuplicated(optional.isPresent() && !optional.get().getId().equals(rec.getIdOfEntityDataType()), false, new String[] {"urlName"}, new String[] {rec.getItem("urlName").getItemNameKey()});
  }

  public void childExistenceCheckQiitaItem(Long id) {
    childExistenceCheckQiitaItem(id, (String) null);
  }

  public void childExistenceCheckQiitaItem(Long id, String messageId) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItem";
    internalChildExistenceCheck(qiitaItemRepo.findByQiitaGroup_Id(id), messageId, entityMsgIdPart);
  }

  public void childExistenceCheckQiitaItem(Long id, ChildExistenceCheckConditionBean... conditions) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItem";
    internalChildExistenceCheck(qiitaItemRepo.findByQiitaGroup_Id(id), entityMsgIdPart, conditions);
  }

  public void childExistenceCheckQiitaItem(QiitaGroupBaseRecord rec, ChildExistenceCheckConditionBean[] conditions, String referingRecordDataLabel, String recordSpecifyingFieldName) {
    String entityMsgIdPart = "jp.ecuacion.splib.core.entity.qiitaItem";
    internalChildExistenceCheck(qiitaItemRepo.findByQiitaGroup_Id(rec.getIdOfEntityDataType()), null, entityMsgIdPart, conditions, referingRecordDataLabel, recordSpecifyingFieldName);
  }

  public void allChildrenExistenceChecks(Long id) {
    allChildrenExistenceChecks(id, null);
  }

  public void allChildrenExistenceChecks(Long id, String messageId, Class<?>... clses) {
    List<Class<?>> skipList = Arrays.asList(clses);

    if (!skipList.contains(QiitaItem.class)) childExistenceCheckQiitaItem(id, messageId);
  }
}
