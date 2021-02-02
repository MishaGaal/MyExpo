package com.example.dto;

import com.example.entity.Ticket;
import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class TicketsDTO {

    private Page<Ticket> tickets;

}
