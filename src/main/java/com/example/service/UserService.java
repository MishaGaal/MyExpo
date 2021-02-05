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
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No such user"));
    }

    public UsersDTO getAllUsers() throws UserException {
        return new UsersDTO((List<User>) userRepository.findAll());
    }


    public User userSubmit(Integer id, UserDTO userDTO) {
        return userRepository.save(User.configureUser(id, userDTO));
    }

    public User registerNewUser(UserDTO userDTO) throws UserException {
            return userRepository.save(User.builder()
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .email(userDTO.getEmail())
                    .roles(Collections.singleton(Role.USER))
                    .active(true)
                    .build());

    }

    public User findById(Integer id) throws UserException {
        return userRepository.findById(id).orElseThrow(() -> new UserException("Can't find user by id!"));
    }

    public UserDTO createDTO(Integer id) throws UserException {
        return new UserDTO(findById(id));
    }

}
