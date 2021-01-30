package com.example.dto;

import com.example.entity.Expo;
import com.example.entity.Holle;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Holle> holles;

    @NotBlank(message = "Please add 60.jpg as a default")
    private String imgName;

    @Min(value = 5, message = "Need more tickets")
    private int amount;

    @Min(value = 0, message = "Ticket price can't be lower than 0!")
    private double ticket_price;

    private Date startDate;
    private Date endDate;


    public ExpoDTO() {
        holles = new HashSet<>();
    }

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


    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public Set<Holle> getHolles() {
        return holles;
    }

    public void setHolles(Set<Holle> holles) {
        this.holles = holles;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_ua() {
        return title_ua;
    }

    public void setTitle_ua(String title_ua) {
        this.title_ua = title_ua;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_ua() {
        return description_ua;
    }

    public void setDescription_ua(String description_ua) {
        this.description_ua = description_ua;
    }
}
