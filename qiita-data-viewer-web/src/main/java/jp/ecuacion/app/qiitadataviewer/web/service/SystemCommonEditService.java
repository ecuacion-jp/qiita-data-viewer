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

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import jp.ecuacion.app.qiitadataviewer.base.entity.SystemCommon;
import jp.ecuacion.app.qiitadataviewer.base.record.SystemCommonBaseRecord;
import jp.ecuacion.app.qiitadataviewer.web.bl.AccBl;
import jp.ecuacion.splib.web.form.SplibEditForm;
import jp.ecuacion.splib.web.jpa.service.SplibEditJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class SystemCommonEditService<F extends SplibEditForm, E extends SystemCommon>
    extends SplibEditJpaService<F, E> {

  @Autowired
  protected HttpServletRequest request;

  @Autowired
  protected AccBl accBl;

  protected void setCreateAndLstUpdAccNames(SystemCommonBaseRecord rec, UserDetails loginUser) {
    Map<Long, String> accNameMap =
        new HashMap<>(accBl.getAcc(loginUser).getIsAdmin() ? accBl.getAccNameMapForAccAdmin()
            : accBl.getAccNameMapForAccGeneral());

    rec.setCreateAccName(accNameMap.get(Long.valueOf(rec.getCreateAccId())));
    rec.setLstUpdAccName(accNameMap.get(Long.valueOf(rec.getLstUpdAccId())));
  }
}
