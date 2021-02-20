package com.example.repository;


import com.example.entity.Ticket;
import com.example.entity.User;
import com.example.util.StatUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface TicketRepository extends CrudRepository<Ticket, Integer> {


    @Query("SELECT new com.example.util.StatUtils(COUNT(t.expo), t) FROM Ticket AS t GROUP BY t.expo ORDER BY COUNT(t.expo) DESC")
    Optional<Page<StatUtils>> countAllByOrderByExpo(Pageable pageable);

    Optional<List<Ticket>> findAllByUser(User user);

}
