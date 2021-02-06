package com.example.controller;

import com.example.entity.Expo;
import com.example.service.ExpoService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
        Expo expo = Expo.builder()
                .title("Test Title")
                .build();
        Expo[] resExp = {expo};
        Page<Expo> res = new PageImpl<>(Arrays.asList(resExp.clone()));
        Mockito.doReturn(res).when(expoService).findByExhibitedTrue(ArgumentMatchers.any());
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test Title")));
    }

    @Test
    public void getMain() throws Exception {
        Expo expo = Expo.builder()
                .title("Test Title")
                .build();
        Expo[] resExp = {expo};
        Page<Expo> res = new PageImpl<>(Arrays.asList(resExp.clone()));
        Mockito.doReturn(res).when(expoService).findByExhibitedTrue(ArgumentMatchers.any());
        this.mockMvc.perform(get("/index")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test Title")));
    }
}