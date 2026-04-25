package jp.ecuacion.app.qiitadataviewer.base.repository;

import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.entity.MailAuthManagement;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface MailAuthManagementBaseRepository extends SystemCommonBaseRepository<MailAuthManagement, Long>, JpaSpecificationExecutor<MailAuthManagement> {

  /** Is defined with jpql because hibernate filter does not take effect to spring data jpa standard 'findById'. */
  @Query(value = "from MailAuthManagement where id = :id")
  Optional<MailAuthManagement> findById(Long id);

  @Query(nativeQuery = true, value = "select * from Instance where del_flg = false")
  public List<MailAuthManagement> findAllFromAllGroups();

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Query(nativeQuery = true, 
      value = "select * from MAIL_AUTH_MANAGEMENT where ID = :#{#entity.id} and del_flg = true")
  Optional<MailAuthManagement> findByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") MailAuthManagement entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records.
  The entity doesn't have a natural key. Unsatisfied condition is used in the where clause. It not called from library. */
  @Query(nativeQuery = true, 
      value = "select * from MAIL_AUTH_MANAGEMENT where 1 = 2 and del_flg = true")
  Optional<MailAuthManagement> findByNaturalKeyAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") MailAuthManagement entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Modifying
  @Query(nativeQuery = true, 
      value = "delete from MAIL_AUTH_MANAGEMENT where ID = :#{#entity.id} and del_flg = true")
  void deleteByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") MailAuthManagement entity);

}
