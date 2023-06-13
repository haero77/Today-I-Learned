# Spring Framework



<details>
    <summary><b>✅ 서블릿 대신 Spring 쓰는 이유? Spring 왜 쓰나요?</b></summary>

- 기존 서블릿의 경우, 핸들러의 공통 로직이 매번 중복된다는 단점 
  - `프론트 컨트롤러`의 등장함으로써 공통 로직을 처리 👉 Spring MVC는 디스패처 서블릿을 통해 프론트 컨트롤러 패턴 구현.
  - 프론트 컨트롤러 이전에는 요청마다 서블릿을 정의하고 요청을 수행할 때마다 매번 스레드를 생성했다면, 이제는 하나의 서블릿만 정의하고 이 서블릿이 모든 요청을 수행
- 스프링으로 웹 요청을 처리함으로써, 
  - 스프링 MVC에서 제공하는 디스패치 서블릿 사용 👉 개발자는 핸들러에만 집중
  - 스프링 컨테이너 사용 👉 `IoC`, `DI`


> 💡 서블릿: 동적 페이지를 만드는데 사용되는 서버 프로그램

---

Ref.

- https://backtony.github.io/interview/2021-11-28-interview-11/

</details>

<details>
    <summary><b>✅ Spring 은 왜 쓰나요?</b></summary>

- 초기에 기본적인 설정과 적용시킬 기술들만 잘 선택을 해준다면, **기술보다는 애플리케이션의 로직 비즈니스 로직에만 집중**할 수 있다.
- Java 기반의 프레임워크 👉  **Java가 갖는 객체 지향 언어의 특성을 잘 살릴 수 있는 프레임워크**
- 예) IoC, DI의 개념을 활용하여 다형성을 충분히 만족시킴

</details>

<details>
    <summary><b>✅ Spring Framework?</b></summary>

- 엔터프라이즈용 Java 애플리케이션 개발을 편하게 할 수 있게 해주는 **오픈소스 경량급 애플리케이션 프레임워크**
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

---

Ref.

- https://www.codestates.com/blog/content/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8

</details>

<details>
    <summary><b>✅ Spring, Spring Boot, Spring MVC 차이?</b></summary>

---

- `Spring`
  - POJO 객체 기반의 엔터프라이즈 애플리케이션 개발을 쉽고 편하게 할 수 있게하는 프레임워크
  - `DI`, `IoC`를 적절히 사용해서 느슨하게 결합된 애플리케이션을 개발할 수 있게함.
  - 문제: 스프링 기반으로 개발을 할 때 **필요한 라이브러리를 등록하기 위한 많은 설정을 필요로 한다.** 
    - 라이브러리간 종속성, 버전 호환성 등을 신경써야함

- `Spring Boot`
  - 스프링 부트는 스프링으로 애플리케이션을 만들 때에 **필요한 설정을 간편하게 처리해주는 별도의 프레임워크**
  - 스프링에서 제공하는 **많은 라이브러리를 기본 설정 값으로 자동으로 설정**할 수 있게 해준다.
    - 👉 Spring MVC 역시 편하게 사용할 수 있도록 해준다.
  - **개발자는 종속성이나 버전 호환성에 대해 걱정할 필요가 없게 됨**

- `Spring MVC`
  - **웹 애플리케이션 개발을 위한 MVC 패턴 기반의 Spring 프레임워크**
  - `디스패처 서블릿` 등을 활용하여 해당 애플리케이션으로 들어오는 **모든 요청을 핸들링 & 공통 작업을** 처리

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

> 💡프레임워크: 
> - 프레임워크란 응용 프로그램이나 소프트웨어 솔루션 개발을 수월하기 위해 구조, 틀이 제공된 소프트웨어 환경

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
    <summary><b>✅ IoC?</b></summary>

> - 👉 **'개발자가 아닌 스프링이 사용할 객체를 생성하여 의존관계를 맺어주는 것'**
> - 객체 내부에서 필요한 객체에 대한 생성을 '직접' 하게 되면, 
>   - 다른 객체를 사용해야하는 경우, 기존 코드를 지우고 반드시 프로덕션 코드를 수정할 수 밖에 없게된다.
> - `제어의 역전`을 통해 어떤 객체를 주입할지 선택가능하므로, 유지보수성 증가.

--- 

- 객체의 생성에서부터 생명주기의 관리까지 모든 객체에 대한 제어권이 바뀐 것을 의미 
  - 또는 제어 권한을 자신이 아닌 다른 대상에게 위임하는 것

- 개발자는 프레임워크에 필요한 부품을 개발하고 조립하는 방식으로 개발 &  최종 호출은 개발자가 아니라 프레임워크의 내부에서 결정된 대로 이뤄지게 되는데 이런 현상을 `제어의 역전`이라고 함

- Spring에서는 `IoC 컨테이너`를 통해 객체의 생성주기를 관리함으로써 `IoC`를 구현

---

