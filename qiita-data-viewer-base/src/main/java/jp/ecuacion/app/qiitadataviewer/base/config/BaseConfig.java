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
