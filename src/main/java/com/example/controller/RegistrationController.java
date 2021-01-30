package com.example.controller;


import com.example.dto.UserDTO;
import com.example.exception.UserException;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


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
            userService.registerNewUser(userDTO, bindingResult);
        } catch (UserException e) {
            System.err.println(e.getMessage());
            model.addAttribute("user", userDTO);
            model.addAttribute("errorMessage", e.getMessage());
            return "registration";
        }
        return "redirect:/login";
    }

}
