package com.example.demo.mock;

import com.example.demo.common.service.port.ClockHolder;
import com.example.demo.common.service.port.UuidHolder;
import com.example.demo.post.controller.port.PostService;
import com.example.demo.post.service.PostServiceImpl;
import com.example.demo.post.service.port.PostRepository;
import com.example.demo.user.controller.UserController;
import com.example.demo.user.controller.UserCreateController;
import com.example.demo.user.service.CertificationService;
import com.example.demo.user.service.UserServiceImpl;
import com.example.demo.user.service.port.MailSender;
import com.example.demo.user.service.port.UserRepository;
import lombok.Builder;

public class TestContainer {

    public final MailSender mailSender;
    public final UserRepository userRepository;
    public final PostRepository postRepository;
    public final UserCreateService userCreateService;
    public final UserReadService userReadService;
    public final UserUpdateService userUpdateService;
    public final AuthenticationService authenticationService;
    public final PostService postService;
    public final CertificationService certificationService;
    public final UserController userController;
    public final UserCreateController userCreateController;

    @Builder
    private TestContainer(ClockHolder clockHolder, UuidHolder uuidHolder) {
        this.mailSender = new FakeMailSender();
        this.userRepository = new FakeUserRepository();
        this.postRepository = new FakePostRepository();

        this.postService = PostServiceImpl.builder()
                .postRepository(this.postRepository)
                .userRepository(this.userRepository)
                .clockHolder(clockHolder)
                .build();

        this.certificationService = new CertificationService(this.mailSender);
        UserServiceImpl userService = UserServiceImpl.builder()
                .userRepository(userRepository)
                .uuidHolder(uuidHolder)
                .clockHolder(clockHolder)
                .certificationService(this.certificationService)
                .build();
        this.userCreateService = userService;
        this.userReadService = userService;
        this.userUpdateService = userService;
        this.authenticationService = userService;

        this.userController = UserController.builder()
                .userCreateService(this.userCreateService)
                .userReadService(this.userReadService)
                .userUpdateService(this.userUpdateService)
                .authenticationService(this.authenticationService)
                .build();
        this.userCreateController = UserCreateController.builder()
                .userCreateService(this.userCreateService)
                .build();
    }

}