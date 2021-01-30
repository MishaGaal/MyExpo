package com.example.entity;


import com.example.dto.ExpoDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Expo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String titleUa;
    private String description;
    private String descriptionUa;
    private Date startDate;
    private Date endDate;
    private int amount;
    private String imgName;
    private double ticketPrice;
    private boolean exhibited;


    @ElementCollection(targetClass = Holle.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "expo_holle", joinColumns = @JoinColumn(name = "expo_id"))
    @Enumerated(EnumType.STRING)
    private Set<Holle> holles;

    public static Expo buildExpo(Expo expo, ExpoDTO expoDTO) {
        if (expoDTO.getImgName() == null) {
            expo.setImgName("60.jpg");
        }
        expo.setImgName(expoDTO.getImgName());
        expo.setTitle(expoDTO.getTitle());
        expo.setTitleUa(expoDTO.getTitle_ua());
        expo.setDescription(expoDTO.getDescription());
        expo.setDescriptionUa(expoDTO.getDescription_ua());
        expo.setStartDate(expoDTO.getStartDate());
        expo.setEndDate(expoDTO.getEndDate());
        expo.setAmount(expoDTO.getAmount());
        expo.setTicketPrice(expoDTO.getTicket_price());
        if (expo.getHolles() != null) {
            expo.getHolles().clear();
        }
        expo.setHolles(expoDTO.getHolles());
        if (expoDTO.getHolles().size() == 0) {
            expo.setExhibited(false);
        } else {
            expo.setExhibited(true);
        }
        return expo;
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

    public boolean isExhibited() {
        return exhibited;
    }

    public void setExhibited(boolean exhibited) {
        this.exhibited = exhibited;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticket_price) {
        this.ticketPrice = ticket_price;
    }

    public Set<Holle> getHolles() {
        return holles;
    }

    public void setHolles(Set<Holle> holles) {
        this.holles = holles;
    }

    public String getTitleUa() {
        return titleUa;
    }

    public void setTitleUa(String title_ua) {
        this.titleUa = title_ua;
    }

    public String getDescriptionUa() {
        return descriptionUa;
    }

    public void setDescriptionUa(String description_ua) {
        this.descriptionUa = description_ua;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
