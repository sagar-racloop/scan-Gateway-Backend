package com.abdm.sharegateway.co.validator;

import com.abdm.sharegateway.co.validator.impl.HipValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = HipValidatorImpl.class)
public @interface HipValidator {

    String message() default "PayLoad error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
