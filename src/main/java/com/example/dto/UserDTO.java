package com.example.dto;

import com.example.entity.Expo;
import com.example.entity.Role;
import com.example.entity.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

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

    public UserDTO(User byId) {
        this.username = byId.getUsername();
        this.password = byId.getPassword();
        this.roles = byId.getRoles();
    }

}
