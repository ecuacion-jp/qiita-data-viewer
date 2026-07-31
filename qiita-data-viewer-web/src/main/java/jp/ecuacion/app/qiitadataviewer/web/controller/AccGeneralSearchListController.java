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

import java.util.Objects;
import jp.ecuacion.app.qiitadataviewer.web.controller.AccGeneralSearchListController.AccGeneralListForm;
import jp.ecuacion.app.qiitadataviewer.web.controller.AccGeneralSearchListController.AccGeneralSearchForm;
import jp.ecuacion.app.qiitadataviewer.web.record.AccGeneralRecord;
import jp.ecuacion.app.qiitadataviewer.web.record.AccGeneralSearchRecord;
import jp.ecuacion.app.qiitadataviewer.web.service.AccGeneralSearchListService;
import jp.ecuacion.splib.web.form.SplibListForm;
import jp.ecuacion.splib.web.form.SplibSearchRecForm;
import jp.ecuacion.splib.web.jpa.controller.SplibSearchListJpaController;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** Admin-only screen listing general (OAuth) user accounts. */
//@formatter:off
@Controller
@Scope("prototype")
@RequestMapping("admin/accGeneral/searchList")
public class AccGeneralSearchListController extends SplibSearchListJpaController<
    AccGeneralSearchForm, AccGeneralListForm, AccGeneralSearchListService> {
  //@formatter:on

  public AccGeneralSearchListController() {
    super("accGeneral", newContext().mainRootRecordName("rec"));
  }

  @Component
  @Scope("prototype")
  public static class AccGeneralSearchForm extends SplibSearchRecForm<AccGeneralSearchRecord> {
    public AccGeneralSearchForm() {
      rec = new AccGeneralSearchRecord();
      Objects.requireNonNull(rec).getAcc().setIsValid(true);
    }

    @Override
    @NonNull
    protected String getDefaultSortItem() {
      return "accId";
    }
  }

  public static class AccGeneralListForm extends SplibListForm<AccGeneralRecord> {
    public AccGeneralListForm() {
      rec = new AccGeneralRecord();
    }
  }
}
