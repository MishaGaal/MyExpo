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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {
    @Autowired
    TicketService ticketService;

    @MockBean
    UserRepository userRepository;
    @MockBean
    ExpoRepository expoRepository;
    @MockBean
    TicketRepository ticketRepository;


    @Test(expected = ExpoException.class)
    public void buyTicketRollback() throws ExpoException {
        Mockito.doReturn(Optional.of(new Expo())).when(expoRepository).findById(1);
        ticketService.buyTicket(1, new User());
        Mockito.verify(ticketRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    public void buyTicket() throws ExpoException {
        Expo expo = new Expo();
        expo.setAmount(7);
        Mockito.doReturn(Optional.of(expo)).when(expoRepository).findById(1);
        ticketService.buyTicket(1, new User());
        Mockito.verify(ticketRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }


    @Test(expected = UserException.class)
    public void getUserNoneTickets() throws UserException {
        ticketService.getUserTickets(new User());
        Mockito.verify(ticketRepository, Mockito.times(1)).findAllByUser(ArgumentMatchers.any());
    }

    @Test
    public void getViewStat() throws ExpoException {
        StatUtils[] s = {new StatUtils(12L, new Ticket())};

        Page<StatUtils> res = new PageImpl<>(Arrays.asList(s));
        Mockito.doReturn(Optional.of(res)).when(ticketRepository).countAllByOrderByExpo(ArgumentMatchers.any());

        ticketService.getViewStat(ArgumentMatchers.any());

        Mockito.verify(ticketRepository, Mockito.times(1)).countAllByOrderByExpo(ArgumentMatchers.any());

    }
}