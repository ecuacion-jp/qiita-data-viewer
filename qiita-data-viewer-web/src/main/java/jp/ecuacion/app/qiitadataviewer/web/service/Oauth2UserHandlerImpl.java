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
import jp.ecuacion.app.qiitadataviewer.base.entity.Acc;
import jp.ecuacion.app.qiitadataviewer.base.entity.AccGeneral;
import jp.ecuacion.app.qiitadataviewer.base.enums.AccRoleEnum;
import jp.ecuacion.app.qiitadataviewer.web.config.AppConfig;
import jp.ecuacion.app.qiitadataviewer.web.repository.AccGeneralRepository;
import jp.ecuacion.app.qiitadataviewer.web.repository.AccRepository;
import jp.ecuacion.splib.web.oauth2.SplibOauth2UserHandler;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/** Handles user lookup and creation for Google / Apple OAuth2 login. */
@Service
@Transactional(rollbackOn = Exception.class)
public class Oauth2UserHandlerImpl implements SplibOauth2UserHandler {

  private final AccRepository accRepo;
  private final AccGeneralRepository accGeneralRepo;

  public Oauth2UserHandlerImpl(AccRepository accRepo, AccGeneralRepository accGeneralRepo) {
    this.accRepo = accRepo;
    this.accGeneralRepo = accGeneralRepo;
  }

  @Override
  public UserDetails findOrCreateUser(String email, String name, String provider) {
    Acc acc = accRepo.findByMailAddress(email).orElseGet(() -> createAcc(email, name));
    acc.setHasLoggedIn(true);

    return User.builder().username(email)
        .password(acc.getHashedPassword() != null ? acc.getHashedPassword() : "")
        .roles(acc.getRole().toString()).build();
  }

  @Override
  public void afterLoginSuccess(HttpServletRequest request, UserDetails userDetails) {
    accRepo.findByMailAddress(userDetails.getUsername()).ifPresent(
        acc -> request.getSession().setAttribute(AppConfig.SESSION_KEY_LOGIN_USER_ID, acc.getId()));
  }

  private Acc createAcc(String email, String name) {
    Acc acc = new Acc();
    acc.setMailAddress(email);
    acc.setName(name != null ? name : email);
    acc.setIsAdmin(false);
    acc.setRole(AccRoleEnum.ACC_USER);
    acc.setHashedPassword(null);
    acc.setIsValid(true);
    acc.setIsAuthenticated(true);
    acc.setHasLoggedIn(false);
    accRepo.save(acc);

    AccGeneral accGeneral = new AccGeneral();
    accGeneral.setAcc(acc);
    // Unused by this app (no "app" concept), but the column is NOT NULL.
    accGeneral.setAccessibleToAllApps(false);
    accGeneralRepo.save(accGeneral);

    return acc;
  }
}
