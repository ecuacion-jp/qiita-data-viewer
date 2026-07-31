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
import jakarta.transaction.Transactional;
import java.util.Optional;
import jp.ecuacion.app.qiitadataviewer.base.entity.Acc;
import jp.ecuacion.app.qiitadataviewer.web.config.AppConfig;
import jp.ecuacion.app.qiitadataviewer.web.repository.AccRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Handles password-based login for admin accounts, and is also used by the switch-user
 * (impersonation) filter to look up the general account being switched to.
 */
@Transactional(rollbackOn = Exception.class)
@Service
@Scope("prototype")
public class LoginAuthenticationService implements UserDetailsService {

  @Autowired
  private HttpServletRequest request;

  private AccRepository accRepo;

  public LoginAuthenticationService(AccRepository accRepo) {
    this.accRepo = accRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {

    boolean isAdmin = requestPathStartsWith("/public/admin");

    try {
      Optional<Acc> optAcc = accRepo
          .findByMailAddressAndIsAdminAndIsValidTrueAndIsAuthenticatedTrue(mailAddress, isAdmin);

      if (optAcc.isEmpty()) {
        throw new UsernameNotFoundException("");
      }

      Acc acc = optAcc.get();
      String password = acc.getHashedPassword() != null ? acc.getHashedPassword() : "";
      UserDetails userDetails =
          User.builder().username(mailAddress).password(password).roles(acc.getRole().toString())
              .build();

      // Stored for use as the audit "who did this" id.
      request.getSession().setAttribute(AppConfig.SESSION_KEY_LOGIN_USER_ID, acc.getId());

      acc.setHasLoggedIn(true);

      return userDetails;

    } catch (UsernameNotFoundException exception) {
      // Rethrow with an empty message so the resulting error message is uniform.
      throw new UsernameNotFoundException("");

    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }

  private boolean requestPathStartsWith(String pathStart) {
    return request.getRequestURI().startsWith(pathStart)
        || request.getRequestURI().startsWith(request.getContextPath() + pathStart);
  }
}
