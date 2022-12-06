package com.abdm.sharegateway.co.validator;

import com.abdm.sharegateway.co.validator.impl.DuplicateHipCheckImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = DuplicateHipCheckImpl.class)
public @interface DuplicateHipCheck {

    String message() default "Hip already exist.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
