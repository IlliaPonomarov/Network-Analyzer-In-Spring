package com.network.analyzer.utility.validators.network;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPValidator implements ConstraintValidator<IPValidation, String> {
    @Override
    public void initialize(IPValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String ip, ConstraintValidatorContext context) {
        String regexIPv4 = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";
        Pattern pattern = Pattern.compile(regexIPv4);
        Matcher matcher = pattern.matcher(ip);

        String regexIPv6 = "^([0-9a-fA-F]{1,4}:){7}([0-9a-fA-F]){1,4}$";
        Pattern pattern2 = Pattern.compile(regexIPv6);
        Matcher matcher2 = pattern2.matcher(ip);

        return matcher.matches() || matcher2.matches();
    }
}
