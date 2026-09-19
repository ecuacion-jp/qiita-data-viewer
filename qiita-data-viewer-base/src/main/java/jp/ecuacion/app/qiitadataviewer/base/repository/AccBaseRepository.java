package jp.ecuacion.app.qiitadataviewer.base.repository;

import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.entity.Acc;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface AccBaseRepository extends SystemCommonBaseRepository<Acc, Long>, JpaSpecificationExecutor<Acc> {

  /** Is defined with jpql because hibernate filter does not take effect to spring data jpa standard 'findById'. */
  @Query(value = "from Acc where id = :id")
  Optional<Acc> findById(Long id);

  /** Finds by natural key. */
  Optional<Acc> findByMailAddress(
      String mailAddress);

  @Query(nativeQuery = true, value = "select * from Instance where del_flg = false")
  public List<Acc> findAllFromAllGroups();

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Query(nativeQuery = true, 
      value = "select * from ACC where ID = :#{#entity.id} and del_flg = true")
  Optional<Acc> findByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") Acc entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Query(nativeQuery = true, 
      value = "select * from ACC where mail_address = :#{#entity.mailAddress} and del_flg = true")
  Optional<Acc> findByNaturalKeyAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") Acc entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Modifying
  @Query(nativeQuery = true, 
      value = "delete from ACC where ID = :#{#entity.id} and del_flg = true")
  void deleteByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") Acc entity);

}
