package jp.ecuacion.app.qiitadataviewer.base.datatype;

import jakarta.validation.*;
import java.lang.annotation.*;
import jp.ecuacion.lib.validation.constraints.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = {})
@SizeString(min = 7, max = 15)
@PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "ipAddress")
public @interface IpAddressDataTypeValidator {

  String message() default "";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}
