package com.example.service;


import com.example.controller.ControllerUtils;
import com.example.dto.UserDTO;
import com.example.dto.UsersDTO;
import com.example.entity.User;
import com.example.exception.UserException;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).get();
    }

    public UsersDTO getAllUsers() throws UserException {
        List<User> users = (List<User>) userRepository.findAll();
        if (users == null) {
            throw new UserException("Can't get users");
        }
        return new UsersDTO(users);
    }

    public User findByUsername(UserDTO userDTO) throws UserException {
        return userRepository.findByUsername(userDTO.getUsername()).orElseThrow(UserException::new);
    }


    public User userSubmit(Integer id, UserDTO userDTO) throws UserException {
        User user = new User();
        user.setId(id);
        try {
            userRepository.save(User.configureUser(user, userDTO));
        } catch (Exception e) {
            System.err.println("SQLException");
            throw new UserException("Can't save user");
        }
        return user;
    }

    public User registerNewUser(UserDTO userDTO, BindingResult bindingResult) throws UserException {
        ControllerUtils.validate(bindingResult, userDTO);
        try {
            return userRepository.save(User.buildUser(new User(), userDTO));
        } catch (Exception e) {
            System.err.println("SQLException");
            throw new UserException("User already exists");
        }
    }

    public User findById(Integer id) throws UserException {
        return userRepository.findById(id).orElseThrow(UserException::new);
    }

    public UserDTO createDTO(Integer id) throws UserException {
        return new UserDTO(findById(id));
    }

}
