package com.example.util;


import com.example.dto.UserDTO;
import com.example.exception.ValidationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ControllerUtils {

    public static void validateMessage(BindingResult bindingResult, UserDTO userDTO) throws ValidationException {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            throw new ValidationException(errors.get(0).getDefaultMessage());
        }
        if (!userDTO.getPassword().equals(userDTO.getPasswordConf())) {
            throw new ValidationException("Passwords are different!");
        }
    }

    public static void validateMessage(BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            throw new ValidationException((errors.get(0).getDefaultMessage()));
        }
    }


}
