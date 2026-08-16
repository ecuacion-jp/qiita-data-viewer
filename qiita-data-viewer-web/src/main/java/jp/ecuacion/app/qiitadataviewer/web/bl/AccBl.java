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
package jp.ecuacion.app.qiitadataviewer.web.bl;

import java.util.Map;
import java.util.stream.Collectors;
import jp.ecuacion.app.qiitadataviewer.base.bl.AccBaseBl;
import jp.ecuacion.app.qiitadataviewer.base.entity.Acc;
import jp.ecuacion.app.qiitadataviewer.base.entity.AccAdmin;
import jp.ecuacion.app.qiitadataviewer.base.entity.AccGeneral;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AccBl extends AccBaseBl {

  /** Returned in a managed state. */
  public Acc getAcc(UserDetails loginUser) {
    return repo.findByMailAddress(loginUser.getUsername()).get();
  }

  public AccGeneral getAccGeneral(UserDetails loginUser) {
    return getAcc(loginUser).getAccGeneral();
  }

  public AccAdmin getAccAdmin(UserDetails loginUser) {
    return getAcc(loginUser).getAccAdmin();
  }

  public Map<Long, String> getAccNameMapForAccGeneral() {
    return repo.findAll().stream().collect(Collectors.toMap(Acc::getId, Acc::getName));
  }

  public Map<Long, String> getAccNameMapForAccAdmin() {
    return repo.findAll().stream().collect(Collectors.toMap(Acc::getId, Acc::getName));
  }
}
