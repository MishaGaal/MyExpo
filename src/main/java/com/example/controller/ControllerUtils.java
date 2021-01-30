package com.example.controller;

import com.example.dto.ExpoDTO;
import com.example.dto.UserDTO;
import com.example.exception.ExpoException;
import com.example.exception.UserException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerUtils {

    public static Map<String, String> gerErrors(BindingResult bindingResult) {
        return bindingResult
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(fieldError -> fieldError.getField() + "Error", FieldError::getDefaultMessage));
    }

    public static void validate(BindingResult bindingResult, UserDTO userDTO) throws UserException {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            throw new UserException(errors.get(0).getDefaultMessage());
        }
        if (userDTO.getPassword() != null && !userDTO.getPassword().equals(userDTO.getPasswordConf())) {
            throw new UserException("Passwords are different!");
        }
    }


    public static void validate(BindingResult bindingResult) throws ExpoException {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            throw new ExpoException((errors.get(0).getDefaultMessage()));
        }
    }

    public static void validate(Date startDate, Date endDate) throws ExpoException {
        if (startDate == null || endDate == null) {
            throw new ExpoException("Invalid dates");
        }
    }

    public static void validate(@Valid ExpoDTO expoDTO, BindingResult bindingResult) throws ExpoException {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            throw new ExpoException((errors.get(0).getDefaultMessage()));
        }
    }
}
