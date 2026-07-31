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

import jp.ecuacion.app.qiitadataviewer.base.entity.AccGeneral;
import jp.ecuacion.app.qiitadataviewer.web.controller.AccGeneralEditController.AccGeneralEditForm;
import jp.ecuacion.app.qiitadataviewer.web.record.AccGeneralEditRecord;
import jp.ecuacion.app.qiitadataviewer.web.repository.AccGeneralRepository;
import jp.ecuacion.lib.core.util.ObjectsUtil;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/** Admin-only edit screen for a general user. Only role/isValid are actually changed. */
@Service
@Scope("prototype")
public class AccGeneralEditService
    extends SystemCommonEditService<AccGeneralEditForm, AccGeneral> {

  private AccGeneralRepository repo;

  public AccGeneralEditService(AccGeneralRepository repo) {
    this.repo = repo;
  }

  @Override
  public void prepareForm(AccGeneralEditForm form, @Nullable UserDetails loginUser) {}

  @Override
  public void getInsertPage(AccGeneralEditForm form, @Nullable UserDetails loginUser) {
    // Not reachable from the UI (general accounts are only created via OAuth login).
  }

  @Override
  public void getUpdatePage(AccGeneralEditForm form, @Nullable UserDetails loginUser) {
    UserDetails nonNullLoginUser = ObjectsUtil.requireNonNull(loginUser);
    AccGeneral e = repo.findById(form.getAccGeneral().getAccIdOfEntityDataType()).orElseThrow();

    AccGeneralEditRecord rec = new AccGeneralEditRecord(e, getParams());
    setCreateAndLstUpdAccNames(rec, nonNullLoginUser);
    setCreateAndLstUpdAccNames(rec.getAcc(), nonNullLoginUser);
    form.setAccGeneral(rec);
  }

  @Override
  public void edit(AccGeneralEditForm form, @Nullable UserDetails loginUser) throws Exception {
    AccGeneralEditRecord rec = form.getAccGeneral();

    // optimistic locking
    accBl.findAndOptimisticLockingCheck(rec.getAcc());

    // update (role / isValid; other Acc fields round-trip unchanged)
    accBl.insertOrUpdate(rec.getAcc());
  }
}
