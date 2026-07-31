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
import jp.ecuacion.app.qiitadataviewer.base.enums.AccRoleEnum;
import jp.ecuacion.splib.core.bean.AuthorizationBean;
import jp.ecuacion.splib.web.config.SplibWebSecurityConfig;
import jp.ecuacion.splib.web.oauth2.SplibAppleClientSecretService;
import jp.ecuacion.splib.web.oauth2.SplibOauth2AuthSuccessHandler;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

/** Security configuration for general (Google / Apple OAuth2) login. */
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class AppSecurityConfig extends SplibWebSecurityConfig {

  protected AppSecurityConfig(@Nullable SplibOauth2AuthSuccessHandler oauth2SuccessHandler,
      @Nullable SplibAppleClientSecretService appleClientSecretService,
      @Nullable ClientRegistrationRepository clientRegistrationRepository) {
    super(oauth2SuccessHandler, appleClientSecretService, clientRegistrationRepository);
  }

  @Override
  protected String getDefaultSuccessUrl() {
    return "/account/qiitaItem/searchList/page";
  }

  @Override
  protected List<AuthorizationBean> getRoleInfo() {
    List<AuthorizationBean> list = new ArrayList<>();
    list.add(new AuthorizationBean("/account/qiitaItem/**", AccRoleEnum.ACC_USER.toString()));

    return list;
  }

  @Override
  protected @Nullable List<AuthorizationBean> getAuthorityInfo() {
    return null;
  }

  @Override
  protected String getLoginNeededPage() {
    return "/public/login/page";
  }

  @Override
  protected String getAccessDeniedPage() {
    return "/public/login/page";
  }
}
