package com.example.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc // API Test 시에 MockMvc 자주 사용.
@AutoConfigureTestDatabase
@SqlGroup({
        @Sql(value = "/sql/user-controller-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("사용자는 특정 유저의 정보를 전달받을 수 있다.")
    @Test
    void getUserById() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1)) // JSON 데이터 검증
                .andExpect(jsonPath("$.email").value("kok202@naver.com"))
                .andExpect(jsonPath("$.nickname").value("kok202"))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }
    
    @DisplayName("사용자는 존재하지 않는 유저의 아이디로 api 호출할 경우 404 응답을 받는다.")
    @Test
    void getUserById_404() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(get("/api/user/12345679"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));// 응답이 JSON이 아니라 문자열인 경우 content() 이용
    }
    
}