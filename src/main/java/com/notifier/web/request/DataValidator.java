package com.notifier.web.request;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DataValidator implements ConstraintValidator<DataCheck, LocalDateTime> {
    @Override
    public boolean isValid(LocalDateTime time, ConstraintValidatorContext constraintValidatorContext) {
        return LocalDateTime.now().isAfter(time) ? false : true;
    }
}
