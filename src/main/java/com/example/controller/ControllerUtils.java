package com.example.controller;


import com.example.dto.UserDTO;
import com.example.exception.ValidationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Objects;

public class ControllerUtils {

    public static void validateMessage(BindingResult bindingResult, UserDTO userDTO) throws ValidationException {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            throw new ValidationException(errors.get(0).getDefaultMessage());
        }
        if (Objects.equals(userDTO.getPassword(), userDTO.getPasswordConf())) {
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
