package jp.ecuacion.app.qiitadataviewer.base.repository;

import java.util.*;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface QiitaUserBaseRepository extends SystemCommonBaseRepository<QiitaUser, Long>, JpaSpecificationExecutor<QiitaUser> {

  /** Is defined with jpql because hibernate filter does not take effect to spring data jpa standard 'findById'. */
  @Query(value = "from QiitaUser where id = :id")
  Optional<QiitaUser> findById(Long id);

  /** Finds by natural key. */
  Optional<QiitaUser> findByUserIdInQiitaWebsite(
      String userIdInQiitaWebsite);

  /** Is generated for existence check when a parent record is deleted. */
  public List<QiitaUser> findByAcc_Id(Long id);

  @Query(nativeQuery = true, value = "select * from Instance where del_flg = false")
  public List<QiitaUser> findAllFromAllGroups();

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Query(nativeQuery = true, 
      value = "select * from QIITA_USER where ID = :#{#entity.id} and del_flg = true")
  Optional<QiitaUser> findByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") QiitaUser entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Query(nativeQuery = true, 
      value = "select * from QIITA_USER where user_id_in_qiita_website = :#{#entity.userIdInQiitaWebsite} and del_flg = true")
  Optional<QiitaUser> findByNaturalKeyAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") QiitaUser entity);

  /** Used for procedures in libraries. Native query is used to search soft deleted records. */
  @Modifying
  @Query(nativeQuery = true, 
      value = "delete from QIITA_USER where ID = :#{#entity.id} and del_flg = true")
  void deleteByIdAndSoftDeleteFieldTrueFromAllGroups(@Param("entity") QiitaUser entity);

}
