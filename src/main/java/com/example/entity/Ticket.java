package com.example.entity;


import lombok.*;

import javax.persistence.*;



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


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Ticket(Expo expo, User user, Integer amount) {
        expo.setAmount(expo.getAmount() - amount);
        this.expo = expo;
        this.user = user;
    }

    @Override
    public String toString() {
        return id.toString();
    }


}
