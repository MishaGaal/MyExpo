package com.example.entity;


import com.example.dto.ExpoDTO;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString


@Entity
public class Expo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String titleUa;
    private String description;
    private String descriptionUa;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private int amount;
    private String imgName;
    private double ticketPrice;
    private boolean exhibited;


    @ElementCollection(targetClass = Holle.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "expo_holle", joinColumns = @JoinColumn(name = "expo_id"))
    @Enumerated(EnumType.STRING)
    private Set<Holle> holles;



    public static Expo buildAndConfigureExpo(Integer id, ExpoDTO expoDTO) {
        Expo expo = Expo.builder()
                .id(id)
                .imgName(expoDTO.getImgName())
                .title(expoDTO.getTitle())
                .titleUa(expoDTO.getTitle_ua())
                .description(expoDTO.getDescription())
                .descriptionUa(expoDTO.getDescription_ua())
                .startDate(expoDTO.getStartDate())
                .endDate(expoDTO.getEndDate())
                .amount(expoDTO.getAmount())
                .ticketPrice(expoDTO.getTicket_price())
                .holles(expoDTO.getHolles())
                .build();
        if (expoDTO.getHolles().size() == 0) {
            expo.setExhibited(false);
        } else {
            expo.setExhibited(true);
        }
        return expo;
    }

}
