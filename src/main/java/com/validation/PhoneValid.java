package com.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Constraint(validatedBy = PhoneValidator.class)

public @interface PhoneValid {

    String message() default "{Phone Number isn't valid, start with 0 and it have 10 number character!}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
