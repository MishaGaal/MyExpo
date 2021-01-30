package com.example.controller;


import com.example.dto.ExpoDTO;
import com.example.entity.Expo;
import com.example.entity.Holle;
import com.example.exception.ExpoException;
import com.example.service.ExpoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/expo")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class ExpoController {

    private final ExpoService expoService;

    @Autowired
    public ExpoController(ExpoService expoService) {
        this.expoService = expoService;
    }

    @GetMapping
    public String getAllExpos(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.ASC, value = 6) Pageable pageable) {
        try {
            model.addAttribute("expos", expoService.getAllExpos(pageable));
        } catch (ExpoException e) {
            System.err.println("Can't get expos!");
        }
        return "expo";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("holles", Holle.values());
        model.addAttribute("expo", new ExpoDTO());
        return "addExpo";
    }

    @PostMapping("/add")
    public String userSubmit(@Valid ExpoDTO expoDTO, BindingResult bindingResult, Model model) {
        try {
            expoService.addNewExpo(expoDTO, bindingResult);
        } catch (ExpoException e) {
            System.err.println("Can't add new expo");
            model.addAttribute("expo", expoDTO);
            model.addAttribute("errorMessage", e.getMessage());
            return "addExpo";
        }
        return "redirect:/expo";
    }


    @GetMapping("/edit/{expo}")
    public String expoEditForm(@PathVariable(value = "expo") Integer id, Model model) {
        try {
            model.addAttribute("id", id);
            model.addAttribute("expo", expoService.createDTO(id));
            model.addAttribute("holles", Holle.values());
        } catch (Exception e) {
            System.err.println("Can't create expo: " + e.getMessage());
        }
        return "expoEdit";
    }

    @PostMapping("/edit/{expo}")
    public String expoSubmit(@RequestParam("id") Integer id, @Valid ExpoDTO expoDTO, BindingResult bindingResult) {
        try {
            expoService.expoSubmit(id, expoDTO, bindingResult);
        } catch (ExpoException e) {
            System.err.println("Can't edit expo: " + e.getMessage());
        }
        return "redirect:/expo";
    }

    @GetMapping("delete/{expo}")
    public String expoDelete(@PathVariable Expo expo) {
        try {
            expoService.deleteExpo(expo);
        } catch (ExpoException e) {
            System.err.println("Can't delete expo");
        }
        return "redirect:/expo";
    }


}
