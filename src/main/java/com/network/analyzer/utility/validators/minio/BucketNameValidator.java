package com.network.analyzer.utility.validators.minio;

import com.network.analyzer.utility.validators.minio.annotations.BucketNameValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BucketNameValidator implements ConstraintValidator<BucketNameValidation, String> {
    @Override
    public void initialize(BucketNameValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty() && value.length() >= 3 && value.length() <= 63;
    }
}
