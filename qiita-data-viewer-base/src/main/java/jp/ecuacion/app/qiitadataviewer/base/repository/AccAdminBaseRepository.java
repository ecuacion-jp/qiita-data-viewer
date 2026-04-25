package jp.ecuacion.app.qiitadataviewer.base.repository;

import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.entity.AccAdmin;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface AccAdminBaseRepository extends SystemCommonBaseRepository<AccAdmin, Long>, JpaSpecificationExecutor<AccAdmin> {

  /** Is defined with jpql because hibernate filter does not take effect to spring data jpa standard 'findById'. */
  @Query(value = "from AccAdmin where accId = :id")
  Optional<AccAdmin> findById(Long id);

  /** Is generated for existence check when a parent record is deleted. */
  public Optional<AccAdmin> findByAcc_Id(Long id);

  @Query(nativeQuery = true, value = "select * from Instance where del_flg = false")
  public List<AccAdmin> findAllFromAllGroups();

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Query(nativeQuery = true, 
      value = "select * from ACC_ADMIN where ACC_ID = :#{#entity.accId} and del_flg = true")
  Optional<AccAdmin> findByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") AccAdmin entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records.
  The entity doesn't have a natural key. Unsatisfied condition is used in the where clause. It not called from library. */
  @Query(nativeQuery = true, 
      value = "select * from ACC_ADMIN where 1 = 2 and del_flg = true")
  Optional<AccAdmin> findByNaturalKeyAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") AccAdmin entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Modifying
  @Query(nativeQuery = true, 
      value = "delete from ACC_ADMIN where ACC_ID = :#{#entity.accId} and del_flg = true")
  void deleteByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") AccAdmin entity);

}
