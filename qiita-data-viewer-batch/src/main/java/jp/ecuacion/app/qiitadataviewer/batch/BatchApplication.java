package jp.ecuacion.app.qiitadataviewer.batch;

import jp.ecuacion.splib.batch.SplibBatchApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Batch application main class. */
@SpringBootApplication
public class BatchApplication extends SplibBatchApplication {

  /** Main method. */
  public static void main(String[] args) {
    SplibBatchApplication.main(BatchApplication.class, args);
  }
}
