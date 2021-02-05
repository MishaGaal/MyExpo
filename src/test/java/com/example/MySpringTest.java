package com.example;

import com.example.controller.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class MySpringTest {

    @Autowired
    private ExpoController expoController;
    @Autowired
    private FilterController filterController;
    @Autowired
    private GuestController guestController;
    @Autowired
    private MainController mainController;
    @Autowired
    private RegistrationController registrationController;
    @Autowired
    private TicketController ticketController;
    @Autowired
    private UserController userController;


    @Test
    public void contextLoads() {
        assertThat(expoController).isNotNull();
        assertThat(filterController).isNotNull();
        assertThat(guestController).isNotNull();
        assertThat(mainController).isNotNull();
        assertThat(registrationController).isNotNull();
        assertThat(ticketController).isNotNull();
        assertThat(userController).isNotNull();
    }
}