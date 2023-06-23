# 통합 테스트 

- 모듈 A + B 의 결과가 AB가 될지 BA가 될지, C가 될지 알 수 없다.
- 단위 테스트 만으로는 커버 불가능한 영역이 있음.

<br>

### 통합 테스트 

- _**"여러 모듈이 협력하는 기능을 통합적으로 검증하는 테스트"**_
- 일반적으로 작은 범위의 단위 테스트만으로는 기능 전체의 신뢰성을 보장할 수 없다.
- 풍부한 단위 테스트 & 큰 기능 단위를 검증하는 통합 테스트

<br>

---

# Spring & JPA 훑어보기 

## Spring 


<img width="600" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/6780e75b-46d4-4ffd-a95b-38f32dc1e2e0">

- Library Vs. Framework

### 3대 개념

- `IoC(Inversion of Control)`
  - 필요한 객체를 직접 생성하면 너무 강결합 👉 약결합으로 만들기 위한 방법이 IoC 
  - 객체의 생성 주기에 대한 관리를 제3자(IoC 컨테이너)가 주관 
    - 이 과정에서 `DI`가 나옴
- `DI(Dependency Injection)`
  - 기능을 명세한 인터페이스를 주입
  - 주는 객체를 사용하기만 한다. 
- `AOP(Aspect Oriented Programming)`
  - 비즈니스 로직과 관계없는 부분 -> `관점`
    - ex) 트랜잭션 등 

<br>

## JPA

### ORM

- 객체 지향 패러다임과 관계형 DB 패러다임의 불일치
- 이전에는 개발자가 객체의 데이터를 한땀한땀 매핑하여 DB에 저장 및 조회(CRUD)
- ORM을 사용함으로써 개발자는 단순 작업을 줄이고, 비즈니스 로직에 집중할 수 있다.



### JPA

- Java 진영의 ORM 기술 표준
- `인터페이스`이고, 여러 구현체가 있지만 보통 Hibernate를 많이 사용한다.
- 반복적인 CRUD SQL을 생성 및 실행해주고, 여러 부가 기능들을 제공한다.
- 편리하지만 **쿼리를 직접 작성하지 않기 때문에,**
  - 👉 **어떤 식으로 쿼리가 만들어지고 실행되는지 명확하게 이해하고 있어야 한다.**

- Spring 진영에서는 JPA를 한 번 더 추상화한 Spring Data JPA 제공
- QueryDSL과 조합하여 많이 사용한다. (타입체크, 동적쿼리)


# 레이어별 테스트

# Persistence Layer

- Data Access의 역할
- 비즈니스 가공로직이 포함되어서는 안 된다.
- Data에 대한 CRUD에만 집중한 레이어

# Business Layer

- 비즈니스 로직을 구현하는 역할
- Persistence Layer와의 상호작용(Data를 읽고 쓰는 행위)을 통해 비즈니스 로직을 전개시킨다.
- 트랜잭션을 보장해야한다.

### 추가된 요구사항

> ✅ 상품 번호 리스트를 받아 주문 생성하기 <br>
> ✅ 주문은 주문 상태, 주문 등록 시간을 가진다. <br>
> ✅ 주문의 총 금액을 계산할 수 있어야 한다.

### @DataJpaTest의 @Transactional

- 서비스 레이어는 클렌징 해주지 않아 테스트가 실패한다.
- Repository 테스트는 `tearDown()`으로 클렌징 해주지 않아도 테스트가 통과함. 왜일까?
  - Repository 레이어는 `@DataJpaTest`를 사용. -> `@Transactional 붙어있음` -> 테스트가 끝나고 롤백된다.