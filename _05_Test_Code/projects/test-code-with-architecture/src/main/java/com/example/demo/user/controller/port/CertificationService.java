package com.example.demo.user.controller.port;

// FIXME: CertificationService 를 컨트롤러에 두면 하위 모듈인 서비스가 상위 모듈인 컨트롤러를 의존
public interface CertificationService {

    void send(String email, long userId, String certificationCode);

}
