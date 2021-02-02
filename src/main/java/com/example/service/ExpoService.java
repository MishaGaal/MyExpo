package com.example.service;


import com.example.dto.ExpoDTO;
import com.example.dto.ExposDTO;
import com.example.entity.Expo;
import com.example.exception.ExpoException;
import com.example.repository.ExpoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class ExpoService {

    @Autowired
    ExpoRepository expoRepository;


    public Page<Expo> getAllExpos(Pageable pageable) throws ExpoException {
        return expoRepository.findAll(pageable).orElseThrow(() -> new ExpoException("No expos had been found"));
    }

    public Page<Expo> findByExhibitedTrue(Pageable pageable) throws ExpoException {
        return new ExposDTO(expoRepository.findByExhibitedTrue(pageable).orElseThrow(() -> new ExpoException("No expos or none exhibited"))).getExpos();
    }


    public Expo addNewExpo(ExpoDTO expoDTO) {
        return expoRepository.save(Expo.builder()
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
                .build());
    }


    public Expo expoSubmit(Integer id, ExpoDTO expoDTO) {
        return expoRepository.save(Expo.buildExpo(id, expoDTO));

    }


    public Expo deleteExpo(Expo expo) throws ExpoException {
        if (expo == null) {
            throw new ExpoException("Expo is null");
        }
        expoRepository.delete(expo);
        return expo;
    }


    public ExpoDTO createDTO(Integer id) throws ExpoException {
        Expo expo = expoRepository
                .findById(id)
                .orElseThrow(() -> new ExpoException("Can't find by id expo"));
        return new ExpoDTO(expo);
    }


    public Page<Expo> findByExhibitedTrueOrderByPriceDesc(Pageable pageable) throws ExpoException {
        return new ExposDTO(expoRepository
                .findByExhibitedTrueOrderByTicketPriceDesc(pageable)
                .orElseThrow(() -> new ExpoException("No expos or none exhibited")))
                .getExpos();
    }

    public Page<Expo> findByExhibitedTrueOrderByPriceAsc(Pageable pageable) throws ExpoException {
        return new ExposDTO(expoRepository
                .findByExhibitedTrueOrderByTicketPriceAsc(pageable)
                .orElseThrow(() -> new ExpoException("No expos or none exhibited")))
                .getExpos();
    }

    public Page<Expo> filterTheme(String theme, Pageable pageable) throws ExpoException {

        return
                new ExposDTO(
                        expoRepository
                                .findByExhibitedTrueAndDescriptionOrDescriptionUaContaining(theme, theme, pageable)
                                .orElseThrow(() -> new ExpoException("No expos or none exhibited or containing: " + theme)))
                        .getExpos();
    }

    public Page<Expo> filterDates(LocalDate startDate, LocalDate endDate, Pageable pageable) throws ExpoException {
        return
                new ExposDTO(
                        expoRepository
                                .findByExhibitedTrueAndStartDateBetween(startDate, endDate, pageable)
                                .orElseThrow(() -> new ExpoException("No expos or none exhibited or between dates: " + startDate + ", " + endDate)))
                        .getExpos();
    }


}
