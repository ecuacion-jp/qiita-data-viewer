package jp.ecuacion.app.qiitadataviewer.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("jp.ecuacion.app.qiitadataviewer.core.repository")
@ComponentScan("jp.ecuacion.app.qiitadataviewer.base.config"
    + ",jp.ecuacion.app.qiitadataviewer.core.bl"
    + ",jp.ecuacion.app.qiitadataviewer.core.util"
  )
@PropertySources({
  @PropertySource(value = "classpath:application_core.properties"),
  @PropertySource(value = "classpath:application_core-profile.properties")
})
public class AppCoreConfig {

}
