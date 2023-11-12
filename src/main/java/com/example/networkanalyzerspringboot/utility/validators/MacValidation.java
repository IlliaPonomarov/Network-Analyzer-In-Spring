package com.example.networkanalyzerspringboot.utility.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MacValidator.class)
public @interface MacValidation {

    String message() default "Incorrect MAC address format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
