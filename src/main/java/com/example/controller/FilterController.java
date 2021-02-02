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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("expos")
public class FilterController {

    private final ExpoService expoService;

    @Autowired
    public FilterController(ExpoService expoService) {
        this.expoService = expoService;
    }

    @GetMapping
    public String createFilter() {
        return "main";
    }

    @GetMapping("desc")
    public String exposDesc(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.ASC, value = 6) Pageable pageable) {
        try {
            model.addAttribute("expos", expoService.findByExhibitedTrueOrderByPriceDesc(pageable));
        } catch (Exception e) {
            log.info("{}", "Cant find by desc price expos: " + e.getMessage());
        }
        return "main";
    }

    @GetMapping("asc")
    public String ascDesc(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.ASC, value = 6) Pageable pageable) {
        try {
            model.addAttribute("expos", expoService.findByExhibitedTrueOrderByPriceAsc(pageable));
        } catch (Exception e) {
            log.info("{}", "Cant find by asc price expos: " + e.getMessage());
        }
        return "main";
    }

    @PostMapping()
    public String filterSubmit(Model model,
                               @RequestParam String theme,
                               @PageableDefault(sort = "id", direction = Sort.Direction.ASC, value = 6) Pageable pageable) {
        try {
            model.addAttribute("expos", expoService.filterTheme(theme, pageable));
        } catch (Exception e) {
            log.info("{}", "Cant find by theme expos: " + e.getMessage());
        }
        return "main";
    }

    @PostMapping("dates")
    public String filterSubmit(Model model,
                               @RequestParam(defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate startDate,
                               @RequestParam(defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate endDate,
                               @PageableDefault(sort = "id", direction = Sort.Direction.ASC, value = 6) Pageable pageable) {

        try {
            model.addAttribute("expos", expoService.filterDates(startDate, endDate, pageable));
        } catch (Exception e) {
            log.info("{}", "Cant find by dates expos: " + e.getMessage());
        }
        return "main";
    }

}
