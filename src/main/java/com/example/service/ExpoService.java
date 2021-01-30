package com.example.service;


import com.example.controller.ControllerUtils;
import com.example.dto.ExpoDTO;
import com.example.dto.ExposDTO;
import com.example.entity.Expo;
import com.example.exception.ExpoException;
import com.example.repository.ExpoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.sql.Date;


@Service
public class ExpoService {

    @Autowired
    ExpoRepository expoRepository;


    public Page<Expo> getAllExpos(Pageable pageable) throws ExpoException {
        return expoRepository.findAll(pageable).orElseThrow(ExpoException::new);
    }

    public Page<Expo> findByExhibitedTrue(Pageable pageable) throws ExpoException {
        return new ExposDTO(expoRepository.findByExhibitedTrue(pageable).orElseThrow(ExpoException::new)).getExpos();
    }


    public Expo addNewExpo(ExpoDTO expoDTO, BindingResult bindingResult) throws ExpoException {
        try {
            ControllerUtils.validate(bindingResult);
            return expoRepository.save(Expo.buildExpo(new Expo(), expoDTO));
        } catch (ExpoException e) {
            System.err.println("Validation Exception");
            throw new ExpoException(e.getMessage());
        }
    }


    public Expo expoSubmit(Integer id, ExpoDTO expoDTO, BindingResult bindingResult) throws ExpoException {
        ControllerUtils.validate(bindingResult);
        Expo expo = new Expo();
        expo.setId(id);
        expo = expoRepository.save(Expo.buildExpo(expo, expoDTO));
        return expo;
    }


    public Expo deleteExpo(Expo expo) throws ExpoException {
        if (expo == null) {
            throw new ExpoException("Expo is null");
        }
        expoRepository.delete(expo);
        return expo;
    }


    public ExpoDTO createDTO(Integer id) throws ExpoException {
        Expo expo = expoRepository.findById(id).orElseThrow(ExpoException::new);
        return new ExpoDTO(expo);
    }


    public Page<Expo> findByExhibitedTrueOrderByPriceDesc(Pageable pageable) throws ExpoException {
        return new ExposDTO(expoRepository.findByExhibitedTrueOrderByTicketPriceDesc(pageable).orElseThrow(ExpoException::new)).getExpos();
    }

    public Page<Expo> findByExhibitedTrueOrderByPriceAsc(Pageable pageable) throws ExpoException {
        return new ExposDTO(expoRepository.findByExhibitedTrueOrderByTicketPriceAsc(pageable).orElseThrow(ExpoException::new)).getExpos();
    }

    public Page<Expo> filterTheme(String theme, Pageable pageable) throws ExpoException {

        return
                new ExposDTO(
                        expoRepository
                                .findByExhibitedTrueAndDescriptionOrDescriptionUaContaining(theme, theme, pageable)
                                .orElseThrow(ExpoException::new))
                        .getExpos();
    }

    public Page<Expo> filterDates(Date startDate, Date endDate, Pageable pageable) throws ExpoException {
        return
                new ExposDTO(
                        expoRepository
                                .findByExhibitedTrueAndStartDateBetween(startDate, endDate, pageable)
                                .orElseThrow(ExpoException::new))
                        .getExpos();
    }


}
