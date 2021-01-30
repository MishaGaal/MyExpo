package com.example.service;

import com.example.entity.Expo;
import com.example.entity.Ticket;
import com.example.entity.User;
import com.example.exception.ExpoException;
import com.example.repository.ExpoRepository;
import com.example.repository.TicketRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;


@Service
public class TicketService {

    @Autowired
    ExpoRepository expoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {ExpoException.class, SQLException.class})
    public Expo buyTicket(Integer id, Integer userId) throws ExpoException {
        Expo expo = expoRepository.findById(id).orElseThrow(ExpoException::new);
        if (expo.getAmount() == 0) {
            throw new ExpoException("No more tickets left");
        }
        expo.setAmount(expo.getAmount() - 1);
        expo = expoRepository.save(expo);
        User user = userRepository.findById(userId).orElseThrow(ExpoException::new);
        Ticket ticket = new Ticket(expo);
        ticket.setBought(true);
        ticket = ticketRepository.save(ticket);
        user.getUserTickets().add(ticket);
        user = userRepository.save(user);
        if (expo == null || user == null || ticket == null) {
            throw new ExpoException("Transaction failed!");
        }
        return expo;
    }

    public Page<Ticket> getUserTickets(Integer id) throws ExpoException {
        return new PageImpl<>(new ArrayList<>(userRepository.findById(id).orElseThrow(ExpoException::new).getUserTickets()));
    }

    public Page<Ticket> getViewStat(Pageable pageable) throws ExpoException {
        return ticketRepository.findByBoughtTrueOrderByTicketUsers(pageable).orElseThrow(ExpoException::new);
    }
}
