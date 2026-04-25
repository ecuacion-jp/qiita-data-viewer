package jp.ecuacion.app.qiitadataviewer.base.repository;

import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.entity.AccAgreement;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface AccAgreementBaseRepository extends SystemCommonBaseRepository<AccAgreement, Long>, JpaSpecificationExecutor<AccAgreement> {

  /** Is defined with jpql because hibernate filter does not take effect to spring data jpa standard 'findById'. */
  @Query(value = "from AccAgreement where id = :id")
  Optional<AccAgreement> findById(Long id);

  @Query(nativeQuery = true, value = "select * from Instance where del_flg = false")
  public List<AccAgreement> findAllFromAllGroups();

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Query(nativeQuery = true, 
      value = "select * from ACC_AGREEMENT where ID = :#{#entity.id} and del_flg = true")
  Optional<AccAgreement> findByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") AccAgreement entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records.
  The entity doesn't have a natural key. Unsatisfied condition is used in the where clause. It not called from library. */
  @Query(nativeQuery = true, 
      value = "select * from ACC_AGREEMENT where 1 = 2 and del_flg = true")
  Optional<AccAgreement> findByNaturalKeyAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") AccAgreement entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Modifying
  @Query(nativeQuery = true, 
      value = "delete from ACC_AGREEMENT where ID = :#{#entity.id} and del_flg = true")
  void deleteByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") AccAgreement entity);

}
