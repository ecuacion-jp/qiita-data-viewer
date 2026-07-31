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
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import jp.ecuacion.app.qiitadataviewer.base.entity.Acc;
import jp.ecuacion.app.qiitadataviewer.web.record.AccAdminRecord;
import jp.ecuacion.app.qiitadataviewer.web.record.AccGeneralRecord;
import jp.ecuacion.app.qiitadataviewer.web.repository.AccRepository;
import jp.ecuacion.splib.core.record.SplibRecord;
import jp.ecuacion.splib.web.exception.RedirectException;
import jp.ecuacion.splib.web.service.SplibDataStoreDependentControllerAdviceService;
import jp.ecuacion.splib.web.util.SplibDatetimeFormatUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class AppAccountControllerAdviceService
    extends SplibDataStoreDependentControllerAdviceService {

  private AccRepository accRepo;
  private HttpServletRequest request;
  private HttpServletResponse response;

  public AppAccountControllerAdviceService(AccRepository accRepo, HttpServletRequest request,
      HttpServletResponse response) {
    this.accRepo = accRepo;
    this.request = request;
    this.response = response;
  }

  @Override
  public AccGeneralRecord getAccGeneral(UserDetails loginUser) {
    Acc acc = getAcc(loginUser);
    return new AccGeneralRecord(acc.getAccGeneral(), SplibDatetimeFormatUtil.getParams(request));
  }

  @Override
  public AccAdminRecord getAccAdmin(UserDetails loginUser) {
    Acc acc = getAcc(loginUser);
    return new AccAdminRecord(acc.getAccAdmin(), SplibDatetimeFormatUtil.getParams(request));
  }

  @Override
  public Object getGroupId(SplibRecord loginAcc) {
    return Long.valueOf(((AccGeneralRecord) loginAcc).getAccId());
  }

  // Only called from SplibDataStoreDependentControllerAdvice.setAccountInfo, which already
  // returns early when loginUser is null - so loginUser is always non-null here in practice.
  private Acc getAcc(UserDetails loginUser) {
    Optional<Acc> optAcc = accRepo.findByMailAddress(loginUser.getUsername());

    // loginUser is non-null (logged in) but no matching Acc exists only when a stale session
    // survives a mail-address change. Log the stale session out immediately.
    if (optAcc.isEmpty()) {
      new SecurityContextLogoutHandler().logout(request, response, null);
      throw new RedirectException("/public/show/page?id=home&logoutDoneBecauseMailAddressChanged");
    }

    return optAcc.get();
  }
}
