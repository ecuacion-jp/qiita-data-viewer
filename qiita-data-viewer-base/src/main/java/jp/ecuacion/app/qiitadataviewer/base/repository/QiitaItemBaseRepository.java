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
package jp.ecuacion.app.qiitadataviewer.base.repository;

import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface QiitaItemBaseRepository extends SystemCommonBaseRepository<QiitaItem, Long>, JpaSpecificationExecutor<QiitaItem> {

  /** Is defined with jpql because hibernate filter does not take effect to spring data jpa standard 'findById'. */
  @Query(value = "from QiitaItem where id = :id")
  Optional<QiitaItem> findById(Long id);

  /** Finds by natural key. */
  Optional<QiitaItem> findByItemIdInQiitaWebsite(
      String itemIdInQiitaWebsite);

  /** Is generated for existence check when a parent record is deleted. */
  public List<QiitaItem> findByAcc_Id(Long id);

  /** Is generated for existence check when a parent record is deleted. */
  public List<QiitaItem> findByQiitaGroup_Id(Long id);

  /** Is generated for existence check when a parent record is deleted. */
  public List<QiitaItem> findByQiitaUser_Id(Long id);

  @Query(nativeQuery = true, value = "select * from Instance where del_flg = false")
  public List<QiitaItem> findAllFromAllGroups();

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Query(nativeQuery = true, 
      value = "select * from QIITA_ITEM where ID = :#{#entity.id} and del_flg = true")
  Optional<QiitaItem> findByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") QiitaItem entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Query(nativeQuery = true, 
      value = "select * from QIITA_ITEM where item_id_in_qiita_website = :#{#entity.itemIdInQiitaWebsite} and del_flg = true")
  Optional<QiitaItem> findByNaturalKeyAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") QiitaItem entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Modifying
  @Query(nativeQuery = true, 
      value = "delete from QIITA_ITEM where ID = :#{#entity.id} and del_flg = true")
  void deleteByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") QiitaItem entity);

}
