package com.uniovi.notaneitor.validators;

import com.uniovi.notaneitor.entities.Mark;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddMarkValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Mark.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Mark mark = (Mark) target;

        // Comprobamos que la descripción no esté vacía o en blanco
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
        // Si la descripción existe, comprobamos que tenga más de 20 caracteres
        if (mark.getDescription() != null && mark.getDescription().trim().length() <= 20) {
            errors.rejectValue("description", "Error.mark.description.length",
                    "La descripción debe tener más de 20 caracteres.");
        }

        // Comprobamos que el score no sea nulo
        if (mark.getScore() == null) {
            errors.rejectValue("score", "Error.empty");
        } else if (mark.getScore() < 0 || mark.getScore() > 10) {
            errors.rejectValue("score", "Error.mark.score.range",
                    "El score debe estar entre 0 y 10.");
        }
    }
}
