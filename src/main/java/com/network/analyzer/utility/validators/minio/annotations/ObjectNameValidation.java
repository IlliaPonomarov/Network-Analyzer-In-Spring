package com.network.analyzer.utility.validators.minio.annotations;


import com.network.analyzer.utility.validators.ethernet.MacValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MacValidator.class)
public @interface ObjectNameValidation {

        String message() default "Incorrect object name format";
        Class<?>[] groups() default {};
        Class<?>[] payload() default {};
}
