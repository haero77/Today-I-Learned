package com.example.demo.user.domain;

import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
import com.example.demo.mock.TestClockHolder;
import com.example.demo.mock.TestUuidHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    // TODO: DisplayName 변경? ex) User 를 생성 시 상태는 PENDING 이다.
    @DisplayName("UserCreate 객체로 생성할 수 있다.")
    @Test
    void createUserByUserCreate() {
        // given
        UserCreate userCreate = UserCreate.builder()
                .email("example@email.com")
                .address("address")
                .nickname("nickname")
                .build();

        // when
        User user = User.from(userCreate, new TestUuidHolder("12345678-1234-1234-1234-123456789012"));

        // then
        assertThat(user.getId()).isNull();
        assertThat(user.getEmail()).isEqualTo("example@email.com");
        assertThat(user.getAddress()).isEqualTo("address");
        assertThat(user.getNickname()).isEqualTo("nickname");
        assertThat(user.getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(user.getCertificationCode()).isEqualTo("12345678-1234-1234-1234-123456789012");
    }

    @DisplayName("UserUpdate 객체로 데이터를 업데이트할 수 있다.")
    @Test
    void updateUserByUserUpdate() {
        // given
        User user = User.builder()
                .id(1L)
                .email("example@email.com")
                .nickname("nickname")
                .address("address")
                .certificationCode("12345678-1234-1234-1234-123456789012")
                .lastLoginAt(100L)
                .status(UserStatus.ACTIVE)
                .build();

        UserUpdate userUpdate = UserUpdate.builder()
                .nickname("newNickname")
                .address("newAddress")
                .build();

        // when
        user = user.update(userUpdate);

        // then
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getEmail()).isEqualTo("example@email.com");
        assertThat(user.getAddress()).isEqualTo("newAddress");
        assertThat(user.getNickname()).isEqualTo("newNickname");
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(user.getLastLoginAt()).isEqualTo(100L);
        assertThat(user.getCertificationCode()).isEqualTo("12345678-1234-1234-1234-123456789012");
    }

    @DisplayName("User 는 로그인 할 수 있고, 로그인 시 마지막 로그인 시간이 변경된다.")
    @Test
    void login() {
        // given
        User user = User.builder()
                .id(1L)
                .email("example@email.com")
                .nickname("nickname")
                .address("address")
                .certificationCode("12345678-1234-1234-1234-123456789012")
                .lastLoginAt(100L)
                .status(UserStatus.ACTIVE)
                .build();

        long lastLoginAt = 200L;

        // when
        user = user.login(new TestClockHolder(lastLoginAt));

        // then
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getNickname()).isEqualTo("nickname");
        assertThat(user.getEmail()).isEqualTo("example@email.com");
        assertThat(user.getAddress()).isEqualTo("address");
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(user.getLastLoginAt()).isEqualTo(lastLoginAt);
        assertThat(user.getCertificationCode()).isEqualTo("12345678-1234-1234-1234-123456789012");
    }

    @DisplayName("유효한 인증 코드로 계정을 활성화 할 수 있다.")
    @Test
    void certificate() {
        // given
        String certificationCode = "12345678-1234-1234-1234-123456789012";

        User user = User.builder()
                .id(1L)
                .email("example@email.com")
                .nickname("nickname")
                .address("Seoul")
                .certificationCode(certificationCode)
                .lastLoginAt(100L)
                .status(UserStatus.PENDING)
                .build();

        // when
        user = user.certificate(certificationCode);

        // then
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @DisplayName("잘못된 인증 코드로 계정을 활성화 시 에러를 발생시킨다.")
    @Test
    void certificate_with_wrong_certifcationCode() {
        // given
        User user = User.builder()
                .id(1L)
                .email("example@email.com")
                .nickname("nickname")
                .address("Seoul")
                .certificationCode("12345678-1234-1234-1234-123456789012")
                .lastLoginAt(100L)
                .status(UserStatus.PENDING)
                .build();

        String invalidCertificationCode = "0000";

        // when
        // then
        assertThatThrownBy(() -> user.certificate(invalidCertificationCode))
                .isInstanceOf(CertificationCodeNotMatchedException.class)
                .hasMessage("자격 증명에 실패하였습니다.");
    }

}