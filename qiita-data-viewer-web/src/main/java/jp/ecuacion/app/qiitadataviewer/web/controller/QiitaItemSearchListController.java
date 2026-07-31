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

import jp.ecuacion.app.qiitadataviewer.web.controller.QiitaItemSearchListController.QiitaItemListForm;
import jp.ecuacion.app.qiitadataviewer.web.controller.QiitaItemSearchListController.QiitaItemSearchForm;
import jp.ecuacion.app.qiitadataviewer.web.form.SystemCommonSearchForm;
import jp.ecuacion.app.qiitadataviewer.web.record.QiitaItemRecord;
import jp.ecuacion.app.qiitadataviewer.web.service.QiitaItemSearchListService;
import jp.ecuacion.splib.web.form.SplibListForm;
import jp.ecuacion.splib.web.jpa.controller.SplibSearchListJpaController;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@formatter:off
@Controller
@Scope("prototype")
@RequestMapping("account/qiitaItem/searchList")
public class QiitaItemSearchListController extends SplibSearchListJpaController
    <QiitaItemSearchForm, QiitaItemListForm, QiitaItemSearchListService> {
  //@formatter:on

  public QiitaItemSearchListController() {
    super("qiitaItem");
  }

  public static class QiitaItemListForm extends SplibListForm<QiitaItemRecord> {

    private QiitaItemRecord qiitaItem = new QiitaItemRecord();

    public QiitaItemRecord getQiitaItem() {
      return qiitaItem;
    }

    public void setQiitaItem(QiitaItemRecord qiitaItem) {
      this.qiitaItem = qiitaItem;
    }
  }

  @Component
  @Scope("prototype")
  public static class QiitaItemSearchForm extends SystemCommonSearchForm {

    private QiitaItemRecord qiitaItem = new QiitaItemRecord();

    @Override
    @NonNull
    protected String getDefaultSortItem() {
      return "createdAt";
    }

    public QiitaItemRecord getQiitaItem() {
      return qiitaItem;
    }

    public void setQiitaItem(QiitaItemRecord qiitaItem) {
      this.qiitaItem = qiitaItem;
    }
  }
}
