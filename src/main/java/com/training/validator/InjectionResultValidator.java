package com.training.validator;

import com.training.model.InjectionResult;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class InjectionResultValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return InjectionResult.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        InjectionResult injectionResult = (InjectionResult) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customer", "NotEmpty.injectionResult");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prevention", "NotEmpty.injectionResult");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vaccine", "NotEmpty.injectionResult");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "injectionPlace", "NotEmpty.injectionResult");

        if (injectionResult.getNumberOfInjection() >= 1) {
            if (injectionResult.getInjectionDate() == null && injectionResult.getNextInjectionDate() == null) {
                errors.rejectValue("injectionDate", "NotEmpty.injectionResult.date");
            }
            if (injectionResult.getNumberOfInjection() == 1 && injectionResult.getInjectionDate() != null && injectionResult.getNextInjectionDate() != null) {
                errors.rejectValue("injectionDate", "Only.injectionResult.date");
            }
            if (injectionResult.getNumberOfInjection() > 1 && injectionResult.getInjectionDate() != null) {
                if (injectionResult.getNextInjectionDate() == null) {
                    errors.rejectValue("nextInjectionDate", "NotEmpty.injectionResult");
                } else if (injectionResult.getNextInjectionDate().before(injectionResult.getInjectionDate())) {
                    errors.rejectValue("nextInjectionDate", "Pattern.injectionResult.nextDate");
                }
            }
        } else  if (injectionResult.getInjectionDate() != null || injectionResult.getNextInjectionDate() != null) {
            errors.rejectValue("injectionDate", "Empty.injectionResult.date");
        }
    }
}
