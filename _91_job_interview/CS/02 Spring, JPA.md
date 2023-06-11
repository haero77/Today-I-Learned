# Spring Framework

<details>
    <summary><b>🔼 Spring 은 왜 쓰나요?</b></summary>

- Java 기반의 프레임워크 👉  **Java가 갖는 객체 지향 언어의 특성을 잘 살릴 수 있는 프레임워크** 
- 예) IoC, DI의 개념을 활용하여 다형성을 충분히 만족시킴   

</details>

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
    <summary><b>✅ Framework?</b></summary>

- 프레임워크란 응용 프로그램이나 소프트웨어 솔루션 개발을 수월하기 위해 구조, 틀이 제공된 소프트웨어 환경

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

- 객체의 생성에서부터 생명주기의 관리까지 모든 객체에 대한 제어권이 바뀐 것을 의미 
  - 또는 제어 권한을 자신이 아닌 다른 대상에게 위임하는 것

- 개발자는 프레임워크에 필요한 부품을 개발하고 조립하는 방식으로 개발 &  최종 호출은 개발자가 아니라 프레임워크의 내부에서 결정된 대로 이뤄지게 되는데 이런 현상을 `제어의 역전`이라고 함

- Spring에서는 `IoC 컨테이너`를 통해 객체의 생성주기를 관리함으로써 `IoC`를 구현

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
    <summary><b>JPA와 같은 ORM을 사용하는 이유가 뭔가요?</b></summary>
</details>

<details>
    <summary><b>JPA와 같은 ORM을 사용하는 이유가 뭔가요?</b></summary>
</details>

<details>
    <summary><b>Hibernate란?</b></summary>
</details>

<br>

---

## 영속성 컨텍스트

<details>
    <summary><b>영속성 컨텍스트란?</b></summary>
</details>

<details>
    <summary><b>언제 flush가 일어나는지? ⭐️</b></summary>
</details>

<details>
    <summary><b>@Transactional은 어떤 기능을 하나요?</b></summary>
</details>

<details>
    <summary><b>@Transactional(readonly=true) 는 어떤 기능인가요? 이게 도움이 되나요?</b></summary>
</details>

<details>
    <summary><b>그런데, 읽기에 트랜잭션을 걸 필요가 있나요? @Transactional을 안 붙이면 되는거 아닐까요?</b></summary>
</details>

<br>

---

## N + 1

<details>
    <summary><b>N + 1 문제란? ⭐️</b></summary>
</details>


<details>
    <summary><b>Eager Loading일 때는 N + 1 문제가 안 일어나는지? ⭐️</b></summary>
</details>

<details>
    <summary><b>N + 1 문제 해결법? ⭐️</b></summary>
</details>

<details>
    <summary><b>어떤 경우든 Fetch Join을 이용하여 문제를 해결 할 수 있나요?</b></summary>
</details>

<details>
    <summary><b>둘 이상의 컬렉션에 왜 Fetch Join 사용이 불가능한가요?</b></summary>
</details>

<details>
    <summary><b>일대다 관계를 가지는 엔티티를 Fetch Join 할 때 발생할 수 있는 문제점?</b></summary>
</details>

<details>
    <summary><b>@BatchSize 에 대해서 설명해주세요.</b></summary>
</details>

<details>
    <summary><b>프록시를 사용해서 N+1이 생기는데 애초에 프록시를 사용안하면 되는 거 아닌가요?</b></summary>
</details>


<br>

---

## OSIV

<details>
    <summary><b>OSIV 옵션에 대해 설명해주세요.</b></summary>
</details>

osiv의 장단점 얘기해주세요!

장점:
LazyInitializationException 예외를 방지하여 지연로딩된 데이터에 쉽게 접근할 수 있습니다.
트랜잭션 관리가 편리해지며, 일관된 세션 상태를 유지할 수 있습니다.

단점:
데이터베이스 커넥션 리소스를 오랫동안 유지해야 하므로, 커넥션 풀 리소스 사용에 주의해야 합니다.
오용되면 성능 저하나 데이터 일관성 문제를 야기할 수 있습니다.
OSIV에서 S는 무엇을 의미하나요?

세션으로 영속성 컨텍스트를 의미합니다.
그럼 컨트롤러에서 엔티티 수정 안되겠네요?

만약 엔티티를 수정 후 트랜잭션을 시작하는 서비스의 메소드가 호출되어
해당 트랜잭션이 커밋되면 엔티티가 수정될 수 있습니다.
스프링에서 OSIV의 기본값은 뭐죠?

true입니다.
프로젝트에서 osiv 를 끄고 컨트롤러단에 엔티티를 안보내셨던데 왜 그러셨죠?

osiv를 끄고 엔티티를 컨트롤러에 보내면 영속성 컨텍스트 밖에서 지연로딩 기능을 사용하려했을 경우 예외가 발생할 수 있기 때문입니다.
스프링 osiv를 적용하면 flush는 어느지점에 일어나나요?

트랜잭션이 커밋할 때 발생합니다.

<br>

---

## JPA Proxy

<details>
    <summary><b>JPA Proxy에 대해 설명해주세요.</b></summary>
</details>


<br>

---