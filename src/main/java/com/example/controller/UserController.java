package com.example.controller;


import com.example.dto.UserDTO;
import com.example.entity.Role;
import com.example.exception.UserException;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
            System.err.println("Cant get all users");
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
            System.err.println("Cant find user info");
        }
        return "userEdit";
    }

    @PostMapping
    public String userSubmit(@RequestParam("id") Integer id, UserDTO userDTO) {
        try {
            userService.userSubmit(id, userDTO);
        } catch (UserException e) {
            System.err.println("Cant edit user");
        }
        return "redirect:/user";
    }

}
