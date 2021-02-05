package com.example.controller;

import com.example.entity.Expo;
import com.example.service.ExpoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class GuestControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpoService expoService;


    @Test
    public void getGuestMain() throws Exception {
        Expo[] resExp = {new Expo(), new Expo(), new Expo()};
        Page<Expo> res = new PageImpl<>(Arrays.asList(resExp.clone()));
        when(expoService.findByExhibitedTrue(null)).thenReturn(res);

        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getMain() throws Exception {
        Expo[] resExp = {new Expo(), new Expo(), new Expo()};
        Page<Expo> res = new PageImpl<>(Arrays.asList(resExp.clone()));
        when(expoService.findByExhibitedTrue(null)).thenReturn(res);

        this.mockMvc.perform(get("/index")).andDo(print()).andExpect(status().isOk());
    }
}