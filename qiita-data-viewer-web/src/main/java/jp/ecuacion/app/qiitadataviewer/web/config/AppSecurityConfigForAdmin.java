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

import java.util.ArrayList;
import java.util.List;
import jp.ecuacion.splib.core.bean.AuthorizationBean;
import jp.ecuacion.splib.web.config.SplibWebSecurityConfigForAdmin;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/** Security configuration for admin (password) login. */
@Configuration
@EnableWebSecurity
public class AppSecurityConfigForAdmin extends SplibWebSecurityConfigForAdmin {

  @Override
  protected String getDefaultSuccessUrl() {
    return "/admin/accGeneral/searchList/page";
  }

  @Override
  protected List<AuthorizationBean> getRoleInfo() {
    return new ArrayList<>();
  }

  @Override
  protected List<AuthorizationBean> getAuthorityInfo() {
    return new ArrayList<>();
  }

  @Override
  protected String getLoginNeededPage() {
    return "/public/adminLogin/page?accessDenied";
  }

  @Override
  protected String getAccessDeniedPage() {
    return "/public/adminLogin/page?accessDenied";
  }
}
