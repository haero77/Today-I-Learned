# Spring Framework

<details>
    <summary><b>✅ Spring Framework?</b></summary>

- 자바 엔터프라이즈 개발을 편하게 해주는 **경량급 오픈소스 애플리케이션 프레임워크**
- 엔터프라이즈 애플리케이션을 개발하는데 필요한 인프라를 제공함으로써, 개발자는 비즈니스 로직에만 집중할 수 있다. 
- 스프링의 프레임워크의 중요한 특징으로는 `의존성 주입`, `제어의 역전` 등이 있다.  

---

### Spring 

- 자바 엔터프라이즈 개발을 편하게 해주는 **경량급 오픈소스 애플리케이션 프레임워크**
- **Lightweight Java Applicaion Framework**
  - 목표: POJO 기반(경량급)의 엔터프라이즈 애플리케이션 개발을 쉽고 편하게 할 수 있게한다.
  - 자바 애플리케이션을 개발하는데 필요한 인프라를 제공 👉 개발자는 애플리케이션 비즈니스 로직에 집중할 수 있게 된다.
- 동적인 웹 사이트를 개발하기 위한 여러 가지 서비스를 제공한다.

> 💡 자바 엔터프라이즈
>  - 웹 프로그래밍에 필요한 기능을 다수 포함
>  - JSP, Servlet, JDBC 등

</details>

<details>
    <summary><b>🔼 Spring, Spring Boot, Spring MVC 차이?</b></summary>


---

- `Spring`
  - POJO 객체 기반의 엔터프라이즈 애플리케이션 개발을 쉽고 편하게 할 수 있게하는 프레임워크
  - `DI`, `IoC`를 적절히 사용해서 느슨하게 결합된 애플리케이션을 개발할 수 있게함.
  - 문제: 스프링 기반으로 개발을 할 때 **필요한 라이브러리를 등록하기 위한 많은 설정을 필요로 한다.** 
    - 라이브러리간 종속성, 버전 호환성 등을 신경써야함

- `Spring Boot` 
  - 스프링 프레임워크의 모듈
  - 스프링에서 제공하는 **많은 라이브러리를 기본 설정 값으로 자동으로 설정**할 수 있게 해준다.
    - 👉 Spring MVC 역시 편하게 사용할 수 있도록 해준다.
  - **개발자는 종속성이나 버전 호환성에 대해 걱정할 필요가 없게 됨**

- `Spring MVC`
  - MVC 패턴을 구현할 수 있도록 지원해주는 스프링 '프레임워크'

--- 

- [Spring Boot vs. Spring MVC vs. Spring 의 비교](https://blog.naver.com/PostView.nhn?isHttpsRedirect=true&blogId=sthwin&logNo=221271008423&parentCategoryNo=&categoryNo=50&viewDate=&isShowPopularPosts=true&from=search)

</details>

<details>
    <summary><b>✅ 프레임워크 Vs. 라이브러리</b></summary>

- 공통점
  - 둘 다 **다른 누군가가 작성해둔 코드**, 프로젝트를 위해서 가져다 쓴다.
- 차이점
  - **프로그램 `제어의 주도권`이 누구한테 있느냐**
- `프레임워크`
  - 이미 정해진 규칙에 따라 코드를 작성하고, 프레임워크가 내가 작성한 코드를 호출하여 프로그램을 제어 
  - 라이브러리를 포함
  - `JUnit5`를 사용하여 코드를 작성하고, 실행은 Junit의  `@Test` 어노테이션이 대신해준다.  
- `라이브러리`
  - 내가 코드를 호출해서 컨트롤하는 거면 라이브러리. 

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FXs3xz%2FbtqHByulBdl%2Fku7QE8veHKu4qzKeWkIPVk%2Fimg.png)

---

- https://www.youtube.com/watch?v=t9ccIykXTCM
- https://nhj12311.tistory.com/382


</details>

<details>
    <summary><b>✅ POJO 객체란?</b></summary>

