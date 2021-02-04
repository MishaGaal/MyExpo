package com.example.entity;


import com.example.exception.ExpoException;
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

    public Ticket(Expo expo, User user) throws ExpoException {
        if (expo.getAmount() == 0) {
            throw new ExpoException("No more tickets left");
        }
        expo.setAmount(expo.getAmount() - 1);
        this.expo = expo;
        this.user = user;
    }

    @Override
    public String toString() {
        return id.toString();
    }


}
