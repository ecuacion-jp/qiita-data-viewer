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
package jp.ecuacion.app.qiitadataviewer.web.config;

import jp.ecuacion.splib.web.config.SplibWebSecurityConfigForSwitchUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/** Security configuration for switch-user (admin impersonating a general user). */
@Configuration
public class AppSecurityConfigForSwitchUser extends SplibWebSecurityConfigForSwitchUser {

  protected AppSecurityConfigForSwitchUser(UserDetailsService userDetailsService) {
    super(userDetailsService);
  }

  @Override
  protected String getSwitchingUserDonePagePath() {
    return "/account/qiitaItem/searchList/page?switchUserSuccess";
  }

  @Override
  protected String getSwitchingUserFailurePagePath() {
    return "/admin/accGeneral/searchList/page?switchUserFailure";
  }

  @Override
  protected String getExitingUserDonePagePath() {
    return "/admin/accGeneral/searchList/page?switchUserExit";
  }
}
