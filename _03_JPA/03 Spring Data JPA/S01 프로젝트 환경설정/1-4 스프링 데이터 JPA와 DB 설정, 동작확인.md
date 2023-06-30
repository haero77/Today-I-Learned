### @PersistContext

```java
@PersistenceContext
private
EntityManager em;
```

- `@PersistContext` 사용하면 스프링 컨테이너에 의해 자동으로 `EntityManager` 주입

<br>

### JPA 생성자가 private이 아니라 protected인 이유

- JPA 표준 스펙에서, 엔티티는 디폴트 생성자가 있어야한다.
- private이 아니라 protected까지만 허용하는 이유는
  - JPA가 `프록시` 같은 기술을 사용하는데,
  - 하이버네이트 같은 구현체들이 강제로 객체를 만들어내야하는데 그때 디폴트 생성자를 private으로 막아놓으면 객체를 못 만들기 때문.

<br>

### JPA의 모든 변경은 트랜잭션 안에서 이루어져야 한다.

- 트랜잭션 없이 변경하면 아래와 같은 에러가 난다.

```java
No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call; nested exception is javax.persistence.TransactionRequiredException: No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call
```

- `org.springframework.transaction.annotation.Transactional` 을 사용하자
  - `javax.Transactional` 보다 기능이 더 많다.

<br>

### 테스트의 @Transactional은 자동으로 롤백이된다.

- 테스트에 `@Transactional`을 사용하면,
  - 그냥 테스트를 실행하면, 테이블 생성 쿼리만 보이고 다른 쿼리는 나오지 않는다.
  - 테스트 종료후 자동으로 롤백하고, 영속성 컨텍스트에 플러시도 안 한다.
    - `Rolled back transaction for test`
- `@Rollback(value = false)`을 통해 롤백을 막을 수 있다.

<br>

### 같은 트랜잭션안에서, 영속성 컨텍스트는 동일성을 보장한다.

```java
assertThat(findMember).isEqualTo(member); // true
```

- `equals()`를 오버라이딩 하지 않았으므로 `==` 비교인데 true가 나온다.

<br>

### p6spy

```java
// build.gradle
dependencies {
    implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.7.1' // 1.7.1 버전을 써야 정상 작동
}
```

- 실무에서는 성능테스트 해보고 사용해야한다. 