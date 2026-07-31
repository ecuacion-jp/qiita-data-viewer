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
import jp.ecuacion.app.qiitadataviewer.base.entity.AccGeneral;
import jp.ecuacion.app.qiitadataviewer.web.controller.AccGeneralSearchListController.AccGeneralListForm;
import jp.ecuacion.app.qiitadataviewer.web.controller.AccGeneralSearchListController.AccGeneralSearchForm;
import jp.ecuacion.app.qiitadataviewer.web.record.AccGeneralRecord;
import jp.ecuacion.app.qiitadataviewer.web.record.AccGeneralSearchRecord;
import jp.ecuacion.app.qiitadataviewer.web.repository.AccGeneralRepository;
import jp.ecuacion.lib.core.util.ObjectsUtil;
import jp.ecuacion.splib.web.jpa.util.SpecFactory;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class AccGeneralSearchListService
    extends SystemCommonSearchListService<AccGeneralSearchForm, AccGeneralListForm, AccGeneral> {

  private AccGeneralRepository repo;

  public AccGeneralSearchListService(AccGeneralRepository repo) {
    this.repo = repo;
  }

  @Override
  public void prepareForm(AccGeneralSearchForm searchForm, AccGeneralListForm listForm,
      @Nullable UserDetails loginUser) {}

  @Override
  public void page(AccGeneralSearchForm searchForm, AccGeneralListForm listForm,
      @Nullable UserDetails loginUser) {
    UserDetails nonNullLoginUser = ObjectsUtil.requireNonNull(loginUser);
    @SuppressWarnings("null")
    List<AccGeneralRecord> list = getListFormCommon(searchForm, repo).stream()
        .map(e -> new AccGeneralRecord(e, getParams()))
        .peek(rec -> setLstUpdAccName(rec, nonNullLoginUser))
        .peek(rec -> setLstUpdAccName(rec.getAcc(), nonNullLoginUser)).toList();

    listForm.setRecList(list);
  }

  @Override
  protected Specification<AccGeneral> getSpecs(AccGeneralSearchForm searchForm) {
    AccGeneralSearchRecord rec = ObjectsUtil.requireNonNull(searchForm.getRec());

    SpecFactory<AccGeneral> specFct = new SpecFactory<>();
    List<Specification<AccGeneral>> list = specFct.addExplicitSearchConditions(rec);

    return Specification.allOf(list);
  }

  @Override
  public void delete(AccGeneralListForm listForm, UserDetails loginUser) throws Exception {
    // Not reachable from the UI (no delete button); soft delete kept for contract completeness.
    AccGeneralRecord rec = ObjectsUtil.requireNonNull(listForm.getRec());
    AccGeneral e = repo.findById(rec.getAccIdOfEntityDataType()).orElseThrow();
    e.setDelFlg(true);
  }
}
