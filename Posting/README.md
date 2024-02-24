> _포스팅 주제_

<!-- TOC -->
* [Java](#java)
* [Spring](#spring)
  * [Filter, Interceptor](#filter-interceptor)
* [JPA](#jpa)
* [Test](#test)
* [Git](#git)
* [IntelliJ](#intellij)
* [Library](#library)
* [Network](#network)
* [회고](#회고)
  * [실무록](#실무록)
<!-- TOC -->

# Java

- [ ]  annotationProcessor 가 뭔데?
- [ ]  fixme 단독 실행 시 테스트 성공하지만, 여러 개 실행시 실패
- [ ]  @Configuration 은 어떻게 동작할까
- [ ]  LocalDateTime 커스텀 애노테이션
- [ ]  Enum에서 @RequiredArgsConstructor를 지양해야하는 이유
- [ ]  static 에서는 List<T> 가 안되는 이유
- [ ] 인터페이스가 상속구조를 가질 때, 어떻게 접근 제한을 하도록 할 수 있을까
    - `XXXRepository extends JpaRepository<XXX, Long>, XXXCustomRepository`
        - `XXXRepository implements XXXCustomRepository`
    - 인지부하를 줄이기 위해 Repository 하나만 꽂고 싶다.
        - interface와 구현체에서 public 을 뗀다
        - interface의 추상 메서드이기 때문에, 구현체의 접근제어자는 무조건 public. protected로 바꾸면 에러 난다.
- [ ] LocalDate의 plusMonths는 어떻게 정확하게 처리하는가
    - 2019.3.1 + 1달을 하면 2019.4.1 이 나온다.
    - 반면, 2019.1.31 + 1달을 하면 2019.2.28 이 나온다.
        - 대체 plusMonths는 어떻게 이를 처리했을까 궁금하다.

# Spring

- [ ]  @Configuration 은 어떻게 빈을 찾고 싱글톤으로 등록하는가
    - [ ] [@Configuration은 어떻게 빈을 등록하고, 싱글톤으로 관리할까?](https://tecoble.techcourse.co.kr/post/2023-05-22-configuration/)
- [ ]  어떻게 여러 쿼리 파라미터를 객체로 바인딩 할 수 있을까?
    - http://dolszewski.com/spring/how-to-bind-requestparam-to-object/
- [ ]  왜 기본 생성자가가 필요하다는 걸까?
- [ ]  `@Valid`의 동작 원리
- [ ]  `@Valid`와 `@Validation`은 무엇이 다른가?
- [ ]  implementation, runtimeOnly, compileOnly의 차이점(feat. 멀티 모듈)
- [ ]  MapStruct 인스턴스 통째로 매핑하기
- [ ]  Provider 구조?
- [ ] 메서드에서 빌더를 파라미터로 사용하면 안 되는 이유?
    - 클래스 레벨에 @Builder를 사용해야하는 이유와 관련 있을 것 같음
- lombok does not copy the annotation 'org.springframework.beans.factory.annotation.Qualifier' into
  the constructor

## Filter, Interceptor

- [ ] 인터셉터에서 발생시킨 오류를 @RestControllerAdvice는 어떻게 알아채는가?
    - 분명 컨트롤러에서 발생한 오류가 아닐터인데?

# JPA

- [ ]  ***RepositoryImpl은 언제 사용되는 걸까?***
    - [ ]  PostRepositoryCustom, PostRepository, PostRepositoryImpl

# Test

- [ ]  단독 실행 시 테스트 성공하지만, 여러 개 실행시 실패
    - [ ]  테스트 실행 시 마다
- [ ]  MockRestServiceServer
    - [ ]  https://www.baeldung.com/spring-mock-rest-template
- [ ]  Testcontainers

---

# Git

- Git Merge
    - 참고
        - [Git] Merge 이해하기 (Merge / Squash and Merge / Rebase and Merge)
            - https://im-developer.tistory.com/182

---

# IntelliJ

- [ ]  여러 개발자들이 같은 local profile 을 이용하는방법
    - [ ]  intellij 에서 edit configuration 말고?

# Library

- [ ]  POI 를 이용하여 엑셀 다운로드 쉽고 편하게 하기
    - [ ]  https://techblog.woowahan.com/2698/

# Network

- [ ]  Content type header 와 accept header 차이점
    - [ ]  https://www.linkedin.com/pulse/headers-rest-api-calls-rosalina-das/
    - [ ]  https://kritika-gupta.medium.com/accept-header-vs-content-type-header-b84df3d1214a

---

# 회고

- [ ] 신입 개발자 두 명의 테스트 코드 도입기
    - 부제: 개발 문화. 없어서 만들어봤습니다.
- [ ] 단독 실행 시 테스트 성공하지만, 여러 개 실행시 실패

## 실무록

- [ ] 조회 시 POST를 쓰게 된 이유

## 레거시 청산 기록

- [ ] 무의미한 Optional 팩토리 메서드 사용 없애기