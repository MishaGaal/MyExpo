package com.example.dto;

import com.example.entity.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class UsersDTO {
    private List<User> users;

}

