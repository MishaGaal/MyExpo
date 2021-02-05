package com.example.service;

import com.example.dto.UserDTO;
import com.example.exception.UserException;
import com.example.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {


    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Test
    public void getAllUsers() throws UserException {
        userService.getAllUsers();
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void userSubmit() {
        UserDTO user = new UserDTO();
        user.setUsername("Test");
        user.setRoles(new HashSet<>());
        userService.userSubmit(0, user);
        Mockito.verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any());

    }


    @Test
    public void registerNewUser() throws UserException {
        UserDTO user = new UserDTO();
        user.setUsername("Test");
        user.setPassword("test123456");
        user.setRoles(new HashSet<>());
        userService.registerNewUser(user);
        Mockito.verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test(expected = UserException.class)
    public void findById() throws UserException {
        userService.findById(1);
        Mockito.verify(userRepository, Mockito.times(1)).findById(ArgumentMatchers.anyInt());

    }
}