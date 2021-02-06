package com.example.controller;


import com.example.entity.User;
import com.example.exception.ExpoException;
import com.example.exception.UserException;
import com.example.service.TicketService;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("ticket")
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;

    @Autowired
    public TicketController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }


    @GetMapping
    public String getTickets(@AuthenticationPrincipal User user, Model model) {
        try {
            model.addAttribute("tickets", ticketService.getUserTickets(user));
        } catch (UserException e) {
            log.info("{}", "Cant get all users: " + e.getMessage());
        }
        return "ticket";
    }


    @GetMapping("stat")
    public String getAllUsers(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        try {
            model.addAttribute("tickets", ticketService.getViewStat(pageable));
        } catch (ExpoException e) {
            log.info("{}", "Cant get all users: " + e.getMessage());
        }
        return "stat";
    }

    @GetMapping("{expo}/buy")
    public String expoBuy(@PathVariable(value = "expo") Integer id, @AuthenticationPrincipal User user) {
        try {
            ticketService.buyTicket(id, user);
        } catch (ExpoException e) {
            log.info("{}", "Cant buy ticket: " + e.getMessage());
            return "redirect:/main";
        }
        return "redirect:/ticket";
    }
}
