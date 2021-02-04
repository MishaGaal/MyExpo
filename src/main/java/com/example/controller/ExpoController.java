package com.example.controller;


import com.example.dto.ExpoDTO;
import com.example.entity.Expo;
import com.example.entity.Holle;
import com.example.exception.ExpoException;
import com.example.exception.ValidationException;
import com.example.service.ExpoService;
import com.example.util.ControllerUtils;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
            log.info("{}", "Cant find all expos: " + e.getMessage());
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
    public String expoSubmit(@Valid ExpoDTO expoDTO, BindingResult bindingResult, Model model) {
        try {
            ControllerUtils.validateMessage(bindingResult);
            expoService.addNewExpo(expoDTO);
        } catch (ValidationException e) {
            log.info("{}", "Validation Exception: " + e.getMessage());
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
            log.info("{}", "Cant find expo: " + e.getMessage());
        }
        return "expoEdit";
    }

    @PostMapping("/edit/{expo}")
    public String expoSubmit(@RequestParam("id") Integer id, @Valid ExpoDTO expoDTO, BindingResult bindingResult, Model model) {
        try {
            ControllerUtils.validateMessage(bindingResult);
            expoService.expoSubmit(id, expoDTO);
        } catch (ValidationException e) {
            log.info("{}", "Validation Exception: " + e.getMessage());
            model.addAttribute("expo", expoDTO);
        }
        return "redirect:/expo";
    }

    @GetMapping("delete/{expo}")
    public String expoDelete(@PathVariable Expo expo) {
        try {
            expoService.deleteExpo(expo);
        } catch (ExpoException e) {
            log.info("{}", "Cant delete expo: " + e.getMessage());
        }
        return "redirect:/expo";
    }


}
