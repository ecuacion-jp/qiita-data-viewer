package jp.ecuacion.app.qiitadataviewer.base.datatype;

import jakarta.validation.*;
import java.lang.annotation.*;
import jp.ecuacion.lib.validation.constraints.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = {})
@SizeString(min = 1, max = 100)
@PatternWithDescription(regexp = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]*$", description = "code")
@PatternWithDescription(regexp = "^[^!\"#\\$%&\\(\\)=\\^~\\\\\\|`\\[\\{;\\+:\\\\*\\]\\},<>/\\?]*$", description = "prohibitedChars")
public @interface CodeDataTypeValidator {

  String message() default "";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}
