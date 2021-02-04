package com.example.service;

import com.example.entity.Expo;
import com.example.entity.Ticket;
import com.example.entity.User;
import com.example.exception.ExpoException;
import com.example.exception.UserException;
import com.example.repository.ExpoRepository;
import com.example.repository.TicketRepository;
import com.example.repository.UserRepository;
import com.example.util.StatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TicketService {

    @Autowired
    ExpoRepository expoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;


    @Transactional
    public Ticket buyTicket(Integer id, Integer userId) throws ExpoException, UserException {
        Expo expo = expoRepository.findById(id).orElseThrow(() -> new ExpoException("Cant find expo by Id"));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("Cant find user by Id"));
        return ticketRepository.save(new Ticket(expo, user));
    }


    public List<Ticket> getUserTickets(User user) throws UserException {
        return ticketRepository.findAllByUser(user).orElseThrow(() -> new UserException("No tickets for this user"));
    }

    public Page<StatUtils> getViewStat(Pageable pageable) throws ExpoException {
        return ticketRepository.countAllByOrderByExpo(pageable).orElseThrow(() -> new ExpoException("No tickets have been bought"));
    }
}
