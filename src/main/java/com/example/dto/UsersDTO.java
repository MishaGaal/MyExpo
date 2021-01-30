package com.example.dto;

import com.example.entity.User;

import java.util.List;

public class UsersDTO {
    private List<User> users;

    public UsersDTO() {
    }

    public UsersDTO(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void List(List<User> users) {
        this.users = users;
    }
}

