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
package jp.ecuacion.app.qiitadataviewer.batch.config;

import jp.ecuacion.app.qiitadataviewer.batch.tasklet.FetchQiitaItemsTasklet;
import jp.ecuacion.lib.core.util.ObjectsUtil;
import jp.ecuacion.splib.batch.config.SplibAppParentBatchConfig;
import jp.ecuacion.splib.batch.exceptionhandler.SplibExceptionHandler;
import jp.ecuacion.splib.batch.listener.SplibJobExecutionListener;
import jp.ecuacion.splib.batch.listener.SplibStepExecutionListener;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
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
  @Bean
  Step fetchQiitaItemsJobStep1(JobRepository jobRepository,
      PlatformTransactionManager transactionManager) {
    return ObjectsUtil.requireNonNull(preparedStepBuilder("fetchQiitaItemsJobStep1", jobRepository,
        transactionManager, fetchQiitaItemsTasklet)).build();
  }
}
