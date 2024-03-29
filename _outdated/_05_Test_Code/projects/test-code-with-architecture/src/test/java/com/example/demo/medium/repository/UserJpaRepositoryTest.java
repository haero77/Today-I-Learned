package com.example.demo.medium.repository;

import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.infrastructure.persistence.entity.UserEntity;
import com.example.demo.user.infrastructure.persistence.repository.UserJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql("/sql/user-repository-test-data.sql")
class UserJpaRepositoryTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @DisplayName("findByIdAndStatus 로 유저 데이터를 찾아올 수 있다.")
    @Test
    void findByIdAndStatus() {
        // given
        // when
        Optional<UserEntity> result = userJpaRepository.findByIdAndStatus(1, UserStatus.ACTIVE);

        // then
        assertThat(result.isPresent()).isNotNull();
    }

    @DisplayName("findByIdAndStatus 는 데이터가 없으면 Optional emtpy 를 내려준다.")
    @Test
    void findByIdAndStatus2() {
        // given
        // when
        Optional<UserEntity> result = userJpaRepository.findByIdAndStatus(1, UserStatus.PENDING);

        // then
        assertThat(result.isEmpty()).isTrue();
    }

    @DisplayName("findByEmailAndStatus 로 유저 데이터를 찾아올 수 있다.")
    @Test
    void findByEmailAndStatus() {
        // given
        // when
        Optional<UserEntity> result = userJpaRepository.findByEmailAndStatus("email", UserStatus.ACTIVE);

        // then
        assertThat(result.isPresent()).isNotNull();
    }

    @DisplayName("findByEmailAndStatus 는 데이터가 없으면 Optional emtpy 를 내려준다.")
    @Test
    void findByEmailAndStatus2() {
        // given
        // when
        Optional<UserEntity> result = userJpaRepository.findByEmailAndStatus("email", UserStatus.PENDING);

        // then
        assertThat(result.isEmpty()).isTrue();
    }

}