- [스프링과 스프링부트(Spring Boot)ㅣ정의, 특징, 사용 이유, 생성 방법](https://www.codestates.com/blog/content/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8)

</details>

<details>
    <summary><b>✅ DI?</b></summary>

- DI는 스프링에서 지원하는 `IoC의 형태`
- **클래스 사이의 의존관계를 빈 설정 정보를 바탕으로 컨테이너가 자동으로 연결해주는 것**
  - Bean 설정 파일에 의존관계가 필요하다는 정보만 추가함으로써 👉 오브젝트의 레퍼런스를 컨테이너가 주입을 해준다.
  - 런타임에 동적으로 의존관계가 생긴다. (컨테이너가 흐름의 주체가 된다.)

- 장점 
  - DI를 통해 `결합도`를 낮출 수 있다.
  - 객체에 대한 독립적인 테스트가 가능해진다. 
- 단점
  - DI를 설정하는 작업이 필요하므로, 간단한 프로그램에서는 이 과정이 번거로울 수 있음.
  - 코드 추적이 어려움. 
    - 의존성이 주입되었을 때 비로소 어떤 객체가 주입되었는지 알 수 있다.

> 💡 `컨테이너` 
>  - 프레임워크기반의 개발에서는 프레임워크가 자신이 흐름의 주체가 되어 필요할 때마다 애플리케이션을 호출하여 진행
>  - 이 때 `흐름의 제어권`을 가지는 것이 컨테이너

> 💡 `결합도`: 구현체에 의존하느냐 추상체에 의존하느냐

---

- https://www.nextree.co.kr/p11247/

</details>

<details>
    <summary><b>✅ 의존성 주입 방식은 어떤 것들이 있나요?</b></summary>

- 생성자 주입
  - 필요한 의존성을 모두 포함하는 생성자를 만들고, 해당 생성자를 통해 의존성 주입
  - 생성자 호출 시점에 딱 한 번만 주입되는 것이 보장됨
  - **불변, 필수** 의존관계에 사용.

- Setter 주입
  - 필드값을 수정하는 메서드를 통해 주입
  - **선택, 변경** 가능성이 있는 의존관계에 사용

```java
@Autowired
public void setMemberRepository(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
}
  ```


- 필드 주입 
  - 필드에 `@Autowired`를 통해 의존성 주입
  - 외부에서 접근이 불가능
  - DI 프레임워크가 없으면 아무것도 할 수 없는 객체가 된다.
  - 외부에서 접근이 불가능해서 테스트 하기 힘들다는 치명적인 단점

</details>

<details>
    <summary><b>✅ 생성자 주입을 사용하는 이유?</b></summary>

- **대부분의 의존성 주입은 한 번 일어나고 난 뒤에 애플리케이션 종료때까지 유지 됨**
  - 중간에 의존관계가 변경되면 예상치 못한 흐름으로 넘어간다. 👉 의도와 다르게 동작 위험
- `Setter 주입`을 사용하면 `public`으로 접근 제어를 열어야하고, 누군가 실수로 필드값을 변경할 수도 있다. 
- `필드 주입`의 경우 외부에서 접근이 불가능하므로 테스트를 할 수가 없다. 
- 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일이 없다. 따라서 불변하게 설계할 수 있다.

</details>

<br>

---

## 프록시, AOP

<details>
    <summary><b>✅ 프록시란? 프록시 패턴이란?</b></summary>

### 프록시

- 클라이언트가 요청한 결과를 서버에 직접 요청하는 것이 아닌, **대리자를 통해서 간접적으로 요청**할 수 있는데, 여기서 대리자를 `프록시`라 한다.
- 클라이언트 ➡️ 프록시 ➡️ 서버

- 특징 
  - `대체 가능성` 
    - 객체에서 프록시가 되려면, 클라이언트는 실제 서버에게 요청했는지 프록시에게 요청했는지 조차 몰라야한다.

- 주요 기능 
  - `접근 제어`
    - 권한에 따른 접근 차단
    - 캐싱
    - 지연 로딩
  - `부가 기능 추가`
    - 원래 서버가 제공하는 기능에 대해 부가 기능 수행
    - 예) 요청값이나 응답값을 조

### 프록시 패턴 

- 프록시를 이용하여 **특정 객체에 대한 접근을 제어하거나 기능을 추가**할 수 있는 패턴

- 예시
  - JPA에서, 연관된 엔티티를 조회할 때 `Lazy Loading`으로 설정한 경우 프록시 객체가 주입됨.
  - `@Transactional`을 이용하면 스프링 AOP로 인해 해당 객체의 프록시 객체를 만들어서 주입하여 사용.

- 장점
  - `OCP` 만족
    - 기능은 확장하면서, 기존 객체의 변경은 없음 👉 `OCP`

- 단점
  - 프록시 객체가 생성됨에 따라 복잡도 증가. 처리 속도 증가

</details>



<details>
    <summary><b>✅ AOP란? (+ AOP 용어)</b></summary>

### AOP (Aspect-Oriented Programming)

- **애플리케이션 로직을 핵심 기능과 부가 기능으로 나누고, 여러 곳에서 사용되는 부가 기능. 즉 공통 관심 사항을 분리하는 방식의 프로그래밍**을 말한다.
  - 예) 로깅, 데이터베이스 연결
  
- 장점
  - **중복 코드가 줄어듦**
  - **변경 지점이 하나**가 되도록 잘 모듈화 시킴 👉 OOP 단점 극복

### 용어 정리



</details>

<details>
    <summary><b>✅ AOP 적용 방식에는 어떤 것들이 있는지?</b></summary>

### 1. 컴파일 시점 

- `.java` 파일을 컴파일러를 이용해서 `.class`를 만드는 시점에 부가 로직을 추가하는 방식.

- 단점
  - AspectJ가 제공하는 별도의 컴파일러를 사용해야하고, 설정이 복잡. 
  - `AspectJ`를 직접 사용해야한다

- 조인 포인트
  - 모든 지점(생성자, 필드값 접근, static 메서드 접근 & 실행)

> 💡 조인 포인트?
> - 생성자, static 메서드 접근 & 실행 등 **AOP를 적용할 수 있는 지점**
   
### 2. 클래스 로딩 시점 

- `.class` 파일을 `JVM`에 올리기 전에 바이트코드를 조작하여 위빙하는 방식(로드타임 위빙)

- 단점
  - 자바를 실행할 때 별도의 옵션(java -javaagent)을 통해 클래스 로더 조작기를 지정해야함
    - 번거롭고 운영하기 어려움
  - `AspectJ`를 직접 사용해야한다

- 조인 포인트
  - 모든 지점(생성자, 필드값 접근, static 메서드 접근 & 실행)

> 💡 위빙
> - 원본 로직에 부가 로직이 추가되는 것
> - 애스펙트와 실제 코드를 연결해서 붙이는 것

### 3. 런타임 시점

- 클래스 로더에 클래스가 올라가고, **자바가 이미 실행되고 난 후에 부가로직을 추가하는 방식**
- 실제 대상 코드는 그대로 유지. 프록시를 통해 부가 기능이 적용
  - 항상 프록시를 통해야 부가 기능을 사용할 수 있다.
- `Spring AOP`가 사용하는 방식

- 장점
  - 별도의 컴파일러나 실행 옵션을 지정하지 않아도 된다. 스프링만 있으면 사용가능.

- 조인 포인트
  - **메서드 실행 시점으로 제한**된다.
    - **프록시는 메서드 오버라이딩 개념으로 동작**하기 때문에 생성자나 static 메서드, 필드값 접근에는 사용 불가.

---

> 💡스프링은 AspectJ의 문법을 차용하고 프록시 방식의 AOP를 적용한다. AspectJ를 직접 사용하는 것이 아니다. 

</details>

<details>
    <summary><b>✅ Spring이 프록시 방식의 AOP를 사용하는 이유? 그냥 AspectJ 사용하면 되지 않나요?</b></summary>

- 그냥 AspectJ를 사용하면 `런타임`이 아닌 `컴파일 타임`과 `클래스 로드` 시점에 애스펙트를 적용해야함
- 그러기 위해서는 **별도의 컴파일러**를 사용하거나 **자바 실행옵션**, **AspectJ 전용 문법**등 번거롭고 복잡함
- 👉 스프링만 있으면 사용가능한 DI, IoC 개념 등을 이용하여 프록시를 이용해 AOP를 적용 가능. 

</details>

<details>
    <summary><b>Spring AOP를 어떤 기능에 적용하여 활용해봤는지?</b></summary>
</details>

<details>
    <summary><b>🔼 Spring JDK dynamic proxy vs CGLIB(code Generator Library) 차이점은?</b></summary>

- 기존 Proxy 패턴을 이용한 프록시의 문제
  - 부가 기능 코드의 중복이 생긴다
  - 매번 프록시 객체를 생성해야함 👉코드의 복잡도 증가 

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FezX5oG%2FbtrOY9OTpAj%2ForkSBdcoHI8SHP1WeVmQHK%2Fimg.png)

