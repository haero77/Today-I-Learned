> 🎯 GOAL
> - JPA에서 제공하는 `deleteAll()`과 `deleteAllInBatch()`의 차이점을 이해한다. 
> - 테스트 코드에서 `tearDown()`을 쓸 때 발생할 수 있는 문제점을 인지한다.

# 들어가며

[지난 포스팅](https://velog.io/@balparang/테스트-코드의-Transactional은-주의해서-사용하자)에서, 테스트 코드에서 테스트 메서드 종료 후 Repository를 clear 해주기 위해 `@Transactional` 어노테이션을 사용하게 되면 프로덕션 코드에 마치 `@Transactional`이 있는 것처럼 착각할 수 있기 때문에 이 점을 주의해서 사용해야함을 알 수 있었습니다. 본 포스팅에서는 테스트 코드에서 `@Transactional` 사용 시 위험성을 방지하기 위해 `tearDown()` 메서드를 사용할 때 자주 사용되는 `deleteAll()`과 `deleteAllInBatch()`의 차이점에 대해 살펴봅니다.

<br>

# 본문

## deleteAll()

```java
package org.springframework.data.repository;

@NoRepositoryBean
public interface CrudRepository<T, ID> extends Repository<T, ID> {
	
    ...
    
    /**
     * Deletes all entities managed by the repository.
     */
    void deleteAll();
    
    ...
	
}
```

`deleteAll()`은 인터페이스 `org.springframework.data.repository.CrudRepository`에 정의 되어있습니다. 해당 메서드는 구현체인 `org.springframework.data.jpa.repository.support.SimpleJpaRepository`에 다음과 같이 오버라이딩 되어있습니다. 

```java
package org.springframework.data.jpa.repository.support;

@Repository
@Transactional(readOnly = true)
public class SimpleJpaRepository<T, ID> implements JpaRepositoryImplementation<T, ID> {

    ...

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
     */
    @Override
    public List<T> findAll() {
        return getQuery(null, Sort.unsorted()).getResultList();
    }
	
    ...
	
    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.Repository#deleteAll()
     */
    @Override
    @Transactional
    public void deleteAll() {
    
        for (T element : findAll()) {
            delete(element);
        }
    }
	
    ...
	
}
```

`deleteAll()`이 실행되면, `findAll()`의 결과로 얻은 리스트를 순회하며 데이터를 한 개씩 삭제합니다. 즉, **N개의 데이터가 Repository에 존재하면 N개의 DELETE 쿼리가 실행**됩니다. 결국 데이터가 많아질 수록 테스트 실행 시간이 오래 걸릴 것이고, 이는 테스트 코드의 `FIRST` 원칙 중 `Fast`를 만족시키지 못하는 것으로도 볼 수 있습니다.  
<br>

### deleteAll()의 쿼리

```java
// 테스트 코드 
@AfterEach
void tearDown() {
    teamRepository.deleteAll();
}
```

<img width="933" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/ae33f36c-4129-49a1-8428-8d03d980079f">

예를 들어 `TeamRepository`에 `Team`을 2개 저장하는 테스트 케이스의 경우, `deleteAll()`을 사용하면 위처럼 DELETE 쿼리가 2번 나가는 것을 확인할 수 있습니다. 

<br>

## deleteAllInBatch()

```java
package org.springframework.data.jpa.repository;

@NoRepositoryBean
public interface JpaRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
	
    ...

    /**
     * Deletes all entities in a batch call.
     */
    void deleteAllInBatch();
	
    ...
	
}
```

`deleteAllInBatch()`는 `deleteAll()`과는 달리, `org.springframework.data.jpa.repository.JpaRepository` 인터페이스에 정의되어있습니다. `deleteAll()` 이 '스프링 데이터' 프로젝트에 속해 MyBatis, JdbcTemplate 등 기술에 상관없이 사용가능한 반면에 `deleteAllInBatch()`는 '스프링 데이터 JPA' 프로젝트에 속하다보니 반드시 JPA를 통해서만 사용가능합니다. 

```java
package org.springframework.data.jpa.repository.support;

import static org.springframework.data.jpa.repository.query.QueryUtils.*;

@Repository
@Transactional(readOnly = true)
public class SimpleJpaRepository<T, ID> implements JpaRepositoryImplementation<T, ID> {

    ...

    private String getDeleteAllQueryString() {
        return getQueryString(DELETE_ALL_QUERY_STRING, entityInformation.getEntityName());
    }
    
    ...
	
    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#deleteAllInBatch()
     */
    @Override
    @Transactional
    public void deleteAllInBatch() {
        em.createQuery(getDeleteAllQueryString()).executeUpdate();
    }
	
    ...

}


```

`deleteAllInBatch()`의 구현은 `deleteAll()`과 마찬가지로 구현체 `SimpleJpaRepository`에서 담당하고 있습니다. `deleteAllInBatch()`가 호출되면 내부적으로 `getDeleteAllQueryString()`을 호출하고, `org.springframework.data.jpa.repository.query.QueryUtils` 에 정의되어 있는 상수 `DELETE_ALL_QUERY_STRING`를 가져와서 쿼리를 실행합니다. 

```java
package org.springframework.data.jpa.repository.query;

public abstract class QueryUtils {
	
    ...

    public static final String DELETE_ALL_QUERY_STRING = "delete from %s x";
    
    ...
	
}
```

<br>

### deleteAllInBatch()의 쿼리

```java
// 테스트 코드 
@AfterEach
void tearDown() {
    teamRepository.deleteAllInBatch();
}
```

<img width="1211" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/3c92c490-a3f4-4f98-9924-46e1e52c1106">

결국 테이블에 있는 데이터를 전부 지우는 DELETE 쿼리가 실행됩니다. 즉, **데이터 크기와 관계없이 한 번의 쿼리로도 Repository를 clear 할 수 있게 되므로, `deleteAll()` 보다는 `deleteAllInBatch()` 사용이 테스트 속도를 고려했을 때 우선적으로 사용하는 것이 좋음**을 알 수 있습니다.


<br> 

## tearDown()의 문제: 참조 무결성 제약 조건

`deleteAll()` 대신 `deleteAllInBatch()`을 사용하는 것이 좋다는 것은 이제 충분히 이해했습니다. 그럼 한 걸음 더 가서, 테스트 코드에서 `tearDown()`을 사용할 때 발생할 수 있는 문제점을 살펴보죠.

여기 `Member` 와 `Team` 엔티티가 있다고 가정하겠습니다. Member : Team 은 다대일 관계로, `@ManyToOne`을 통해 연관관계를 매핑해주고 있습니다. 

```java
// Member Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    
    private String username;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    
    public Member(String username, Team team) {
        this.username = username;
        this.team = team;
    }

}

// Team Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    public Team(String name) {
        this.name = name;
    }

}
```

그리고 두 엔티티를 사용하는 테스트 케이스가 다음과 같이 있습니다.

```java
@SpringBootTest
class SampleTest {

    @Autowired
    private TeamRepository teamRepository;
    
    @Autowired
    private MemberRepository memberRepository;
    
    @AfterEach
    void tearDown() {
        teamRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();
    }
    
    @Test
    void sample() {
        // given
        Team team1 = new Team("team1");
        teamRepository.save(team1);
    
        Member member1 = new Member("member1", team1);
        memberRepository.save(member1);
    
        // when
        ...
        
        // then
        ...
    }

}
```

여기서 퀴즈.

이 테스트 케이스 `sample()`은 정상적으로 실행되고 종료될 수 있을까요?

잠깐 생각해봅시다.



- Member 는 FK로 `team_id`를 갖는다.
- `tearDown()`에서는 Team 테이블의 데이터를 먼저 삭제한다.
- ...


_**아!**_ 

그렇습니다. Member 가 참조하고 있던 Team 테이블의 데이터를 먼저 삭제하게 되면 Member 는 참조할 수 없는 PK를 참조하게 됩니다. 즉, **참조 무결성 제약 조건 (referential integrity constraint)을 위반**하게 되는거죠.

<img width="1294" alt="254282389-2ed37971-f529-43b7-8067-84ba02476d76" src="https://github.com/haero77/Today-I-Learned/assets/65555299/77ff2177-2916-47e5-8cbd-76eaa3fb1273">

_(실제로 로그를 확인해보면 친절하게 참조 무결성 제약 조건을 위반하고 있다고 알려줍니다.)_

<br>

### 참조 무결성 제약조건을 신경 써야해

그럼 이 문제를 어떻게 해결할까요? 간단합니다. 

👉 **_참조 무결성 제약 조건을 신경 쓰면서 Repository를 clear 한다._**

```java
@SpringBootTest
class SampleTest {
	
    ...
    
    @AfterEach
    void tearDown() {
        // 참조 무결성 제약 조건을 위반하지 않도록 Member 먼저 삭제
        memberRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
    }
	
    ...

}
```

<img width="659" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/a1a8a965-9f7a-4a1e-a52b-39efe2e64608">

_(TC가 성공하는 것을 확인할 수 있습니다.)_
 

지금이야 겨우 엔티티 2개만 연관관계를 맺으니까 신경쓰는 것이 어렵지 않습니다. 그런데 엔티티가 많아지면요? 그만큼 신경쓰기가 어려워집니다. 당장 위 케이스만 보더라도 새로운 엔티티가 추가된 TC가 생길 겨우 `tearDown()` 메서드도 변경사항이 생기고 무엇을 먼저 삭제할지 신경 써야합니다. 이럴 바에 그냥 속편히 `@Transactional`을 사용하는 쪽에 손을 들어줄 수도 있겠네요. 여튼 강조하고자 하는 것은, `tearDown()`을 사용할 때는 **참조 무결성 제약 조건을 신경쓰자!** 라는 겁니다.

<br>

# 마치며 

본 포스팅을,

- `deleteAll()` 말고 테스트 시간 상 유리한 `deleteAllInBatch()`을 사용하자.
- `tearDown()`을 사용할 때는 **참조 무결성 제약 조건을 신경쓰자.**

정도로 요약해볼 수 있을 것 같습니다. 

`@Transactional`을 사용할 지, `tearDown()`을 사용할 지 결론을 못 내려서 조금 찝찝하긴 한데, 그 부분은 컨벤션에 따르면 되는 부분이라 생각합니다. 그보다 **중요한 것은 각각 사용했을 때의 문제점을 정확히 인지하고 있는가**이겠지요. 

테스트 코드를 공부하며, 코드를 작성하는 것이 더욱 즐거워졌습니다. TDD를 배우기 시작하면서 마치 테스트 케이스와 내가 대화하는 느낌이 들기도 하는 요즘입니다. 더 좋은 테스트를 짜기 위해서는 어떻게 해야할지, 꾸준히 공부해봐야겠군요.  

_마침._

<br>

### Reference

- https://stackoverflow.com/questions/58231638/whats-the-difference-between-deleteallinbatch-and-deleteall
- https://ssdragon.tistory.com/115
- https://blog.yevgnenll.me/posts/jpa-use-not-deleteall-but-deleteallinbatch
