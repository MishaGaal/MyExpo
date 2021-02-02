package com.example.service;

import com.example.entity.Expo;
import com.example.entity.Ticket;
import com.example.entity.User;
import com.example.exception.ExpoException;
import com.example.exception.UserException;
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


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {ExpoException.class, SQLException.class, UserException.class})
    public Expo buyTicket(Integer id, Integer userId) throws ExpoException, UserException {
        Expo expo = expoRepository.findById(id).orElseThrow(() -> new ExpoException("Cant find  expo by Id"));  // multiple purchase
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("Cant find user by Id"));
        if (expo.getAmount() == 0) {
            throw new ExpoException("No more tickets left"); //rem
        }
        expo.setAmount(expo.getAmount() - 1);
        expo.getUsers().add(user);
        expo = expoRepository.save(expo);
       /* Ticket ticket = new Ticket(expo);
        ticket.setBought(true);
        ticket = ticketRepository.save(ticket);
        user.getUserTickets().add(ticket);*/
        user.getTickets().add(expo);
        userRepository.save(user);
        return expo;
    }

    public Page<Ticket> getUserTickets(Integer id) throws UserException {
        return new PageImpl<>(new ArrayList<>(userRepository.findById(id).orElseThrow(() -> new UserException("Cant find user by Id")).getUserTickets()));
    }

    public Page<Ticket> getViewStat(Pageable pageable) throws ExpoException {
        return ticketRepository.findByBoughtTrueOrderByTicketUsers(pageable).orElseThrow(() -> new ExpoException("No tickets have been bought"));
    }
}
