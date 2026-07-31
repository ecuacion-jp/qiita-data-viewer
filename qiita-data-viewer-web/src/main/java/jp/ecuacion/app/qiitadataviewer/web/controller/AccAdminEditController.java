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

import jakarta.validation.Valid;
import jp.ecuacion.app.qiitadataviewer.web.controller.AccAdminEditController.AccAdminEditForm;
import jp.ecuacion.app.qiitadataviewer.web.record.AccAdminEditRecord;
import jp.ecuacion.app.qiitadataviewer.web.service.AccAdminEditService;
import jp.ecuacion.splib.web.form.SplibEditForm;
import jp.ecuacion.splib.web.jpa.controller.SplibEditJpaController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** Admin-only screen to create / edit admin accounts. */
@Controller
@Scope("prototype")
@RequestMapping("admin/accAdmin/edit")
public class AccAdminEditController
    extends SplibEditJpaController<AccAdminEditForm, AccAdminEditService> {

  public AccAdminEditController() {
    super(PageTemplatePatternEnum.PAIR_WITH_SEARCH_LIST, "accAdmin");
  }

  public static class AccAdminEditForm extends SplibEditForm {

    @Valid
    private AccAdminEditRecord accAdmin = new AccAdminEditRecord();

    public AccAdminEditRecord getAccAdmin() {
      return accAdmin;
    }

    public void setAccAdmin(AccAdminEditRecord accAdmin) {
      this.accAdmin = accAdmin;
    }
  }
}
