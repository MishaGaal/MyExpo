package com.example.dto;

import com.example.entity.Expo;
import com.example.entity.Holle;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class ExpoDTO {

    @NotBlank(message = "Please fill title")
    @Length(max = 20, message = "Title is to long")
    private String title;
    @NotBlank(message = "Please fill title_ua")
    @Length(max = 20, message = "Title is to long")
    private String title_ua;
    @Length(min = 10, message = "Description is to short")
    private String description;
    @Length(min = 10, message = "Description_ua is to short")
    private String description_ua;
    private Set<Holle> holles = new HashSet<>();

    @Length(max = 10, message = "To long img name")
    private String imgName = "60.jpg";

    @Min(value = 5, message = "Need more tickets")
    private int amount;

    @Min(value = 0, message = "Ticket price can't be lower than 0!")
    private double ticket_price;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;


    public ExpoDTO(Expo expo) {
        this.title = expo.getTitle();
        this.title_ua = expo.getTitleUa();
        this.ticket_price = expo.getTicketPrice();
        this.description = expo.getDescription();
        this.description_ua = expo.getDescriptionUa();
        this.amount = expo.getAmount();
        this.startDate = expo.getStartDate();
        this.endDate = expo.getEndDate();
        this.imgName = expo.getImgName();
        this.holles = expo.getHolles();
    }

}
