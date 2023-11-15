package com.network.analyzer.utility.validators.network;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VersionValidator implements ConstraintValidator<VersionValidation, String> {
    @Override
    public void initialize(VersionValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

            return value.equals("4") || value.equals("6");
    }
}
