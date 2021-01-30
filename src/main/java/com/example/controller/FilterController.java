package com.example.controller;


import com.example.service.ExpoService;
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

import java.sql.Date;


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
            System.err.println("None found by price desc");
        }
        return "main";
    }

    @GetMapping("asc")
    public String ascDesc(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.ASC, value = 6) Pageable pageable) {
        try {
            model.addAttribute("expos", expoService.findByExhibitedTrueOrderByPriceAsc(pageable));
        } catch (Exception e) {
            System.err.println("None found by price asc");
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
            System.err.println("None found by filter");
        }
        return "main";
    }

    @PostMapping("dates")
    public String filterSubmit(Model model,
                               @RequestParam(defaultValue = "#{T(java.time.LocalDate).now()}") Date startDate,
                               @RequestParam(defaultValue = "#{T(java.time.LocalDate).now()}") Date endDate,
                               @PageableDefault(sort = "id", direction = Sort.Direction.ASC, value = 6) Pageable pageable) {

        try {
            model.addAttribute("expos", expoService.filterDates(startDate, endDate, pageable));
        } catch (Exception e) {
            System.err.println("None found by filter");
        }
        return "main";
    }

}
