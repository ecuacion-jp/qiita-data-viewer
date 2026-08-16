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
import java.util.Objects;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaItem;
import jp.ecuacion.app.qiitadataviewer.web.controller.QiitaItemSearchListController.QiitaItemListForm;
import jp.ecuacion.app.qiitadataviewer.web.controller.QiitaItemSearchListController.QiitaItemSearchForm;
import jp.ecuacion.app.qiitadataviewer.web.record.QiitaItemRecord;
import jp.ecuacion.app.qiitadataviewer.web.repository.QiitaItemRepository;
import jp.ecuacion.splib.web.jpa.util.SpecFactory;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class QiitaItemSearchListService
    extends SystemCommonSearchListService<QiitaItemSearchForm, QiitaItemListForm, QiitaItem> {

  private QiitaItemRepository repo;

  public QiitaItemSearchListService(QiitaItemRepository repo) {
    this.repo = repo;
  }

  @Override
  public void prepareForm(QiitaItemSearchForm searchForm, QiitaItemListForm listForm,
      @Nullable UserDetails loginUser) {}

  @Override
  public void page(QiitaItemSearchForm searchForm, QiitaItemListForm listForm,
      @Nullable UserDetails loginUser) {
    UserDetails nonNullLoginUser = Objects.requireNonNull(loginUser);
    @SuppressWarnings("null")
    List<QiitaItemRecord> list = getListFormCommon(searchForm, repo).getContent().stream()
        .map(e -> new QiitaItemRecord(e, getParams()))
        .peek(rec -> setLstUpdAccName(rec, nonNullLoginUser)).toList();

    listForm.setRecList(list);
  }

  @Override
  protected Specification<QiitaItem> getSpecs(QiitaItemSearchForm searchForm) {
    QiitaItemRecord rec = searchForm.getQiitaItem();

    SpecFactory<QiitaItem> specFct = new SpecFactory<>();
    List<Specification<QiitaItem>> list = specFct.addExplicitSearchConditions(rec);

    return Specification.allOf(list);
  }

  @Override
  public void delete(QiitaItemListForm listForm, UserDetails loginUser) throws Exception {
    // Not reachable from the UI (no delete button); soft delete kept for contract completeness.
    QiitaItem e = repo.findById(listForm.getQiitaItem().getIdOfEntityDataType()).orElseThrow();
    e.setDelFlg(true);
  }
}
