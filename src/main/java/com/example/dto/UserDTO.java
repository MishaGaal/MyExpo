package com.example.dto;

import com.example.entity.Expo;
import com.example.entity.Role;
import com.example.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    @NotBlank(message = "Please enter username")
    @Length(max = 20, message = "Username is to long")
    private String username;

    @Length(min = 6, message = "Password is too short")
    private String password;

    @Length(min = 6, message = "Password cofirmation is too short")
    private String passwordConf;

    private String email;
    private Set<Role> roles;
    private Set<Expo> userExpos = new HashSet<>();

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDTO() {

    }


    public UserDTO(User byId) {
        this.username = byId.getUsername();
        this.password = byId.getPassword();
        this.roles = byId.getRoles();
    }

    public Set<Expo> getUserExpos() {
        return userExpos;
    }

    public void setUserExpos(Set<Expo> userExpos) {
        this.userExpos = userExpos;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordConf() {
        return passwordConf;
    }

    public void setPasswordConf(String passwordConf) {
        this.passwordConf = passwordConf;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
