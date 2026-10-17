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
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface QiitaGroupBaseRepository extends SystemCommonBaseRepository<QiitaGroup, Long>, JpaSpecificationExecutor<QiitaGroup> {

  /** Is defined with jpql because hibernate filter does not take effect to spring data jpa standard 'findById'. */
  @Query(value = "from QiitaGroup where id = :id")
  Optional<QiitaGroup> findById(Long id);

  /** Finds by natural key. */
  Optional<QiitaGroup> findByUrlName(
      String urlName);

  /** Is generated for existence check when a parent record is deleted. */
  public List<QiitaGroup> findByAcc_Id(Long id);

  @Query(nativeQuery = true, value = "select * from Instance where del_flg = false")
  public List<QiitaGroup> findAllFromAllGroups();

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Query(nativeQuery = true, 
      value = "select * from QIITA_GROUP where ID = :#{#entity.id} and del_flg = true")
  Optional<QiitaGroup> findByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") QiitaGroup entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Query(nativeQuery = true, 
      value = "select * from QIITA_GROUP where url_name = :#{#entity.urlName} and del_flg = true")
  Optional<QiitaGroup> findByNaturalKeyAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") QiitaGroup entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Modifying
  @Query(nativeQuery = true, 
      value = "delete from QIITA_GROUP where ID = :#{#entity.id} and del_flg = true")
  void deleteByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") QiitaGroup entity);

}