- 프레임워크 인터페이스나 클래스를 구현하거나 확장하지 않은 단순 클래스
- Java에서 제공하는 기본 API외에는 종속되지 않아 코드가 간결하고 테스트 자동화에 유리
- Spring에서는 도메인과 비즈니스 로직을 수행하는 대상 이 POJO 대상이 될 수 있다. 

</details>

<br>

---

# Spring Core

## IoC, DI

<details>
    <summary><b>IoC?</b></summary>
</details>

<details>
    <summary><b>DI?</b></summary>
</details>

<details>
    <summary><b>객체 주입방식은 어떤 것들이 있나요?</b></summary>
</details>

<details>
    <summary><b>생성자 방식의 객체 주입방식을 사용하는 이유는?</b></summary>
</details>

<br>

---

## AOP, 프록시

<details>
    <summary><b>AOP란?</b></summary>
</details>

<details>
    <summary><b>Spring AOP를 어떤 기능에 적용하여 활용해봤는지</b></summary>
</details>

<details>
    <summary><b>Springjdk dynamic proxy vs CGLIB(code Generator Library) 차이점은?</b></summary>
</details>

<br>

---


## Spring Bean

<details>
    <summary><b>스프링 컨테이너란?</b></summary>
</details>

<details>
    <summary><b>스프링 빈</b></summary>
</details>

<details>
    <summary><b>빈 등록과정</b></summary>
</details>

<details>
    <summary><b>빈 스코프</b></summary>
</details>

<details>
    <summary><b>빈 라이프사이클</b></summary>
</details>

<details>
    <summary><b>@Component, @Controller, @Service, @Repository</b></summary>
</details>

---

# Spring MVC

## MVC

<details>
    <summary><b>MVC 패턴</b></summary>
</details>

<details>
    <summary><b>디스패처 서블릿이란? 동작과정?</b></summary>
</details>

<details>
    <summary><b>프론트 컨트롤러(패턴)</b></summary>
</details>

<br>

---


## 필터, 인터셉터 

<details>
    <summary><b>서블릿 필터란?</b></summary>

- 서블릿에서 제공하는 기능으로, 디스패처 서블릿에 요청이 전달되기 전/후 에 URL 패턴에 맞는 요청에 대해 부가작업을 처리할 수 있는 기능 제공

- `웹 컨테이너`에서 관리

- 용도
  - 모든 요청에 대한 로깅/검사
  - 이미지/데이터 압축, 문자열 인코딩

</details>

<details>
    <summary><b>스프링 인터셉터란?</b></summary>

- Spring이 제공하는 기술로써, 디스패처 서블릿이 컨트롤러를 호출하기 전과 후에 요청과 응답을 참조하거나 가공할 수 있는 기능을 제공.

- `스프링 컨테이너`에서 관리 
  - `@ControllerAdvice`, `@ExceptionHandler` 와 같은 스프링에서 제공하는 예외처리 사용 가능.
  
- 용도
  - API 호출에 대한 로깅/검사
  - Controller로 넘겨주는 데이터의 가공

</details>

<details>
    <summary><b>필터 Vs. 인터셉터 차이?</b></summary>

- **관리하는 컨테이너**가 다르다.
  - 필터는 웹 컨테이너
  - 인터셉터는 스프링 컨테이너에서 관리

- **Spring 예외 처리 적용 여부**

- **HttpServletRequest, Response 객체 조작 여부** 
  - 필터는 가능
  - 인터셉터는 불가능

</details>

<details>
    <summary><b>필터 Vs. 인터셉터 Vs. AOP?</b></summary>
</details>

<details>
    <summary><b>⭐️ 필터와 인터셉터 사용 시 예외가 발생하면 어떻게 되나요?</b></summary>
</details>


<br>

---


## 예외 처리

<details>
    <summary><b>스프링은 예외를 어떻게 처리하는지?</b></summary>
</details>

<details>
    <summary><b>@ControllerAdvice란? 동작원리?</b></summary>
</details>

<details>
    <summary><b>@ExceptionHandler란? 동작원리?</b></summary>
</details>


<br>

---

<br>

---

# JPA

## JPA, ORM, Hibernate

<br>

---

## 영속성 컨텍스트

<br>

---

## N + 1

<br>

---

## OSIV
