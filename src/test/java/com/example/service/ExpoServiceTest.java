package com.example.service;

import com.example.dto.ExpoDTO;
import com.example.entity.Expo;
import com.example.exception.ExpoException;
import com.example.repository.ExpoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpoServiceTest {

    @Autowired
    ExpoService expoService;

    @MockBean
    ExpoRepository expoRepository;


    @Test
    public void getAllExpos() throws ExpoException {
        Expo[] s = {new Expo()};
        Page<Expo> res = new PageImpl<>(Arrays.asList(s));
        Mockito.doReturn(Optional.of(res)).when(expoRepository).findAll(ArgumentMatchers.any());
        expoService.getAllExpos(ArgumentMatchers.any());
        Mockito.verify(expoRepository, Mockito.times(1)).findAll(ArgumentMatchers.any());
    }

    @Test(expected = ExpoException.class)
    public void getNoExpos() throws ExpoException {
        expoService.getAllExpos(ArgumentMatchers.any());
        Mockito.verify(expoRepository, Mockito.times(1)).findAll(ArgumentMatchers.any());
    }


    @Test
    public void findByExhibitedTrue() throws ExpoException {
        Expo[] s = {new Expo()};
        Page<Expo> res = new PageImpl<>(Arrays.asList(s));
        Mockito.doReturn(Optional.of(res)).when(expoRepository).findByExhibitedTrue(ArgumentMatchers.any());
        expoService.findByExhibitedTrue(ArgumentMatchers.any());
        Mockito.verify(expoRepository, Mockito.times(1)).findByExhibitedTrue(ArgumentMatchers.any());
    }

    @Test
    public void addNewExpo() {
        expoService.addNewExpo(new ExpoDTO());
        Mockito.verify(expoRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }


    @Test
    public void expoSubmit() {
        expoService.expoSubmit(0, new ExpoDTO());
        Mockito.verify(expoRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    public void findByExhibitedTrueOrderByPriceDesc() throws ExpoException {
        Expo[] s = {new Expo()};
        Page<Expo> res = new PageImpl<>(Arrays.asList(s));
        Mockito.doReturn(Optional.of(res)).when(expoRepository).findByExhibitedTrueOrderByTicketPriceDesc(ArgumentMatchers.any());
        expoService.findByExhibitedTrueOrderByPriceDesc(ArgumentMatchers.any());
        Mockito.verify(expoRepository, Mockito.times(1)).findByExhibitedTrueOrderByTicketPriceDesc(ArgumentMatchers.any());
    }

    @Test(expected = ExpoException.class)
    public void failedByPriceDesc() throws ExpoException {
        expoService.findByExhibitedTrueOrderByPriceDesc(ArgumentMatchers.any());
        Mockito.verify(expoRepository, Mockito.times(1)).findByExhibitedTrueOrderByTicketPriceDesc(ArgumentMatchers.any());
    }

    @Test
    public void findByExhibitedTrueOrderByPriceAsc() throws ExpoException {
        Expo[] s = {new Expo()};
        Page<Expo> res = new PageImpl<>(Arrays.asList(s));
        Mockito.doReturn(Optional.of(res)).when(expoRepository).findByExhibitedTrueOrderByTicketPriceAsc(ArgumentMatchers.any());
        expoService.findByExhibitedTrueOrderByPriceAsc(ArgumentMatchers.any());
        Mockito.verify(expoRepository, Mockito.times(1)).findByExhibitedTrueOrderByTicketPriceAsc(ArgumentMatchers.any());
    }

    @Test(expected = ExpoException.class)
    public void failedByPriceAsc() throws ExpoException {
        expoService.findByExhibitedTrueOrderByPriceAsc(ArgumentMatchers.any());
        Mockito.verify(expoRepository, Mockito.times(1)).findByExhibitedTrueOrderByTicketPriceAsc(ArgumentMatchers.any());
    }

    @Test
    public void filterTheme() throws ExpoException {
        Expo[] s = {new Expo()};
        Page<Expo> res = new PageImpl<>(Arrays.asList(s));
        String theme = "test";
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        Mockito.doReturn(Optional.of(res)).when(expoRepository).findByExhibitedTrueAndDescriptionOrDescriptionUaContaining(theme, theme, pageable);
        expoService.filterTheme(theme, pageable);
        Mockito.verify(expoRepository, Mockito.times(1)).findByExhibitedTrueAndDescriptionOrDescriptionUaContaining(theme, theme, pageable);
    }

    @Test
    public void filterDates() throws ExpoException {
        Expo[] s = {new Expo()};
        Page<Expo> res = new PageImpl<>(Arrays.asList(s));
        Mockito.doReturn(Optional.of(res)).when(expoRepository).findByExhibitedTrueAndStartDateBetween(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
        expoService.filterDates(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verify(expoRepository, Mockito.times(1)).findByExhibitedTrueAndStartDateBetween(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    }
}