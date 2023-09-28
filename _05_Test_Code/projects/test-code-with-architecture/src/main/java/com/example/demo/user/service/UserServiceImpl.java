package com.example.demo.user.service;

import com.example.demo.common.domain.exception.ResourceNotFoundException;
import com.example.demo.common.service.port.ClockHolder;
import com.example.demo.common.service.port.UuidHolder;
import com.example.demo.user.controller.port.UserService;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserCreate;
import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.domain.UserUpdate;
import com.example.demo.user.service.port.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Builder
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CertificationServiceImpl certificationService;
    private final UuidHolder uuidHolder;
    private final ClockHolder clockHolder;

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmailAndStatus(email, UserStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Users", email));
    }

    /**
     * get 은 애초에 데이터가 없으면 에러를 던진다는 뜻을 내포한다.
     */
    @Override
    public User getById(long id) {
        return userRepository.findByIdAndStatus(id, UserStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Users", id));
    }

    /**
     * createUser() -> create()
     * UserService 자체가 User에 대한 책임을 갖고 있기 때문에, create 만 해도 User를 생성한다는 의미를 갖는다.
     *
     * @param userCreate
     * @return
     */
    @Override
    @Transactional
    public User create(UserCreate userCreate) {
        User user = User.from(userCreate, uuidHolder);
        user = userRepository.save(user);

        certificationService.send(user.getEmail(), user.getId(), user.getCertificationCode());

        return user;
    }

    @Override
    @Transactional
    public User update(long id, UserUpdate userUpdate) {
        User user = getById(id);

        user = user.update(userUpdate);

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void login(long id) {
        User user = getById(id);

        user = user.login(clockHolder);

        userRepository.save(user); // 변경한 객체가 영속성 객체가 아니라 도메인 객체이기 때문에 save 까지 진행 (JPA 의존성이 사라지면서 더티 체킹이 안 된다.)
    }

    @Override
    @Transactional
    public void verifyEmail(long id, String certificationCode) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Users", id));

        user = user.certificate(certificationCode);

        userRepository.save(user);
    }

}