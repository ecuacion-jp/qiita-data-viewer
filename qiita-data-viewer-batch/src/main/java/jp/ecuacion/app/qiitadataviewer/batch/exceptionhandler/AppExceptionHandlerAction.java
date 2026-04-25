package jp.ecuacion.app.qiitadataviewer.batch.exceptionhandler;

import jakarta.annotation.Nonnull;
import jp.ecuacion.lib.core.util.MailUtil;
import jp.ecuacion.splib.core.exceptionhandler.SplibExceptionHandlerAction;
import org.springframework.stereotype.Component;

/** Handles uncaught exceptions by sending an error mail. */
@Component
public class AppExceptionHandlerAction implements SplibExceptionHandlerAction {

  @Override
  public void execute(@Nonnull Throwable th) {
    MailUtil.sendErrorMail(th);
  }
}