- Spring AOP를 통해 프록시 객체를 생성할 때, 해당 객체가 인터페이스를 구현 유무의 차이
  - 구현하는 경우 👉 JDK dynamic proxy
  - 구현 안 하는 경우 👉 CGLIB

- ProxyBeanFactory
  - Spring에서 프록시를 Bean으로 만들어주는 하나의 객체

- JDK Dynamic Proxy
  - Reflection을 이용하여 프록시 객체를 생성 👉 속도가 느리다. (동적으로 Class를 Load 하고, Heap에 객체를 띄우는 선행 절차가 존재하기에 나타나는 결과이다.)
  - 인터페이스가 있어야 한다.
- CGLIB
  - 바이트 코드를 조작해서 프록시를 만듦으로 빠르다.
  - 상속 방식으로 구현되어 인터페이스가 없어도된다.
  - Spring Boot의 기본 방식

---

- https://huisam.tistory.com/entry/springAOP
- https://gmoon92.github.io/spring/aop/2019/04/20/jdk-dynamic-proxy-and-cglib.html

</details>

<br>

---


## Spring Bean

<details>
    <summary><b>✅ 스프링 컨테이너란?</b></summary>

- **자바 객체의 생명 주기를 관리하며, 생성된 자바 객체들에게 추가적인 기능을 제공하는 역할**
- 예를 들면, 스프링 빈을 생성하고, 의존성 주입이 필요한 곳에 레퍼런스를 할당해준다.
- `ApplicationContext`를 스프링 컨테이너라고 한다. 

</details>

<details>
    <summary><b>✅ 스프링 Bean이란?</b></summary>

- 스프링 컨테이너 안에 들어있는 객체
- 스프링 컨테이너 초기화 시 빈 객체 생성, 의존 객체 주입 및 초기화
- 스프링 컨테이너 종료 시 빈 객체 소멸

</details>

<details>
    <summary><b>빈 등록과정(교재 보고)</b></summary>



</details>

<details>
    <summary><b>✅ 빈 스코프</b></summary>

- 빈 스코프란, **빈이 존재할 수 있는 범위**를 의미

### 스프링이 지원하는 다양한 스코프 범위

- `싱글톤 스코프`
  - 스프링 빈의 기본 스코프
  - 스프링 컨테이너의 시작과 종료까지 1개의 객체로 유지
  - **싱글톤 빈 객체는 여러 쓰레드에 의해 공유되기 때문에 Stateless로 설계하는 것이 중요**

