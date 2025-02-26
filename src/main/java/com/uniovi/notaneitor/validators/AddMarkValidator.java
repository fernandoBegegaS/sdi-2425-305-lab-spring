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

        // Validar descripción: no vacía y mínimo 20 caracteres
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
        if (mark.getDescription() != null && mark.getDescription().trim().length() < 20) {
            errors.rejectValue("description", "Error.mark.description.length",
                    "La descripcion debe ser 20 caracteres de largo");
        }

        // Validar puntuación: debe estar entre 0 y 10
        if (mark.getScore() == null) {
            errors.rejectValue("score", "Error.empty");
        } else if (mark.getScore() < 0 || mark.getScore() > 10) {
            errors.rejectValue("score", "Error.mark.score.range" ,
                    "La score debe ser 0 a 10");
        }
    }
}
