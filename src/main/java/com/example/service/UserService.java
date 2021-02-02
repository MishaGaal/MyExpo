package com.example.service;


import com.example.dto.UserDTO;
import com.example.dto.UsersDTO;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.exception.UserException;
import com.example.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
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
            throw new UserException("No users found");
        }
        return new UsersDTO(users);
    }


    public User userSubmit(Integer id, UserDTO userDTO) throws UserException {
        try {
            return userRepository.save(User.configureUser(id, userDTO));
        } catch (Exception e) {
            throw new UserException("Update is invalid");
        }

    }

    public User registerNewUser(UserDTO userDTO) throws UserException {
        try {
            return userRepository.save(User.builder()
                    .username(userDTO.getUsername())
                    // .password(passwordEncoder.encode(userDTO.getPassword()));// Easy for development will be changed on release;
                    .password(userDTO.getPassword())
                    .email(userDTO.getEmail())
                    .roles(Collections.singleton(Role.USER))
                    .active(true)
                    .build());
        } catch (Exception e) {
            log.info("{}", "Cant register user: " + e.getMessage());
            throw new UserException("User already exists");
        }
    }

    public User findById(Integer id) throws UserException {
        return userRepository.findById(id).orElseThrow(() -> new UserException("Can't find user by id!"));
    }

    public UserDTO createDTO(Integer id) throws UserException {
        return new UserDTO(findById(id));
    }

}
