package com.example.util;

import com.example.entity.Ticket;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StatUtils {
    private Ticket ticket;
    private Long count;

    public StatUtils(Long count, Ticket ticket) {
        this.ticket = ticket;
        this.count = count;
    }

}
