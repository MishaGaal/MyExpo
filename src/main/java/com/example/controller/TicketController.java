package com.example.controller;


import com.example.entity.User;
import com.example.exception.ExpoException;
import com.example.service.TicketService;
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


@Controller
@RequestMapping("ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public String getTickets(@AuthenticationPrincipal User user, Model model) {
        try {
            model.addAttribute("tickets", ticketService.getUserTickets(user.getId()));
        } catch (ExpoException e) {
            System.err.println("Cant get all users");
        }
        return "ticket";
    }


    @GetMapping("stat")
    public String getAllUsers(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        try {
            model.addAttribute("tickets", ticketService.getViewStat(pageable));
        } catch (ExpoException e) {
            System.err.println("Cant get all users");
        }
        return "stat";
    }

    @GetMapping("{expo}/{user}/buy")
    public String expoBuy(@PathVariable(value = "expo") Integer id, @PathVariable(value = "user") Integer userId) {
        try {
            ticketService.buyTicket(id, userId);
        } catch (ExpoException e) {
            System.err.println("Can't buy ticket :" + e.getMessage());
            return "redirect:/main";
        }
        return "redirect:/main";
    }
}