- `프로토타입 스코프`
  - 빈의 생성, 의존관계 주입, 초기화까지만 관여하고 더 이상 컨테이너가 관리하지 않는 스코프
  - 매번 **요청마다 생성하고** 클라이언트에게 반환한다음에 더 이상 관리 안 함
  - **싱글톤은 스프링 컨테이너가 생성될 때 빈이 생성되는데, 프로토타입은 요청이 있을 때만 생성됨**
  - @Autowired의 지원을 받을 수 있음 -> DI 가능 
  - [언제 사용하는가](https://www.inflearn.com/questions/415649/%ED%94%84%EB%A1%9C%ED%86%A0%ED%83%80%EC%9E%85-%EB%B9%88%EC%97%90-%EB%8C%80%ED%95%9C-%EC%A7%88%EB%AC%B8)

```java
@Scope("prototype")
@Component
public class HelloBean {}
```

- 웹 관련 스코프
  - `request`
    - 웹 요청이 들어오고 나갈 때까지 유지되는 스코프
    - 로깅을 하기 위해 요청이 들어올 때 생성할 때 로깅, 빈이 종료되기전에 `@PreDestroy`로 로깅 남긴다.
  - `session`
    - 웹 세션이 생성되고 종료될 때까지 유지되는 스코프
  - `application`
    - 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프
      - 서블릿 컨텍스트는 web application내에 있는 모든 서블릿들을 관리하며 정보공유할 수 있게 도와 주는 역할 을 하는데, 톰캣 컨테이너가 실행 시 애플리케이션 하나당 한개의 서블릿컨텍스트가 생성된다.
      - 생명 주기는 보통 톰캣의 시작과 종료와 일치한다.

</details>

<details>
    <summary><b>🔼 빈 생명주기</b></summary>

> 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존 관계 주입 -> 초기화 콜백 -> 사용 -> 소멸 전 콜백 -> 스프링 컨테이너 종료
- 스프링 컨테이너에 의해 생명주기가 관리된다.
- 스프링 컨테이너 초기화 시 빈 객체 생성, 의존 관계를 주입하고 초기화 
- 싱글톤 빈들은 컨테이너 종료 전 소멸 전 콜백이 발생
- 초기화와 소멸 메서드는 애노테이션으로 @PostConstruct, @PreDestroy 를 사용하는 것이 권장된다

</details>

<details>
    <summary><b>빈 생명주기 콜백</b></summary>



</details>


<details>
    <summary><b>@Component, @Controller, @Service, @Repository</b></summary>
</details>

---

# Spring MVC

## MVC

<details>
    <summary><b>✅ Spring MVC란?</b></summary>

- **웹 애플리케이션 개발을 위한 MVC 패턴 기반의 Spring 프레임워크**
- `디스패처 서블릿` 등을 활용하여 해당 애플리케이션으로 들어오는 모든 요청을 핸들링 & 공통 작업을 처리

</details>

<details>
    <summary><b>🔼 MVC 패턴이란?</b></summary>

- 정의
  - 애플리케이션의 개발 영역을 Model, View, Controller로 나눠 각 역할에 맞는 코드를 작성
  - 사용자 인터페이스(UI)와 도메인 로직을 분리함으로써 서로에게 영향이 가지 않게한다. 
    - 👉 각자의 독립적인 개발과 유지보수를 용이하게 함

- MVC
  - Model
    - 데이터와 비즈니스 로직을 처리  
    - 비즈니스 로직을 처리한 데이터의 결과
  - View
    - 클라이언트에게 보여줄 화면을 처리 
  - Controller
    - 클라이언트에게 요청을 받는 엔드포인트
    - Model에게 요청을 전달하고, 데이터를 리턴 받아 처리 & View로 리턴

- 장점
  - 과거에는 Controller에 다 담아두고 처리했다.
  - 기능 별로 코드를 분리하여, 가독성을 높이고 재사용성을 증가시킨다.

- 단점  
  - 프로그램 규모가 커짐에 따라 유지보수가 쉽지 않음(why?)

</details>

<details>
    <summary><b>✅ Spring MVC 동작원리?</b></summary>

![](https://camo.githubusercontent.com/d7cef9e49a490593b9ef01173bc8b685394a898e62d254e323ea5a6931279763/68747470733a2f2f6261636b746f6e792e6769746875622e696f2f6173736574732f696d672f706f73742f696e746572766965772f736572766c65742d362e504e47)

1. 디스패처 서블릿이 요청을 받음
2. 핸들러 매핑을 통해 요청을 처리할 핸들러 조회
3. 핸들러에게 요청을 위임할 핸들러 어댑터를 조회, 핸들러에게 요청 위임
4. 핸들러는 비즈니스 로직을 수행하고 `ModelAndView` 로 변환해서 반환
   - ModelAndView: 
     - 디스패처 서블릿에 의해 처리될 뷰를 직접 지정할 수 있고 Model(entity)부분에 있는 데이터를 전달 할 수 있도록 하는 객체
5. viewResolver를 호출
   - 적절한 viewResolver를 찾고 해당 viewResolver를 호출한다.
   - RestController라면 이 과정과 이후 과정 없이 컨버터를 이용해 바로 결과값을 리턴한다.
   - `ViewResolver`:
     - ModelAndView 객체를 View 영역으로 전달하기 위해 알맞은 View 정보를 설정하는 역할 
6. view 반환
   - viewResolver는 뷰의 논리 이름을 물리 이름으로 바꾸고, 렌더링 역할을 담당하는 뷰 객체를 반환
7. view 렌더링
   

</details>

<details>
    <summary><b>✅ 디스패처 서블릿이란? 동작원리?</b></summary>

- 정의
  - HTTP 프로토콜로 들어오는 모든 **요청을 가장 먼저 받아**, **적합한 컨트롤러를 찾아 요청을 위임하는 `프론트 컨트롤러`**
  
- 장점
  - 과거에는 서블릿에 대해 URL을 매핑하기 위해 web.xml 에 모두 등록해줘야 했음 
  - 👉 **디스패처 서블릿의 등장으로 해당 어플리케이션으로 들어오는 모든 요청을 핸들링**해주고 `공통 작업`을 처리
  - **컨트롤러만 구현해두면 디스패처 서블릿이 알아서 요청을 위임해주게 됨**

- 동작원리 

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbImFbg%2FbtrGzZMTuu2%2FCkY4MiKvl5ivUJPoc5I3zk%2Fimg.png)

1. 클라이언트의 요청을 디스패처 서블릿이 받음
2. 요청을 위임할 컨트롤러 조회 
   - 핸들러 매핑을 통해 URL에 매핑된 핸들러(컨트롤러)를 조회
3. 요청을 핸들러로 전달할 `핸들러 어댑터`를 찾아 요청을 위임
   - 직접 요청을 전달하는 것이 아니라 어댑터를 통해 위임하는 이유
     - 컨트롤러의 구현 방식(Controller 인터페이스, 어노테이션)이 다양하므로
   - 어댑터 패턴
     - 호환되지 않는 인터페이스를 가진 객체들이 협업할 수 있도록 하는 구조적 디자인 패턴
4. 핸들러 어댑터가 컨트롤러에게 요청을 위임
   - 컨트롤러로 요청을 위임한 전/후에 공통적인 작업이 필요
   - 예시
     - 인터셉터
     - `ArgumentResolver` 👉 `@RequestBody`, `@RequestParam` 등을 처리
     - `ReturnValueHandler` 👉 ResponseEntity의 Body를 직렬화
5. 비즈니스 로직 처리
6. 컨트롤러가 리턴값을 리턴
7. 핸들러 어댑터가 리턴값을 처리
   - `ReturnValueHandler` 👉 ResponseEntity의 Body를 직렬화
8. 서버의 응답을 클라이언트로 반환함
   - 필터를 거쳐 최종적으로 클라이언트로 반환

--- 

- [[Spring] Dispatcher-Servlet(디스패처 서블릿)이란? 디스패처 서블릿의 개념과 동작 과정](https://mangkyu.tistory.com/18)

</details>

<details>
    <summary><b>✅ 프론트 컨트롤러(패턴)</b></summary>

- 정의 
  - 서블릿 컨테이너의 제일 앞에서, 서버로 들어오는 클라이언트의 모든 요청을 받아 처리해주는 컨트롤러. 
  - MVC 구조에서 함께 사용되는 디자인 패턴

- 서블릿 컨테이너
  - WAS 내부에서 개발자 대신 서블릿을 관리

</details>

<br>

---

## 필터, 인터셉터 

<details>
    <summary><b>✅ 서블릿 필터란?</b></summary>

- 서블릿에서 제공하는 기능으로, **디스패처 서블릿에 요청이 전달되기 전/후** 에 URL 패턴에 맞는 요청에 대해 부가작업을 처리할 수 있는 기능 제공

- `웹 컨테이너`에서 관리

- 용도
  - 모든 요청에 대한 로깅/검사
  - 이미지/데이터 압축, 문자열 인코딩

- 메서드
  - `init()`
    - 필터 객체 초기화.
  - `doFilter()`
    - URL 패턴에 맞는 모든 HTTP 요청이 디스패처 서블릿으로 전달되기 전에 웹 컨테이너에 의해 실행되는 메소드
  - `destroy()`
    - 필터 객체가 소멸될 때 호출되는 메서드
    - 서블릿 컨테이너가 종료될 때 1회 호출 

</details>

<details>
    <summary><b>✅ 스프링 인터셉터란?</b></summary>

- Spring이 제공하는 기술로써, 디스패처 서블릿이 **컨트롤러를 호출하기 전/후**에 요청과 응답을 참조하거나 가공할 수 있는 기능을 제공.

- `스프링 컨테이너`에서 관리 
  - `@ControllerAdvice`, `@ExceptionHandler` 와 같은 스프링에서 제공하는 예외처리 사용 가능.
  
- 용도
  - API 호출에 대한 로깅/검사
  - Controller로 넘겨주는 데이터의 가공

- 메서드 
  - `preHandle()`
    - 핸들러 호출 전 실행
  - `postHandle()`
    - 핸들러 호출 후 실행
    - 핸들러(컨트롤러) 하위 계층에서 예외 발생 시 실행 X
  - `afterCompletion()`
    - 핸들러 호출 후 `postHandle()`까지 실행되고 나서 실행
    - 뷰가 렌더링 된 이후에 호출
    - 핸들러(컨트롤러) 하위 계층에서 예외 발생하더라도 반드시 실행 됨.
  
<img width="777" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/5c531635-fe07-4fb3-8fd1-a04235c87c7b">


</details>

<details>
    <summary><b>✅ 필터 Vs. 인터셉터 차이?</b></summary>

- **관리되는 컨테이너**가 다르다.
  - 필터는 웹 컨테이너
  - 인터셉터는 스프링 컨테이너에서 관리 👉 Spring 에서 제공하는 예외처리 가능.

- **Spring 예외 처리 적용 여부**
  - 인터셉터는 스프링 컨텍스트 안에서 관리되므로 `@ControllerAdvice`, `@ExceptionHandler` 와 같은 스프링에서 제공하는 예외처리 사용 가능.

- **HttpServletRequest, Response 객체 조작 여부** 
  - 필터는 다음 필터로 넘어가기 전에 Request, Response **객체 자체를 변경 가능** 
  - 인터셉터는 Request, Response 객체 자체를 변경할 수는 없지만 **값은 조작 가능**
  
</details>

<details>
    <summary><b>✅ 설명만 들어보면 인터셉터만 쓰는게 나아보이는데, 아닌가요? 필터는 어떤 상황에 사용 해야 하나요?</b></summary>

- 둘의 가장 큰 차이는 스프링 컨텍스트에 속하냐의 여부.

- 필터는 스프링 컨텍스트에 속하지 않는다.
  - **스프링과 무관하게 전역적으로 처리해야하는 작업**에 사용하면 좋다.
    - 예) 문자열 인코딩
    
- 인터셉터는 스프링 컨텍스트에 속한다. 
  - 클라이언트의 요청에 대해 전역적으로 처리 해야하는 작업에 사용하면 좋다.
  - 예를 들면 `인가`는 특정 그룹에 대해서는 권한이 없는 등의 처리를 해주어야하는데, 이러한 작업들을 컨트롤러와 가까운 인터셉터가 처리하기 좋다. 

</details>

<details>
    <summary><b>✅ 필터 Vs. 인터셉터 Vs. AOP?</b></summary>

- **사용 목적의 차이**
  - 웹과 관련된 공통 관심사를 처리할 때는 HTTP 헤더나 URL 정보 등도 필요한데, 필터와 인터셉터는 **`HttpServletRequest` 객체를 제공.** 
  - `필터`: 
    - 스프링과 무관하게 처리해야하는 작업
    - 예) 문자열 인코딩
  - `인터셉터`: 
    - Controller로 넘겨주는 정보(데이터)의 가공
    - 예) 특정 사용자는 특정 기능을 사용 못하게 막는 등의 세부적인 보안 작업
  - `AOP`: 
    - 비즈니스단의 메서드에서 조금 더 세밀하게 조정하고 싶을 때. 
    - 예) 특정 메서드의 트랜잭션 처리

