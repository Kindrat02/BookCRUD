package com.eleks.mentorship.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class PublishYearValidator implements ConstraintValidator<PublishYearConstraint, Integer> {
    @Override
    public void initialize(PublishYearConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer publishYear, ConstraintValidatorContext constraintValidatorContext) {
        return publishYear <= Year.now().getValue();
    }
}
