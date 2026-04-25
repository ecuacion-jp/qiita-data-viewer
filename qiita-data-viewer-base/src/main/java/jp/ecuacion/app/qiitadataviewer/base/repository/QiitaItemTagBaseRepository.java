package jp.ecuacion.app.qiitadataviewer.base.repository;

import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaItemTag;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface QiitaItemTagBaseRepository extends SystemCommonBaseRepository<QiitaItemTag, Long>, JpaSpecificationExecutor<QiitaItemTag> {

  /** Is defined with jpql because hibernate filter does not take effect to spring data jpa standard 'findById'. */
  @Query(value = "from QiitaItemTag where id = :id")
  Optional<QiitaItemTag> findById(Long id);

  /** Finds by natural key. */
  Optional<QiitaItemTag> findByQiitaItem_IdAndQiitaTag_Id(
      Long qiitaItemId, Long qiitaTagId);

  /** Is generated for existence check when a parent record is deleted. */
  public List<QiitaItemTag> findByAcc_Id(Long id);

  /** Is generated for existence check when a parent record is deleted. */
  public List<QiitaItemTag> findByQiitaItem_Id(Long id);

  /** Is generated for existence check when a parent record is deleted. */
  public List<QiitaItemTag> findByQiitaTag_Id(Long id);

  @Query(nativeQuery = true, value = "select * from Instance where del_flg = false")
  public List<QiitaItemTag> findAllFromAllGroups();

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Query(nativeQuery = true, 
      value = "select * from QIITA_ITEM_TAG where ID = :#{#entity.id} and del_flg = true")
  Optional<QiitaItemTag> findByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") QiitaItemTag entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Query(nativeQuery = true, 
      value = "select * from QIITA_ITEM_TAG where qiita_item_id = :#{#entity.qiitaItemId} and qiita_tag_id = :#{#entity.qiitaTagId} and del_flg = true")
  Optional<QiitaItemTag> findByNaturalKeyAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") QiitaItemTag entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Modifying
  @Query(nativeQuery = true, 
      value = "delete from QIITA_ITEM_TAG where ID = :#{#entity.id} and del_flg = true")
  void deleteByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") QiitaItemTag entity);

}
