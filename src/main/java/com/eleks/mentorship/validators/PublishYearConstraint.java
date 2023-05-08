package com.eleks.mentorship.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PublishYearValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PublishYearConstraint {
    String message() default "Publish year cannot be greater that current year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
