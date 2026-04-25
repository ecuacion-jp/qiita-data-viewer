package jp.ecuacion.app.qiitadataviewer.base.advice;

import jp.ecuacion.splib.jpa.advice.SplibSoftDeleteAdvice;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SoftDeleteAdvice extends SplibSoftDeleteAdvice {

}
