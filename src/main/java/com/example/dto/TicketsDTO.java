package com.example.dto;

import com.example.entity.Ticket;
import org.springframework.data.domain.Page;

public class TicketsDTO {

    private Page<Ticket> tickets;

    public TicketsDTO() {
    }

    public TicketsDTO(Page<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Page<Ticket> getExpos() {
        return tickets;
    }

    public void setExpos(Page<Ticket> tickets) {
        this.tickets = tickets;
    }
}
