package com.example.controller;


import com.example.dto.UserDTO;
import com.example.exception.UserException;
import com.example.exception.ValidationException;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String userSubmit(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        try {
            ControllerUtils.validateMessage(bindingResult, userDTO);
            userService.registerNewUser(userDTO);
        } catch (UserException | ValidationException e) {
            log.info("{}", "User registration failed: " + e.getMessage());
            model.addAttribute("user", userDTO);
            model.addAttribute("errorMessage", e.getMessage());
            return "registration";
        }
        return "redirect:/login";
    }

}
