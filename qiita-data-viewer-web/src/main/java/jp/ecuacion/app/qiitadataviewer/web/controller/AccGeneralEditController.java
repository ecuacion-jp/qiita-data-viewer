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
import jp.ecuacion.app.qiitadataviewer.web.controller.AccGeneralEditController.AccGeneralEditForm;
import jp.ecuacion.app.qiitadataviewer.web.record.AccGeneralEditRecord;
import jp.ecuacion.app.qiitadataviewer.web.service.AccGeneralEditService;
import jp.ecuacion.splib.web.form.SplibEditForm;
import jp.ecuacion.splib.web.jpa.controller.SplibEditJpaController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** Admin-only screen to change a general user's role / active flag. */
@Controller
@Scope("prototype")
@RequestMapping("admin/accGeneral/edit")
public class AccGeneralEditController
    extends SplibEditJpaController<AccGeneralEditForm, AccGeneralEditService> {

  public AccGeneralEditController() {
    super(PageTemplatePatternEnum.PAIR_WITH_SEARCH_LIST, "accGeneral");
  }

  public static class AccGeneralEditForm extends SplibEditForm {

    @Valid
    private AccGeneralEditRecord accGeneral = new AccGeneralEditRecord();

    public AccGeneralEditRecord getAccGeneral() {
      return accGeneral;
    }

    public void setAccGeneral(AccGeneralEditRecord accGeneral) {
      this.accGeneral = accGeneral;
    }
  }
}
