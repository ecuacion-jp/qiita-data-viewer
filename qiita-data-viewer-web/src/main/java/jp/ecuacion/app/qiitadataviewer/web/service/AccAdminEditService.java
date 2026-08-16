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
import jp.ecuacion.app.qiitadataviewer.base.entity.Acc;
import jp.ecuacion.app.qiitadataviewer.base.entity.AccAdmin;
import jp.ecuacion.app.qiitadataviewer.web.bl.AccAdminBl;
import jp.ecuacion.app.qiitadataviewer.web.controller.AccAdminEditController.AccAdminEditForm;
import jp.ecuacion.app.qiitadataviewer.web.record.AccAdminEditRecord;
import jp.ecuacion.app.qiitadataviewer.web.repository.AccAdminRepository;
import jp.ecuacion.lib.core.violation.BusinessViolation;
import jp.ecuacion.lib.core.violation.Violations;
import org.apache.commons.lang3.StringUtils;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class AccAdminEditService extends SystemCommonEditService<AccAdminEditForm, AccAdmin> {

  private AccAdminRepository repo;
  private AccAdminBl bl;
  private PasswordEncoder passwordEncoder;

  public AccAdminEditService(AccAdminRepository repo, AccAdminBl bl,
      PasswordEncoder passwordEncoder) {
    this.repo = repo;
    this.bl = bl;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void prepareForm(AccAdminEditForm form, @Nullable UserDetails loginUser) {}

  @Override
  public void getInsertPage(AccAdminEditForm form, @Nullable UserDetails loginUser) {
    form.getAccAdmin().getAcc().setIsValid(true);
  }

  @Override
  public void getUpdatePage(AccAdminEditForm form, @Nullable UserDetails loginUser) {
    UserDetails nonNullLoginUser = Objects.requireNonNull(loginUser);
    AccAdmin e = repo.findById(form.getAccAdmin().getAccIdOfEntityDataType()).orElseThrow();

    AccAdminEditRecord rec = new AccAdminEditRecord(e, getParams());
    setCreateAndLstUpdAccNames(rec, nonNullLoginUser);
    setCreateAndLstUpdAccNames(rec.getAcc(), nonNullLoginUser);
    form.setAccAdmin(rec);
  }

  @Override
  public void edit(AccAdminEditForm form, @Nullable UserDetails loginUser) throws Exception {
    AccAdminEditRecord rec = form.getAccAdmin();
    boolean isInsert = isInsert(rec.getAcc().getId());

    editCheck(rec, isInsert);

    if (isInsert) {
      rec.getAcc().setHashedPassword(passwordEncoder.encode(rec.getRawPassword()));
      rec.getAcc().setIsAdmin(true);
      rec.getAcc().setIsAuthenticated(true);
      rec.getAcc().setIsValid(true);
      rec.getAcc().setHasLoggedIn(false);

    } else {
      // optimistic locking (password is left untouched on update)
      accBl.findAndOptimisticLockingCheck(rec.getAcc());
    }

    Acc accE = accBl.insertOrUpdate(rec.getAcc());
    bl.insertOrUpdate(rec, accE);
  }

  private void editCheck(AccAdminEditRecord rec, boolean isInsert) {
    if (isInsert && StringUtils.isEmpty(rec.getRawPassword())) {
      new Violations()
          .add(new BusinessViolation(new String[] {"accAdmin.rawPassword"},
              "ACC_ADMIN_MSG_ERR_PASSWORD_NEEDED_ON_CREATE"))
          .throwIfAny();
    }

    accBl.naturalKeyDuplicateCheck(rec.getAcc());
    accBl.duplicateCheck(rec.getAcc(), "name");
  }
}
