package com.example.entity;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "expo_id")
    private Expo expo;


    private boolean bought = false;


    @ManyToMany(mappedBy = "userTickets")
    private Set<User> ticketUsers = new HashSet<>();


    public Ticket(Expo expo) {
        this.expo = expo;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
