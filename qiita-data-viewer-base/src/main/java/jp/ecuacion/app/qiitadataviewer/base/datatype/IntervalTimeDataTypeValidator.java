package jp.ecuacion.app.qiitadataviewer.base.datatype;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = {})
@DecimalMin(value = "3")
@DecimalMax(value = "1440")
public @interface IntervalTimeDataTypeValidator {

  String message() default "";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}
