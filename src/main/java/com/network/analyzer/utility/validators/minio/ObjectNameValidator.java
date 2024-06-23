package com.network.analyzer.utility.validators.minio;

import com.network.analyzer.utility.validators.minio.annotations.ObjectNameValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ObjectNameValidator implements ConstraintValidator<ObjectNameValidation, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !s.isEmpty() && s.length() >= 1 && s.length() <= 1024;
    }
}
