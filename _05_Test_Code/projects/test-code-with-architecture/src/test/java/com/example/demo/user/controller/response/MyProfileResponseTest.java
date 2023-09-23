package com.example.demo.user.controller.response;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyProfileResponseTest {

    @DisplayName("User 로 응답을 생성할 수 있다.")
    @Test
    void create() {
        // given
        User user = User.builder()
                .id(1L)
                .email("example@email.com")
                .nickname("nickname")
                .address("address")
                .certificationCode("certificationCode")
                .lastLoginAt(100L)
                .status(UserStatus.ACTIVE)
                .build();

        // when
        MyProfileResponse myProfileResponse = MyProfileResponse.from(user);

        // then
        assertThat(myProfileResponse.getId()).isEqualTo(1L);
        assertThat(myProfileResponse.getEmail()).isEqualTo("example@email.com");
        assertThat(myProfileResponse.getNickname()).isEqualTo("nickname");
        assertThat(myProfileResponse.getAddress()).isEqualTo("address");
        assertThat(myProfileResponse.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(myProfileResponse.getLastLoginAt()).isEqualTo(100L);
    }

}