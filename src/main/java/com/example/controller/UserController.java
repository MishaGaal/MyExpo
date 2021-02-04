package com.example.controller;


import com.example.dto.UserDTO;
import com.example.entity.Role;
import com.example.exception.UserException;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        try {
            model.addAttribute("users", userService.getAllUsers().getUsers());
        } catch (UserException e) {
            log.info("{}", "Cant get all users: " + e.getMessage());
        }
        return "all";
    }


    @GetMapping("{user}")
    public String userEditForm(@PathVariable(value = "user") Integer id, Model model) {
        try {
            model.addAttribute("id", id);
            model.addAttribute("user", userService.createDTO(id));
            model.addAttribute("roles", Role.values());
        } catch (UserException e) {
            log.info("{}", "Cant find user info: " + e.getMessage());
        }
        return "userEdit";
    }

    @PostMapping
    public String userSubmit(@RequestParam("id") Integer id, UserDTO userDTO) {
            userService.userSubmit(id, userDTO);
        return "redirect:/user";
    }

}
