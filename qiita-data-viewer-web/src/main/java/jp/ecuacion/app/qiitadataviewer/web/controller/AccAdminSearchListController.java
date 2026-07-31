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
package jp.ecuacion.app.qiitadataviewer.web.controller;

import jp.ecuacion.app.qiitadataviewer.web.controller.AccAdminSearchListController.AccAdminListForm;
import jp.ecuacion.app.qiitadataviewer.web.controller.AccAdminSearchListController.AccAdminSearchForm;
import jp.ecuacion.app.qiitadataviewer.web.form.SystemCommonSearchForm;
import jp.ecuacion.app.qiitadataviewer.web.record.AccAdminRecord;
import jp.ecuacion.app.qiitadataviewer.web.record.AccAdminSearchRecord;
import jp.ecuacion.app.qiitadataviewer.web.service.AccAdminSearchListService;
import jp.ecuacion.splib.web.form.SplibListForm;
import jp.ecuacion.splib.web.jpa.controller.SplibSearchListJpaController;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** Admin-only screen listing admin accounts themselves. */
@Controller
@Scope("prototype")
@RequestMapping("admin/accAdmin/searchList")
public class AccAdminSearchListController extends
    SplibSearchListJpaController<AccAdminSearchForm, AccAdminListForm, AccAdminSearchListService> {

  public AccAdminSearchListController() {
    super("accAdmin");
  }

  @Component
  @Scope("prototype")
  public static class AccAdminSearchForm extends SystemCommonSearchForm {

    private AccAdminSearchRecord accAdmin = new AccAdminSearchRecord();

    public AccAdminSearchForm() {
      accAdmin.getAcc().setIsValid(true);
    }

    @Override
    @NonNull
    protected String getDefaultSortItem() {
      return "accId";
    }

    public AccAdminSearchRecord getAccAdmin() {
      return accAdmin;
    }

    public void setAccAdmin(AccAdminSearchRecord accAdmin) {
      this.accAdmin = accAdmin;
    }
  }

  public static class AccAdminListForm extends SplibListForm<AccAdminRecord> {
    private AccAdminRecord accAdmin = new AccAdminRecord();

    public AccAdminRecord getAccAdmin() {
      return accAdmin;
    }

    public void setAccAdmin(AccAdminRecord accAdmin) {
      this.accAdmin = accAdmin;
    }
  }
}