- **적용 대상, 실행 위치가 다름**
  - 필터, 인터셉터: 
    - 적용 대상을 URL로 구분
    - 필터는 디스패처 서블릿 전/후
    - 인터셉터는 컨트롤러 전/후
  - AOP: 
    - URL, 파라미터, 어노테이션 등 PointCut이 지원하는 방법으로 대상 지정 

---

- [[Spring] Filter, Interceptor, AOP 차이 및 AOP를 사용하여 Logging을 구현한 이유](https://velog.io/@miot2j/Spring-Filter-Interceptor-AOP-%EC%B0%A8%EC%9D%B4-%EB%B0%8F-AOP%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-Logging%EC%9D%84-%EA%B5%AC%ED%98%84%ED%95%9C-%EC%9D%B4%EC%9C%A0)

</details>

<details>
    <summary><b>🔼 필터와 인터셉터 사용 시 예외가 발생하면 어떻게 되나요?</b></summary>

- 필터에서 예외처리가 되지 않으면 WAS에서 예외를 전달받고, 해당 예외를 처리할 예외 페이지가 있는지 찾는다.

- 인터셉터의 겨우
  - preHandle : 컨트롤러 호출 전에 호출된다.
  - postHandle : 컨트롤러에서 예외가 발생하면 postHandle 은 호출되지 않는다.
  - afterCompletion : afterCompletion 은 항상 호출된다. 이 경우 예외( ex )를 파라미터로 받아서 어떤 예외가 발생했는지 로그로 출력할 수 있다.

</details>

<br>

---

## 예외 처리

<details>
    <summary><b>✅ 스프링은 예외를 어떻게 처리하는지?</b></summary>

### WAS

```java
1. WAS(/error-ex, dispatchType=REQUEST) -> 필터 -> 서블릿 -> 인터셉터 -> 컨트롤러
2. WAS(여기까지 전파) <- 필터 <- 서블릿 <- 인터셉터 <- 컨트롤러(예외발생)
3. WAS 오류 페이지 확인
4. WAS(/error-page/500, dispatchType=ERROR) -> 필터(x) -> 서블릿 -> 인터셉터(x) -> 컨트롤러(/error-page/500) -> View
```

- 컨트롤러에서 발생한 예외가 처리가 되지 않으면 WAS까지 예외가 전파된다.
- WAS에서는 오류 페이지가 있는지 확인해서, 다시 요청을 보내 에러 페이지 View를 렌더링하게 된다. 
  - WAS는 오류 페이지 경로를 찾아서 내부에서 오류 페이지를 호출한다. 이때 오류 페이지 경로로 필터, 서블릿, 인터셉터, 컨트롤러가 모두 다시 호출된다.
    - 실제로는 `DispatcherType` 옵션은 기본적으로 `REQUEST`로 되어 있어 예외 발생 시 필터 객체가 다시 초기화 되지는 않는다. (예외 발생시 `DispatcherType`은 `ERROR`)
    - 인터셉터 역시 `excludePattern`을 사용하면 된다.

### Spring Boot의 예외처리 

- 스프링 부트는 기본적으로 에러 발생 시 `/error`를 오류 페이지로 요청
  - `BasicErrorController`를 이 경로를 기본으로 받는다.
- 클라이언트의 `Accept` 헤더값이 `text/html`인 경우 오류 화면을 제공
- 아닌 경우, `ResponseEntity`로 HTTP Body에 JSON 데이터를 반

</details>

<details>
    <summary><b>🔼 @ExceptionHandler란? 동작원리?</b></summary>

- API 예외 문제를 해결하기 위해 스프링에서 제공하는 예외처리 방법
- `@ExceptionHandler`를 사용하면 `ExceptionHandlerExceptionResolver`에서 예외처리

### 동작 원리

- 예외가 발생
- 예외 처리기 ExceptionHandlerExceptionResolver가 동작
  - 예외 발생 핸들러에 @ExceptionHandler가 있는지 검사
  - @ExceptionHandler 있으면 처리, 없으면 @ContollerAdvice로 넘어감
  - @ContollerAdvice에서 적합한 @ExceptionHandler가 있는지 검사하고 없으면 넘어감
- ResponseStatusExceptionResolver가 동작함
- DefaultHandlerExceptionResolver가 동작
- 적합한 ExceptionResolver가 없으므로 예외가 서블릿까지 전달되고, 서블릿은 SpringBoot가 진행한 자동 설정에 맞게 BasicErrorController로 요청을 다시 전달함

---

- [[Spring] 스프링의 다양한 예외 처리 방법(ExceptionHandler, ControllerAdvice 등) 완벽하게 이해하기 - (1/2)](https://mangkyu.tistory.com/204)

</details>

<details>
    <summary><b>🔼 @ControllerAdvice란? 동작원리?</b></summary>

- @ExceptionHandler 를 전역적으로 적용 가능하다.
  - 여러 컨트롤러에서 발생한 예외를 처리 가능.

---

- https://mangkyu.tistory.com/204

</details>

<br>

---

# 기타

<details>
    <summary><b>✅ 커넥션 풀</b></summary>

- 데이터베이스와의 연결 비용을 줄이기 위해, 미리 연결을 맺어 놓고 이것을 관리하는 것
- 요청이 있을 때 커넥션을 할당하고, 처리가 끝나면 커넥션 풀에게 반납

</details>

<details>
    <summary><b>레이어드 아키텍처?</b></summary>

</details>

<br>

---

# JPA

## JPA, ORM, Hibernate

<details>
    <summary><b>✅ JPA? ORM? Hibernate?</b></summary>

### JPA(Java Persistence API)

- JPA는 자바 진영에서 ORM 기술의 표준.
- JPA는 ORM을 사용하기 위한 인터페이스의 모음. 
  - 즉, 단순한 명세이기 때문에 **구현이 없다.**  👉 `Hibernate`는 JPA를 구현한 **구현체**

![](https://gmlwjd9405.github.io/images/inflearn-jpa/jpa-basic-structure.png)

- JPA는 애플리케이션과 JDBC사이에서 동작

### ORM(Object-Relational Mapping)

- 객체와 관계형 데이터베이스를 매핑해주는 기술
- 객체와 관계형 데이터베이스가 갖는 서로 다른 패러다임의 불일치를 해결하기 위해서 사용.
- 객체는 객체대로, 관계형 데이터베이스는 관계형 데이터베이스대로 설계. 👉 ORM 프레임워크가 중간에서 매핑을 해준다.

### Hibernate

- JPA는 ORM을 사용하기위한 인터페이스의 모음. 
- Hibernate 는 명세된 기능을 사용하기 위한 JPA의 구현체

</details>

<details>
    <summary><b>✅ JPA와 같은 ORM을 사용하는 이유가 뭔가요?</b></summary>

> 👉 "**객체와 관계형 데이터베이스가 갖는 서로 다른 패러다임의 불일치를 해결하기 위해서 사용.**" 

--- 

- 애플리케이션이 발전해가면서 내부의 **복잡성**도 커짐
- **비즈니스 요구사항을 정의한 도메인 모델도 객체로 모델링**하면 객체지향 언어가 가진 장점들을 활용하여 복잡성 제어 가능
- 장점: 추상화, 캡슐화 등 복잡성을 제어 가능
- **관계형 데이터베이스는 데이터 중심으로 구조화**되고, 객체 지향과 같은 개념들이 없음.
- 👉 객체를 RDB에 저장하고 조회하기 위해 수많은 보일러 플레이트 코드들이  비용으로 발생함.
- 패러다임의 불일치 문제를 해결함으로써 이같은 문제 해결

</details>

<br>

---

## 영속성 컨텍스트

<details>
    <summary><b>✅ 영속성 컨텍스트란?</b></summary>

> 👉 엔티티를 영구적으로 저장하는 환경.

--- 

### 영속성 컨텍스트

- 정의 
  - 엔티티를 영구적으로 저장하는 환경

- 장점
  - `1차 캐시`
  - `동일성 보장`
  - `트랜잭션을 지원하는 쓰기 지연`
  - `변경 감지`
  - `지연 로딩`

- 단점 
  - 정확히 어떤 쿼리가 실행될 지 예측할 수 없음.
    - 연관된 엔티티 조회 시 `N+1` 문제 발생 가능

### 1차 캐시 

- 정의 
  - _'영속성 컨텍스트가 내부적으로 갖는 캐시'_ 

- 장점 
  - 조회하는 엔티티가 있으면 DB를 조회하지 않고 1차 캐시에서 먼저 조회 
    - 👉 쿼리를 실행하지 않으므로 성능 향상 
  - JPA는 1차 캐시를 통해 `REPEATABLE READ` 트랜잭션 격리 수준을 데이터베이스가 아닌 애플리케이션 차원에서 제공
    - 👉 트랜잭션이 두 개 이상 수행될 때 생길 수 있는 동시성 문제(`Dirty Read`, `Non-Repeatable Read`)를 해결

### 변경 감지 

- _'엔티티의 변경사항을 데이터베이스에 자동으로 반영하는 기능'_


</details>

<details>
    <summary><b>✅ 변경 감지 시 모든 필드를 업데이트 하면 성능상 안 좋은 것 아닌가요? </b></summary>

- 모든 필드를 사용하면 **수정 쿼리가 항상 같다.**
  - 👉 애플리케이션 로딩 시점에 **수정 쿼리를 미리 생성해두고 재사용** 가능
- 데이터베이스에 동일한 쿼리를 보내면 **데이터베이스는 이전에 한 번 파싱 된 쿼리를 재사용 가능**


</details>

<details>
    <summary><b>✅ 영속성 컨텍스트에서 언제 flush가 일어나는지?</b></summary>

1. **트랜잭션 커밋 시**
   - 트랜잭션을 커밋할 때, 영속성 컨텍스트를 플러시
   - 플러시를 통해 영속성 컨텍스트의 변경 내용을 데이터베이스에 동기화한 후에 실제 데이터베이스 트랜잭션을 커밋
   - 플러시 할 때  쓰기 지연 저장소에 쌓아 놨던 INSERT, UPDATE, DELETE SQL을 DB에 날림.
   
2. **JPQL 쿼리 실행 시** 플러시 자동 호출
   - **엔티티가 영속성 컨텍스트에만 있고, DB에는 없는 상태**에서 SQL을 이용하여 조회할 경우, **데이터가 조회되지 않는 문제가 발생**. 
   - 따라서 JPA에서는 이런 문제를 방지하기 위해 JPQL 실행 전 자동으로 플러시 호출.

3. `entityManager.flush()`를 통한 플러시 직접 호출 

> 💡`플러시`: 영속성 컨텍스트의 변경 내용을 데이터베이스에 동기화하는 작업

> 💡`JPQL`: 엔티티 객체를 조회하는 객체지향 쿼리
>   - SQL은 데이터 중심 쿼리. JPQL은 엔티티 중심 `객체 지향 쿼리`

</details>

<br>

---

# @Transactional

<details>
    <summary><b>✅ @Transactional은 어떤 기능을 하나요?</b></summary>

> 👉 `@Transactional`은 스프링에서 지원하는 `선언적 트랜잭션 관리` 방법.
> - 해당 어노테이션이 적용되는 메서드를 **하나의 트랜잭션으로 묶어주는 역할**.
> - 트랜잭션 실행 중 장애가 발생할 경우 트랜잭션 실행 전 원상태로 복구하는 `원자성`을 지킬 수 있다.

---

> 💡 선언적 트랜잭션 관리 Vs. 프로그래밍 방식의 트랜잭션 관리
> - 선언적 트랜잭션 관리(Declarative Transaction Management)
>   - @Transactional 애노테이션 하나만 선언해서 편리하게 트랜잭션을 적용하는 것을 선언적
      트랜잭션 관리라 한다. (어딘가에 트랜잭션을 사용하겠다고 '선언')
> - 프로그래밍 방식의 트랜잭션 관리(Programmatic Transaction Management)
>   - 트랜잭션 매니저 등을 이용해서 직접 트랜잭션 관련 코드를 작성하는 것. 

---

- [Transactional 어노테이션 - Tecoble](https://tecoble.techcourse.co.kr/post/2021-05-25-transactional/) 

</details>

<details>
    <summary><b>✅ @Transactional(readonly=true) 는 어떤 기능인가요? 이게 도움이 되나요?</b></summary>

> 👉 readOnly=true 옵션을 사용하면 **`읽기 전용 트랜잭션`이 생성**된다.
> - `JPA`
>   - 원래 트랜잭션 커밋 시점에 플러시가 발생하는데, **플러시가 발생하지 않는다.**
>   - 👉 플러시할 때 일어나는 `스냅샷 비교` 등 무거운 로직을 수행하지 않으므로 성능 향상
>   - 변경 감지가 일어나지 않기 때문에, 엔티티를 변경해도 변경이 일어나지 않음 -> 의도치 않은 사용 방지
> - `JDBC 드라이버`
>   - 읽기 전용 트랜잭션에서 변경 쿼리가 발생하면 예외를 던진다.

---

- 트랜잭션은 기본적으로 읽기 쓰기가 모두 가능한 트랜잭션이 생성
- `readOnly=true` 옵션을 사용하면 읽기 전용 트랜잭션이 생성

- `readOnly` 옵션은 크게 3곳에서 적용됨.
  1. 프레임워크
    - JdbcTemplate 
      - 읽기 전용 트랜잭션 안에서 변경 기능을 실행하면 예외를 던진다.
    - JPA
      - 읽기 전용 트랜잭션의 경우 **커밋 시점에 `플러시`를 호출하지 않음.**
      - 플러시할 때 일어나는 `스냅샷 비교` 등 무거운 로직을 수행하지 않으므로 성능 향상
      - 변경 감지가 일어나지 않기 때문에, 엔티티를 변경해도 변경이 일어나지 않음 -> 의도치 않은 사용 방지
      
  2. JDBC 드라이버
    - 읽기 전용 트랜잭션에서 변경 쿼리가 발생하면 예외를 던진다.
    - 읽기, 쓰기(마스터, 슬레이브) 데이터베이스를 구분해서 요청한다. 읽기 전용 트랜잭션의 경우 읽기 (슬레이브) 데이터베이스의 커넥션을 획득해서 사용
  
  3. 데이터베이스 
    - 데이터베이스에 따라 읽기 전용 트랜잭션의 경우 읽기만 하면 되므로, 내부에서 성능 최적화가 발생한다.

--- 

</details>

<details>
    <summary><b>✅ 읽기에 트랜잭션을 걸 필요가 있나요? @Transactional을 안 붙이면 되는거 아닐까요?</b></summary>

> 👉 트랜잭션을 걸 필요가 있다. 
> - 읽기 도중에 다른 트랜잭션이 데이터를 수정하여 생기는 `Dirty Read`, `Non-Repeatable Read` 문제가 발생할 수 있기 때문에, 이런 문제들을 방지하기 위해서라도 사용해야한다.  

</details>

<details>
    <summary><b>트랜잭션 전파란? ⭐⭐️️</b></summary>

</details>

<br>

---

## N + 1

<details>
    <summary><b>✅ N + 1 문제란? ⭐️</b></summary>

> 👉 **처음 실행한 SQL의 결과 수만큼 추가로 SQL을 실행하는 것**
> - 대상 엔티티를 조회하고, 대상과 연관된 엔티티가 
>   - 즉시 로딩이면 바로 N + 1 문제 발생
>   - 지연 로딩이면 연관된 엔티티를 사용하는 시점에서 N + 1 문제 발생

--- 

- 연관된 엔티티를 `즉시 로딩` 하든, `지연 로딩` 하든 발생 가능.

### 예시

**즉시 로딩의 경우**

```java
// Member (1) : (N) Order
// Member -> @OneToMany -> Order
// Order -> @ManyToOne -> Member

@Entity 
public class Member {
	
    @Id @GeneratedValue
    private Long id;
	
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<Order>();
}

@Entity
public class Order {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Member member;
}
```

👉 **JPQL**을 사용할 때 문제 발생

```java
List<Members> members = em.createQuery("select m from Member m", Member.class)
        .getResultList();
```

- JPQL을 사용하면 JPA는 이것을 분석해서 SQL을 생성(이 때 즉시 로딩이나 지연 로딩에 대해 신경 쓰지 않음)

```sql
select * from members; # 1번 실행으로 5명 조회 
select * from orders where member_id = 1; # 회원과 연관된 조회
select * from orders where member_id = 2; # 회원과 연관된 조회
select * from orders where member_id = 3; # 회원과 연관된 조회
select * from orders where member_id = 4; # 회원과 연관된 조회
select * from orders where member_id = 5; # 회원과 연관된 조회
```

`select * from members;` 가 수행되고, 즉시 로딩이므로 연관된 엔티티인 orders 도 조회된다.

**지연 로딩일 경우**

```java
// 지연 로딩 
@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)

// orders 컬렉션 초기화
for (Member members : members) { // member 마다 getOrders -> select * from orders where member_id = memberId
	sout(member.getOrders().size()); 
}
```

</details>

<details>
    <summary><b>✅ N + 1 문제 해결법?️</b></summary>

- 페치 조인
  - SQL 조인을 사용해서 연관된 엔티티를 조회하므로 N + 1 문제가 발생하지 않음.
  - 일대다 조인 시 결과 개수가 늘어나므로 `distinct`를 사용하여 중복을 제거하는 것에 유의

- 하이버네이트의 `@BatchSize` 옵션
  - 연관된 엔티티를 조회할 때, 지정한 size만큼 SQL의 IN 절을 사용하여 조회.

</details>

<details>
    <summary><b>✅ 프록시를 사용해서 N+1이 생기는데 애초에 프록시를 사용안하면 되는 거 아닌가요?</b></summary>

- 비즈니스 로직에서 연관된 엔티티가 항상 사용되는 것은 아님.
- 사용하지 않는 엔티티를 위해 쿼리를 날리거나 메모리를 사용하는 것은 비효율적.
- 👉 프록시 객체를 이용해서, 실제 엔티티가 사용되기 전까지 데이터베이스에서 조회를 지연하는 것이 `지연 로딩` 

</details>


<details>
    <summary><b>✅ 페치 조인의 한계?</b></summary>

- **일대다 페치 조인 시 결과 데이터 수가 증가**
  - 데이터베이스에서 일대다 관계를 갖는 테이블을 조회 시 `다`가 기준이 되어 결과 반환되기 때문. 
  - 👉 컬렉션 페치조인 시 페이징이 불가능.
    - 일을 기준으로 페이징을 해야하는데, 결과 데이터 개수는 `다`에 맞춰지므로 페이징을 할 수 가 없다. 

- **컬렉션 둘 이상에 페치조인 불가능**
  - 컬렉션과 컬렉션의 **카티전 곱**으로 만들어진다.
  - 하이버네이트 사용 시 예외가 발생함

</details>

<br>

---

## OSIV

<details>
    <summary><b>✅ 준영속 상태와 지연 로딩</b></summary>

- 스프링 컨테이너에서는 트랜잭션과 영속성 컨텍스트의 생명 주기가 같다.
  - 트랜잭션 커밋 -> 플러시 -> DB 트랜잭션 커밋 -> **데이터베이스 커넥션 종료**
- 트랜잭션은 주로 서비스 계층의 메서드 단위로 이루어지므로, 엔티티가 프레젠테이션 레이어에 노출된 시점에서 해당 엔티티는 준영속 상태 (트랜잭션이 끝나 영속성 컨텍스트도 종료 되었으므로)
- 프레젠테이션 레이어에서의 엔티티는 준영속 상태 👉 변경 감지, 지연 로딩(예: `member.getName()`)이 동작하지 않음.

### 준영속 상태에서 지연 로딩이 일어나지 않는 문제를 해결하려면

1. 뷰가 필요한 엔티티를 미리 로딩
   1. 글로벌 페치 전략 수정
      - `지연 로딩`을 `즉시 로딩`으로 변경
      - 문제점
        - 사용하지 않는 엔티티를 로딩
        - N + 1 문제 발생
   2. JPQL 페치 조인
      - 화면에 맞춘 리포지토리 메소드가 증가
      - 프레젠테이션 계층이 알게 모르게 데이터 접근 계층을 침범
   3. 강제로 초기화 
2. `OSIV`를 사용해서 엔티티를 항상 영속 상태로 유지하는 방법

> 💡 트랜잭션이 다르면 다른 영속성 컨텍스트를 사용한다.

> 💡 트랜잭션이 같으면 같은 영속성 컨텍스트를 사용한다.

</details>

<details>
    <summary><b>✅ OSIV 옵션에 대해 설명해주세요.</b></summary>

> 👉 영속성 컨텍스트를 프리젠테이션 레이어까지 열어둠으로써, 준영속 상태에서 지연 로딩이 불가능한 문제를 해결하는 방법.
> - 프리젠테이션 레이어에서 `지연 로딩` 등 엔티티를 적극 활용 가능. 
>   - 코드 중복 감소, 유지보수성 증가.
> - 데이터베이스 커넥션 레이어를 너무 오래물고 있기 때문에 실시간 트래픽이 중요한 애플리케이션에서는 커넥션이 부족할 수 있음. 

--- 

### Open Session In View (OSIV)

- 스프링 컨테이너에서는 트랜잭션과 영속성 컨텍스트의 생명 주기가 같으므로, 일반적으로 프리젠테이션 레이어에서는 엔티티가 준영속 상태
- 준영속 상태에서는 지연로딩이 불가능하므로, 지연 로딩이 가능하게 프리젠테이션 레이어까지 `영속성 컨텍스트`와 `데이터베이스 커넥션`을 열어두는 것이 `OSIV`
- 언제까지 열어두는데?
  - API의 경우 클라이언트에게 응답을 내릴 때까지 연다.
  - 뷰 템플릿의 경우 렌더링 할 때 까지.

- 장점
  - 프리젠테이션 레이어에서 `지연 로딩` 가능.
- 단점 
  - **데이터베이스 커넥션을 너무 오래물고 있기 때문에, 실시간 트래픽이 중요한 애플리케이션에서는 커넥션이 부족**할 수 있다. 👉 **치명적**
    - 예) 컨트롤러에서 외부 API라도 호출하게 되면, 외부 API 대기 시간 만큼 커넥션 리소스를 반환하지 못함. 

### Spring OSIV

- 영속성 컨텍스트는 뷰까지 열려있지만, 트랜잭션은 비즈니스 계층(서비스 ~ 모델) 범위에 적용

### 트랜잭션 없이 읽기

- 영속성 컨텍스트는 트랜잭션 범위 안에서 엔티티를 

</details>

<details>
    <summary><b>✅ OSIV 옵션을 사용할 건지? 본인의 생각은?</b></summary>

- 실시간 트래픽에 따라 할 지 말 지 결정 

- 고객용 서비스는 사용 안 한다. 
  - 데이터베이스 연결을 지속적으로 물고 있는 문제
  - 또 뷰를 렌더링 하는동안 추가 쿼리가 발생할 수 있는 가능성 등

- 이유
  - 클라이언트가 요청한 정보는 서비스에서 책임지고 완성해서 컨트롤러로 응답
  - 뷰는 전달받은 데이터를 신뢰하고 'presentation' 하는 것에만 집중하는 것이 좋다고 생각

- ADMIN은 그냥 사용할 것 같다.
  - 트래픽 적고
  - 뷰에서 자유롭게 지연 로딩 가능.

</details>

<details>
    <summary><b>✅ OSIV 옵션을 꺼두면 단점은 없는지?</b></summary>

- 모든 지연로딩을 트랜잭션 안에서 처리해야한다. 
- 즉, 트랜잭션이 끝나기 전에 사용하는 엔티티가 있다면, 지연 로딩을 강제로 호출해야한다.
  - **기존에 뷰 단에서 지연로딩을 했다면, 코드 변경이 많을 것임**

</details>

<details>
    <summary><b>✅ CQRS? (커맨드와 쿼리를 분리)</b></summary>

- CQRS(Command Query Responsibility Segregation)
- 실무에서 OSIV를 끈 상태로 복잡성을 관리하는 좋은 방법 👉 커맨드와 쿼리를 분리
- 보통 비즈니스 로직은 특정 엔티티 몇 개를 등록하거나 수정하는 것이므로 성능에 큰 영향 X
- 복잡한 화면을 출력하기 위한 쿼리는 **화면에 맞추어 성능을 최적화하는 것이 중요.**
  - 그러나 그 복잡성에 비해 핵심 비즈니스에 영향을 주는 것은 아님
- 둘의 관심사를 분리하는 것은 유지보수성에서 중요.

### 구현

- 기존 OrderService
  - `OrderService`: 핵심 비즈니스 로직 
    - 라이프사이클이 길다. 
    - 비즈니스 정책이 녹아있는 서비스 클래스는 잘 변경되지 않음.
  - `OrderQueryService`: 화면이나 API에 맞춘 서비스(주로 읽기 전용 트랜잭션 사)
    - 라이프사이클이 짧다.
    - 조회용, 화면용 보여주기 위한 것은 자주 변경된다. 👉 핵심 비즈니스 로직과 쿼리용 서비스 객체를 분리. 

</details>

## 낙관적 락, 비관적 락

<br>

---

## JPA Proxy

<details>
    <summary><b>JPA Proxy에 대해 설명해주세요.</b></summary>
</details>


<br>

---