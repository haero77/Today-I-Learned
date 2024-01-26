package com.example.demo.user.service.port;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;

import java.util.Optional;

public interface UserRepository {

    User getById(long id);

    User save(User user);

    Optional<User> findById(long id);

    Optional<User> findByIdAndStatus(long id, UserStatus userStatus);

    Optional<User> findByEmailAndStatus(String email, UserStatus userStatus);

}

/**
 * 1 대 다 때기 (떼고나서 기존 걸 동작하기 위해서 코드를 더 짜야됨)
 * 바운디드에 따라서 간점 참조
 * //
 */