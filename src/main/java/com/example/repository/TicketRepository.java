package com.example.repository;


import com.example.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    Optional<Page<Ticket>> findByBoughtTrueOrderByTicketUsers(Pageable pageable);

}
