# 스프링 데이터 JPA 구현체 분석

- 스프링 데이터 JPA가 제공하는 공통 인터페이스의 구현체
  - `org.springframework.data.jpa.repository.support.SimpleJpaRepository`

<br>

### @Repository 의 역할

- 컴포넌트 스캔으로 스프링 빈 등록
- **영속성 계층의 예외를 스프링에서 사용가능한 예외로 변환한다.**
  - JDBC, JPA, ... 등 전부 예외가 다르다.
  - **하부 기술을 JPA에서 JDBC로 바꿔도 예외 핸들링 과정은 동일하다!!**

<br>

### 모든 JPA에서의 변경은 트랜잭션 안에서 일어나야 한다 ⭐️

- 스프링 데이터 JPA를 사용하면 트랜잭션이 전부 적용된다. 
  - 구현체 `SimpleJpaRepository`의 메서드에 `@Transactional` 적용되어 있음. 
- 서비스 계층에서 트랜잭션을 시작하지 않으면 리포지토리에서 트랜잭션 시작
- 서비스 계층에서 트랜잭션을 시작하면 리포지토리는 해당 트랜잭션을 **전파 받아서 사용**한다.


- `@Transactional(readOnly=true)`
  - 데이터를 단순히 조회만하고 변경하지 않는 트랜잭션에서 `readOnly = true` 옵션을 사용하면 플러시를 생략해서 약간의 성능 향상을 얻을 수 있음.
    - 트랜잭션이 끝날 때 기본적으로는 `플러시` 실행.
  - 자세한 내용은 JPA 책 15.4.2 읽기 전용 쿼리의 성능 최적화 참고

<br>

### save() 메서드 ⭐️⭐️️

```java
@Transactional
@Override
public <S extends T> S save(S entity) {

    Assert.notNull(entity, "Entity must not be null.");

    if (entityInformation.isNew(entity)) {
        em.persist(entity);
        return entity;
    } else {
        return em.merge(entity);
    }
}
```

- 새로운 엔티티면 저장(`persist`)
- 새로운 엔티티가 아니면 병합(`merge`)
  - DB에 한 번 들어갔다 나온 경우
  - merge
    - DB에 있는 데이터를 가져와서, save 할려는 엔티티로 덮어쓴다.
    - 단점: **일단 DB SELECT 쿼리를 먼저 한다.**
- **데이터 변경은 merge 가 아니라 변경 감지로 해야한다.**