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

import java.util.Objects;
import jp.ecuacion.app.qiitadataviewer.base.entity.QiitaItem;
import jp.ecuacion.app.qiitadataviewer.web.controller.QiitaItemEditController.QiitaItemEditForm;
import jp.ecuacion.app.qiitadataviewer.web.record.QiitaItemEditRecord;
import jp.ecuacion.app.qiitadataviewer.web.repository.QiitaItemRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/** Backs the read-only article detail screen; there is no insert or actual update flow. */
@Service
@Scope("prototype")
public class QiitaItemEditService extends SystemCommonEditService<QiitaItemEditForm, QiitaItem> {

  private QiitaItemRepository repo;

  public QiitaItemEditService(QiitaItemRepository repo) {
    this.repo = repo;
  }

  @Override
  public void prepareForm(QiitaItemEditForm form, @Nullable UserDetails loginUser) {}

  @Override
  public void getInsertPage(QiitaItemEditForm form, @Nullable UserDetails loginUser) {
    // Not reachable from the UI (no "insert" entry point on the search-list page).
  }

  @Override
  public void getUpdatePage(QiitaItemEditForm form, @Nullable UserDetails loginUser) {
    QiitaItem e = repo.findById(form.getQiitaItem().getIdOfEntityDataType()).orElseThrow();

    QiitaItemEditRecord rec = new QiitaItemEditRecord(e, getParams());
    setCreateAndLstUpdAccNames(rec, Objects.requireNonNull(loginUser));
    form.setQiitaItem(rec);
  }

  @Override
  public void edit(QiitaItemEditForm form, @Nullable UserDetails loginUser) throws Exception {
    // Not reachable from the UI (no submit button on the read-only detail page).
  }
}
