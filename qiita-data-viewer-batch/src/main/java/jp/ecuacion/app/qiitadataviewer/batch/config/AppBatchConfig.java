package jp.ecuacion.app.qiitadataviewer.batch.config;

import jp.ecuacion.app.qiitadataviewer.batch.tasklet.FetchQiitaItemsTasklet;
import jp.ecuacion.splib.batch.config.SplibAppParentBatchConfig;
import jp.ecuacion.splib.batch.exceptionhandler.SplibExceptionHandler;
import jp.ecuacion.splib.batch.listener.SplibJobExecutionListener;
import jp.ecuacion.splib.batch.listener.SplibStepExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;

/** Batch job configuration. */
@Configuration
@EnableJpaRepositories("jp.ecuacion.app.qiitadataviewer.batch.repository")
@ComponentScan(basePackages = "jp.ecuacion.splib.batch.config"
    + ",jp.ecuacion.app.qiitadataviewer.core.config")
@SuppressWarnings("NullAway.Init")
public class AppBatchConfig extends SplibAppParentBatchConfig {

  public AppBatchConfig(SplibJobExecutionListener jobExecutionListener,
      SplibStepExecutionListener stepExecutionListener, SplibExceptionHandler exceptionHandler) {
    super(jobExecutionListener, stepExecutionListener, exceptionHandler);
  }

  @Autowired
  private FetchQiitaItemsTasklet fetchQiitaItemsTasklet;

  /** Runs all jobs sequentially. For testing purposes. */
  @Bean(name = "allJobs")
  Job allJobs(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return preparedJobBuilder("allJobs", jobRepository)
        .start(fetchQiitaItemsJobStep1(jobRepository, transactionManager)).build();
  }

  /** Fetches Qiita items and stores them to DB. */
  @Bean(name = "fetchQiitaItemsJob")
  Job fetchQiitaItemsJob(JobRepository jobRepository,
      PlatformTransactionManager transactionManager) {
    return preparedJobBuilder("fetchQiitaItemsJob", jobRepository)
        .start(fetchQiitaItemsJobStep1(jobRepository, transactionManager)).build();
  }

  /** Step 1 of fetchQiitaItemsJob. */
  @SuppressWarnings("null")
  @Bean
  Step fetchQiitaItemsJobStep1(JobRepository jobRepository,
      PlatformTransactionManager transactionManager) {
    return preparedStepBuilder("fetchQiitaItemsJobStep1", jobRepository, transactionManager,
        fetchQiitaItemsTasklet).build();
  }
}
