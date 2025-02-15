package com.uniovi.notaneitor.validators;

import com.uniovi.notaneitor.complementarios.Professor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddProfessorValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Professor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Professor professor = (Professor) target;

        // Validar DNI: no vacío, longitud exacta de 9, y último carácter debe ser una letra
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
        if (professor.getDNI() != null) {
            String dni = professor.getDNI().trim();
            if (dni.length() != 9) {
                errors.rejectValue("dni", "Error.professor.dni.length");
            } else {
                char lastChar = dni.charAt(dni.length() - 1);
                if (!Character.isLetter(lastChar)) {
                    errors.rejectValue("dni", "Error.professor.dni.lastCharacter");
                }
            }
        }

        // Validar Nombre: no vacío y sin espacios en blanco al inicio o final
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.empty");
        if (professor.getName() != null && !professor.getName().equals(professor.getName().trim())) {
            errors.rejectValue("name", "Error.professor.name.whitespace");
        }

        // Validar Apellidos: no vacío y sin espacios en blanco al inicio o final
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Error.empty");
        if (professor.getSurname() != null && !professor.getSurname().equals(professor.getSurname().trim())) {
            errors.rejectValue("lastName", "Error.professor.lastName.whitespace");
        }
    }
}
