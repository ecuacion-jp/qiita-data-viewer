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
package jp.ecuacion.app.qiitadataviewer.web.service;

import java.util.List;
import jp.ecuacion.app.qiitadataviewer.base.entity.AccAdmin;
import jp.ecuacion.app.qiitadataviewer.web.bl.AccAdminBl;
import jp.ecuacion.app.qiitadataviewer.web.controller.AccAdminSearchListController.AccAdminListForm;
import jp.ecuacion.app.qiitadataviewer.web.controller.AccAdminSearchListController.AccAdminSearchForm;
import jp.ecuacion.app.qiitadataviewer.web.record.AccAdminRecord;
import jp.ecuacion.app.qiitadataviewer.web.record.AccAdminSearchRecord;
import jp.ecuacion.app.qiitadataviewer.web.repository.AccAdminRepository;
import jp.ecuacion.lib.core.util.ObjectsUtil;
import jp.ecuacion.lib.core.violation.BusinessViolation;
import jp.ecuacion.lib.core.violation.Violations;
import jp.ecuacion.splib.web.jpa.util.SpecFactory;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class AccAdminSearchListService
    extends SystemCommonSearchListService<AccAdminSearchForm, AccAdminListForm, AccAdmin> {

  private AccAdminRepository repo;
  private AccAdminBl bl;

  public AccAdminSearchListService(AccAdminRepository repo, AccAdminBl bl) {
    this.repo = repo;
    this.bl = bl;
  }

  @Override
  public void prepareForm(AccAdminSearchForm searchForm, AccAdminListForm listForm,
      @Nullable UserDetails loginUser) {}

  @SuppressWarnings("null")
  @Override
  public void page(AccAdminSearchForm searchForm, AccAdminListForm listForm,
      @Nullable UserDetails loginUser) {
    UserDetails nonNullLoginUser = ObjectsUtil.requireNonNull(loginUser);
    listForm.setRecList(getListFormCommon(searchForm, repo).stream()
        .map(e -> new AccAdminRecord(e, getParams()))
        .peek(rec -> setLstUpdAccName(rec, nonNullLoginUser))
        .peek(rec -> setLstUpdAccName(rec.getAcc(), nonNullLoginUser)).toList());
  }

  @Override
  protected Specification<AccAdmin> getSpecs(AccAdminSearchForm searchForm) {
    AccAdminSearchRecord rec = searchForm.getAccAdmin();

    SpecFactory<AccAdmin> specFct = new SpecFactory<>();
    List<Specification<AccAdmin>> list = specFct.addExplicitSearchConditions(rec);

    return Specification.allOf(list);
  }

  @Override
  public void delete(AccAdminListForm listForm, UserDetails loginUser) throws Exception {
    // optimistic locking
    AccAdmin e = bl.findAndOptimisticLockingCheck(listForm.getAccAdmin());
    accBl.findAndOptimisticLockingCheck(listForm.getAccAdmin().getAcc());

    // checks
    deleteCheck(e);

    // soft delete
    e.setDelFlg(true);
    e.getAcc().setDelFlg(true);
  }

  private void deleteCheck(AccAdmin e) {
    if (e.getAcc().getHasLoggedIn()) {
      new Violations().add(new BusinessViolation("ACC_COMMON_LOGGED_IN_USER_CANNOT_BE_DELETED"))
          .throwIfAny();
    }
  }
}
