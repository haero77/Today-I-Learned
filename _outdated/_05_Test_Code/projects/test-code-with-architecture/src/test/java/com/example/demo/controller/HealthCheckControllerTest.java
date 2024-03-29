package com.example.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // API Test 시에 MockMvc 자주 사용.
@AutoConfigureTestDatabase
class HealthCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("헬스 체크 응답이 200으로 내려온다.")
    @Test
    void healthCheck() throws Exception {
        mockMvc.perform(get("/health_check.html"))
                .andExpect(status().isOk());
    }

}