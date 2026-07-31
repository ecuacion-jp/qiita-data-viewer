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
import jp.ecuacion.app.qiitadataviewer.web.controller.QiitaItemEditController.QiitaItemEditForm;
import jp.ecuacion.app.qiitadataviewer.web.record.QiitaItemEditRecord;
import jp.ecuacion.app.qiitadataviewer.web.service.QiitaItemEditService;
import jp.ecuacion.splib.web.form.SplibEditForm;
import jp.ecuacion.splib.web.jpa.controller.SplibEditJpaController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** Article detail screen. Every field is read-only; there is no create flow. */
@Controller
@Scope("prototype")
@RequestMapping("account/qiitaItem/edit")
public class QiitaItemEditController
    extends SplibEditJpaController<QiitaItemEditForm, QiitaItemEditService> {

  public QiitaItemEditController() {
    super(PageTemplatePatternEnum.PAIR_WITH_SEARCH_LIST, "qiitaItem");
  }

  public static class QiitaItemEditForm extends SplibEditForm {

    @Valid
    private QiitaItemEditRecord qiitaItem = new QiitaItemEditRecord();

    public QiitaItemEditRecord getQiitaItem() {
      return qiitaItem;
    }

    public void setQiitaItem(QiitaItemEditRecord qiitaItem) {
      this.qiitaItem = qiitaItem;
    }
  }
}
