package com.example.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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


    public Ticket() {
    }

    public Ticket(Expo expo) {
        this.expo = expo;
    }


    public Expo getExpo() {
        return expo;
    }

    public void setExpo(Expo expo) {
        this.expo = expo;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public Set<User> getTicketUsers() {
        return ticketUsers;
    }

    public void setTicketUsers(Set<User> ticketUsers) {
        this.ticketUsers = ticketUsers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
