package jp.ecuacion.app.qiitadataviewer.base.repository;

import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaItemTagVersion;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface QiitaItemTagVersionBaseRepository extends SystemCommonBaseRepository<QiitaItemTagVersion, Long>, JpaSpecificationExecutor<QiitaItemTagVersion> {

  /** Is defined with jpql because hibernate filter does not take effect to spring data jpa standard 'findById'. */
  @Query(value = "from QiitaItemTagVersion where id = :id")
  Optional<QiitaItemTagVersion> findById(Long id);

  /** Is generated for existence check when a parent record is deleted. */
  public List<QiitaItemTagVersion> findByAcc_Id(Long id);

  /** Is generated for existence check when a parent record is deleted. */
  public List<QiitaItemTagVersion> findByQiitaItemTag_Id(Long id);

  @Query(nativeQuery = true, value = "select * from Instance where del_flg = false")
  public List<QiitaItemTagVersion> findAllFromAllGroups();

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Query(nativeQuery = true, 
      value = "select * from QIITA_ITEM_TAG_VERSION where ID = :#{#entity.id} and del_flg = true")
  Optional<QiitaItemTagVersion> findByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") QiitaItemTagVersion entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records.
  The entity doesn't have a natural key. Unsatisfied condition is used in the where clause. It not called from library. */
  @Query(nativeQuery = true, 
      value = "select * from QIITA_ITEM_TAG_VERSION where 1 = 2 and del_flg = true")
  Optional<QiitaItemTagVersion> findByNaturalKeyAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") QiitaItemTagVersion entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Modifying
  @Query(nativeQuery = true, 
      value = "delete from QIITA_ITEM_TAG_VERSION where ID = :#{#entity.id} and del_flg = true")
  void deleteByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") QiitaItemTagVersion entity);

}
