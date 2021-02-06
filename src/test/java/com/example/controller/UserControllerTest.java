package com.example.controller;


import com.example.dto.UsersDTO;
import com.example.entity.User;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    // @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void getAllUsers() throws Exception {

        User user = User.builder().username("Test username").password("123456").active(true).build();
        Mockito.doReturn(new UsersDTO(Arrays.asList(new User[]{user}))).when(userService).getAllUsers();
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test username")));
    }

    @Test
    public void userEditForm() {
    }

    @Test
    public void userSubmit() {
    }
}