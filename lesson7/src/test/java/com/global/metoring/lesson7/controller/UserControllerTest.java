package com.global.metoring.lesson7.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void testShouldCheckGetAllUsersRequest() {
        mockMvc
                .perform(get("http://localhost:8080/users/all"))
                .andDo(print())
                .andExpect(status()
                        .isOk());
    }

    @Test
    @SneakyThrows
    public void testShouldCheckPostUserRequest() {
        mockMvc
                .perform(post("http://localhost:8080/users/add"))
                .andDo(print())
                .andExpect(status()
                        .isOk());
    }

}