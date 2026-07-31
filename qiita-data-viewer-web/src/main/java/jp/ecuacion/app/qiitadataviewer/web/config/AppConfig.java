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

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import jp.ecuacion.splib.web.config.SplibWebConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** Web app wide configuration. */
@Configuration
@EnableJpaRepositories("jp.ecuacion.app.qiitadataviewer.web.repository")
@ComponentScan("jp.ecuacion.app.qiitadataviewer.core.config")
// SplibWebConfig itself carries the @ComponentScan that registers ecuacion-splib-web's own
// controllers (login, error pages, etc.) - nothing in the framework imports it automatically.
@Import(SplibWebConfig.class)
public class AppConfig {

  public static final String SESSION_KEY_LOGIN_USER_ID = "loginUserId";

  /** Same sentinel acc id batch uses for records with no real login user (public access, etc). */
  private static final Long BACKGROUND_PROCESS_ACC_ID = -1L;

  @SuppressWarnings("null")
  @Bean
  AuditorAware<Long> auditorAware(HttpServletRequest request) {
    return new AuditorAware<Long>() {
      @Override
      public Optional<Long> getCurrentAuditor() {
        Long accId = (Long) request.getSession().getAttribute(SESSION_KEY_LOGIN_USER_ID);
        return accId == null ? Optional.of(BACKGROUND_PROCESS_ACC_ID) : Optional.of(accId);
      }
    };
  }
}
