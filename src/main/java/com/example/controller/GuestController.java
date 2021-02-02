package com.example.controller;


import com.example.service.ExpoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class GuestController {

    private final ExpoService expoService;

    @Autowired
    public GuestController(ExpoService expoService) {
        this.expoService = expoService;
    }

    @GetMapping("/index")
    public String getGuestMain(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.ASC, value = 6) Pageable pageable) {
        try {
            model.addAttribute("expos", expoService.findByExhibitedTrue(pageable));
        } catch (Exception e) {
            log.info("{}", "Cant find exhibited expos: " + e.getMessage());
        }
        return "index";
    }

    @GetMapping("/")
    public String getMain(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.ASC, value = 6) Pageable pageable) {
        try {
            model.addAttribute("expos", expoService.findByExhibitedTrue(pageable));
        } catch (Exception e) {
            log.info("{}", "Cant find exhibited expos: " + e.getMessage());
        }
        return "index";
    }
}
