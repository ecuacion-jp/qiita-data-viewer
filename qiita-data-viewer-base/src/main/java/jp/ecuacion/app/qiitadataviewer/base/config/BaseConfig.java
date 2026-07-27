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
package jp.ecuacion.app.qiitadataviewer.base.config;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.*;
import org.springframework.data.jpa.repository.config.*;

@Configuration
@EntityScan("jp.ecuacion.app.qiitadataviewer.base.entity")
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@ComponentScan("jp.ecuacion.splib.jpa.config"
    + ",jp.ecuacion.app.qiitadataviewer.base.advice"
    + ",jp.ecuacion.app.qiitadataviewer.base.util"
    )
public class BaseConfig {

  @Bean
  DateTimeProvider dateTimeProvider() {
    return new DateTimeProvider() {
      @Override
      public Optional<TemporalAccessor> getNow() {
        OffsetDateTime time = OffsetDateTime.now();
        return Optional.of(time);
      }
    };
  }

}
