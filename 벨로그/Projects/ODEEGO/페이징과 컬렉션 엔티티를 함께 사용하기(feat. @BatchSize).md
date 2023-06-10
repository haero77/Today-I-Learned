![](https://velog.velcdn.com/images/balparang/post/cbcfbb8d-c3b1-4acc-9295-e5da59fa5f3a/image.png)


# 들어가며

[오디고 프로젝트](https://github.com/prgrms-web-devcourse/Team-PODO-ODEEGO-BE)의 장소 조회 페이징 API를 개발하던 중, `1:N 연관관계`에서 `N`에 해당하는
엔티티를 조회할 때 N + 1 문제가 발생하는 것을 확인했다. 문제 해결 과정에서 `지연 로딩을 수행하는 컬렉션 래퍼`, `페치 조인의 한계`, `Batch Size 옵션` 등을 알게 되었고, 결과적으로는 `@BatchSize`를 이용하여 문제를 해결할 수 있었다. 본 포스팅에서는 **페이징 시 발생한 N+1 문제의 원인을 찾고, 문제를 해결해나간 과정**을 기록한다.

<br>

# 본론

## 엔티티 연관관계 요약

<img width="304" alt="image" src="https://github.com/haero77/Shortify/assets/65555299/8535f389-d7d0-4a99-843a-cfc95659f5db">

_(Place, PlaceImage 의 ERD)_

왜 N+1 문제가 발생했는지 설명하기 전에, 먼저 관련된 엔티티의 연관관계에 대해 간단히 살펴본다. `Place`는 장소를 나타내며, `PlaceImage`는 Place에 관련된 이미지 소스를 담는 엔티티이다. 한
개의 `Place`는 여러 개의 `PlaceImage`를 가질 수 있으므로, `Place : PlaceImage = 1 : N` 관계이다.

일대다 관계의 경우 일반적으로 `@ManyToOne`을 사용하여 `다대일 단방향 매핑`을 사용하지만,

- **새로운 `이미지`를 추가할 때, `이미지`를 생성하고 이미지에 `장소`를 할당하는 것보다, `장소`의 이미지 리스트에 `이미지`를 추가하는 것이 로직상 자연스럽게 읽힌다는 점**
- **Place를 조회 시 PlaceImage 도 같이 조회해야하는 일이 잦다는 점**
  - Place를 조회하고, SELECT문으로 해당 Place에 매핑된 PlaceImage를 조회하는 것보다, Place 조회 후 컬렉션 필드를 조회함으로써 연관된 엔티티를 바로 조회하는 것이 로직 상으로 더 직관적이기 때문.

의 이유로 `다대일 양방향 매핑`을 사용하였다.

```java
// Place 엔티티
@Entity
public class Place {
    // ...

    @OneToMany(mappedBy = "place")
    private final List<PlaceImage> images = new ArrayList<>();
}

// PlaceImage 엔티티
@Entity
public class PlaceImage {
    // ...

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;
}
```

<br>

## 컬렉션 엔티티 조회 시 N+1 문제 발생

필자가 수행해야 하는 작업은

1. 특정 stationName을 갖는 Place를 찾는다.
2. Place의 PlaceImage를 조회하여 페이징이 가능한 DTO로 변환한다.

이렇게 두 가지였다.

먼저 `1. 특정 stationName을 갖는 Place를 찾는다.`를 수행하기 위해 Spring Data Jpa의 쿼리 메소드를 사용하였다. 동시에 페이징이 가능해야 하므로, `Pageable`을 이용하여 페이징 하도록 구현했다.

```java
// PlaceRepository DAO 객체
public interface PlaceRepository extends JpaRepository<Place, Long> {

    Page<Place> findPlacesByStationName(String stationName, Pageable pageable);
}
```


`PlaceRepository::findPlacesByStationName`을 사용하여 `Place`를 영속 상태로 만들고나서,

`2. Place의 PlaceImage를 조회하여 페이징이 가능한 DTO로 변환한다.`

를 수행하기 위해 컬렉션 엔티티인 `List<PlaceImage>`를 조회하는 작업을 DTO에서 진행했다.

<br>


<img width="1188" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/82aee719-276b-4807-ac54-39e0c8bfdee9">

_(N + 1 문제가 발생하는 곳. 파라미터로 받은 컬렉션 래퍼 `images`가 지연 로딩을 대신 처리한다.)_

문제는 여기서 발생했다. 하이버네이트가 `Place`를 영속 상태로 만들 때, 엔티티 `Place`가 컬렉션 엔티티 `List<PlaceImage> images`를 가지므로 하이버네이트는 해당 필드를 **원본 컬렉션 대신 `컬렉션 래퍼`로 대체**한다. 컬렉션 래퍼가 지연 로딩을 대신 수행하므로, 위 코드의 스트림 연산처럼 **실제로 컬렉션에서 데이터를 조회할 때 DB를 조회해서 컬렉션이 초기화**된다.

<img width="787" alt="238828276-82aee719-276b-4807-ac54-39e0c8bfdee9" src="https://github.com/haero77/Today-I-Learned/assets/65555299/cae27f8c-a05f-40cd-b02f-12ec9dbd921a">

_(디버깅 화면. 실제 컬렉션 대신 컬렉션 래퍼인 `org.hibernate.collection.internal.PersistentBag` 가 파라미터로 넘어온 것을 확인할 수 있다. )_

<br>

정리하자면, <br>
Place를 조회할 때 한 개의 쿼리가 실행되고, 결과로 N개의 Place가 영속화된 상태에서 <br>
각 Place에 대해 컬렉션 엔티티를 조회하므로 N개의 쿼리가 실행되어 `N+1 문제`가 발생한 것이다.


```sql
# Place 를 조회하는 쿼리 1개 (N개의 Row가 조회된다.)
select
    place0_.place_id as place_id1_4_,
    place0_.address as address2_4_,
    place0_.category as category3_4_,
    place0_.name as name4_4_,
    place0_.share_url as share_ur5_4_,
    place0_.station_name as station_6_4_ 
from
    place place0_ 
where
    place0_.station_name=? 
order by
    place0_.place_id asc limit ?

# PlaceImage 를 조회하는 쿼리 N개
select
  images0_.place_id as place_id4_5_1_,
  images0_.place_image_id as place_im1_5_1_,
  images0_.place_image_id as place_im1_5_0_,
  images0_.url as url2_5_0_,
  images0_.place_id as place_id4_5_0_,
  images0_.source as source3_5_0_
from
  place_image images0_
where
  images0_.place_id=?

# ...
```

<br>

## 컬렉션 페치 조인을 이용한 N+1 문제 해결

컬렉션 엔티티 조회 시 발생하는 N + 1 문제는 컬렉션 페치 조인을 이용하여 해결할 수 있으므로, `@Query`를 사용하여 직접 페치 조인 JPQL을 작성함으로써 페치 조인을 적용했다.

```java
public interface PlaceRepository extends JpaRepository<Place, Long> {

  @Query(value = "select distinct p from Place p join fetch p.images where p.stationName=:stationName",
      countQuery = "select count(distinct p) from Place p inner join p.images where p.stationName=:stationName")
  Page<Place> findPlacesByStationName(@Param(value = "stationName") String stationName, Pageable pageable);
}
```

(_[스프링 데이터 JPA에서 fetch join이 들어간 경우 Count 쿼리를 정상적으로 만들어내지 못하므로, count 쿼리를 별도로 분리하였다.](https://www.inflearn.com/questions/167730/%ED%8E%98%EC%9D%B4%EC%A7%95-%EC%A7%88%EB%AC%B8)_)

```sql
select
    distinct place0_.place_id as place_id1_4_0_,
    images1_.place_image_id as place_im1_5_1_,
    place0_.address as address2_4_0_,
    place0_.category as category3_4_0_,
    place0_.name as name4_4_0_,
    place0_.share_url as share_ur5_4_0_,
    place0_.station_name as station_6_4_0_,
    images1_.url as url2_5_1_,
    images1_.place_id as place_id4_5_1_,
    images1_.source as source3_5_1_,
    images1_.place_id as place_id4_5_0__,
    images1_.place_image_id as place_im1_5_0__ 
from
    place place0_ 
inner join
    place_image images1_ 
        on place0_.place_id=images1_.place_id 
where
    place0_.station_name=? 
order by
    place0_.place_id asc
```

(_페치 조인이 적용된 Query_)

페치 조인을 적용하면 Place를 조회하는 시점에 PlaceImage도 같이 조회되므로, N+1 문제를 해결할 수 있었다.

그러나, 이렇게 페이징 시 페치 조인을 사용하면 하이버네이트는 아래와 같이 메모리와 관련한 경고 로그를 남긴다.

```java
WARN 10249 --- [nio-8080-exec-1] o.h.h.internal.ast.QueryTranslatorImpl   : HHH000104: firstResult/maxResults specified with collection fetch; applying in memory!
```

컬렉션 페치 조인을 사용하면 페이징이 불가능하므로, 하이버네이트는 DB레벨에서 페이징 하지 못하고 우선 데이터를 모두 가져온 다음 애플리케이션 메모리에서 페이징을 하는 것이다. 이것은 `Out Of Memory Error`를 야기할 수 있기 때문에, 위와 같은 경고 로그를 남기는 것이다.

그렇다면 왜 컬렉션 페치 조인을 사용하면 페이징이 불가능할까?

<br>

### 페치 조인의 한계: 컬렉션을 페치 조인하면 페이징이 불가능

**컬렉션 페치 조인을 사용하면 페이징이 불가능하다.** 일대다 관계에서 `일`을 기준으로 페이징을 하고 싶은데, 페치 조인의 결과로 생성되는 row는 `다`가 기준이 되기 때문이다. 사례로 더 자세히 알아보자.

`Place : PlaceImage = 1 : N` 관계를 갖고, 나는 `Place`를 기준으로 페이징을 하고 싶다.

단순히 Place를 기준으로 조회할 때는 문제가 없이 페이징이 가능하다. row 개수가 페이징 기준인 Place 개수와 일치하기 때문이다.

| place_id | 
|:--------:|
|    1     |
|    2     |

_(전체 Place는 2개이고, 결과 row도 2개이다.)_

<br>

이번에는 컬렉션 페치 조인을 사용하여 Place와 PlaceImage를 함께 조회한 경우를 생각해보자.


| place_id | place_image_id | 
|:--------:|:--------------:|
|    1     |      101       |
|    1     |      102       |
|    2     |      201       |
|    2     |      202       |

_(전체 Place는 2개이고, 결과 row는 4개이다.)_

Place(`일`)를 기준으로 페이징하고 싶은데, 결과 row는 PlaceImage(`다`)의 개수에 맞춰져 있다. (일대다 관계를 갖는 테이블을 조인했으니, 이는 당연한 결과이다.) 따라서 DB 입장에서는 페이징을 해야하는 기준과 결과 row가 달라서 페이징을 할 수 없으므로, 하이버네이트는 모든 데이터를 DB에서 가져와 메모리에서 페이징을 할 수밖에 없던 것이다. 다행히, 하이버네이트에서 제공하는 Batch Size 옵션을 사용하면 컬렉션 엔티티 조회와 페이징을 같이 사용할 수 있다.

<br>

## @BatchSize를 이용하여 N+1 문제 해결하기

```java
import org.hibernate.annotations.BatchSize;

@Entity
public class Place {
	
    // ...

    @BatchSize(size = 100) // size는 일반적으로 100~1000을 사용한다. 
    @OneToMany(mappedBy = "place")
    private final List<PlaceImage> images = new ArrayList<>();
}
```

컬렉션 엔티티 조회 시 발생하는 N+1 문제를 해결하기 위해, `@OneToMany`로 매핑된 컬렉션 엔티티 필드에 `org.hibernate.annotations.BatchSize` 어노테이션을 작성하고 size는 100으로 지정했다. `@BatchSize`를 사용하면, 연관된 엔티티 조회 시 지정한 size 만큼 `IN` 쿼리를 사용하여 조회한다.

```sql
select
    place0_.place_id as place_id1_4_,
    place0_.address as address2_4_,
    place0_.category as category3_4_,
    place0_.name as name4_4_,
    place0_.share_url as share_ur5_4_,
    place0_.station_name as station_6_4_
from
    place place0_
where
    place0_.station_name=?
order by
    place0_.place_id asc limit ?

select
    images0_.place_id as place_id4_5_1_,
    images0_.place_image_id as place_im1_5_1_,
    images0_.place_image_id as place_im1_5_0_,
    images0_.url as url2_5_0_,
    images0_.place_id as place_id4_5_0_,
    images0_.source as source3_5_0_ 
from
    place_image images0_ 
where
    images0_.place_id in (
        ?,?,?,?,?,?,?,?,?,?,?,?
    )
```

_(@BatchSize 적용 후 실행 쿼리)_

기존에는

- Place 를 조회하는 쿼리 1개
- 조회된 N개의 Place에 대해 연관된 엔티티를 조회하는 쿼리 N개

로 인해 N+1 문제가 발생했다면, @BatchSize를 적용한 후에는

- Place 를 조회하는 쿼리 1개 (Place가 N개 조회된다.)
- 조회된 Place N개와 연관된 컬렉션 엔티티를 조회하는 `IN` 쿼리 1개

의 쿼리가 실행된다.

즉, `1 + N` 번 실행 되었던 쿼리가 `1 + 1`번 실행됨으로써 N+1 문제를 해결한 것이다.

<br>

### 성능 테스트

JMeter 를 이용하여 로컬 환경에서 간단히 테스트 해봤다.

<img width="1252" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/b7ebcc10-1bae-48cf-b5de-63f5c0d0b715">

100명의 사용자가 동시에 요청을 보내며, 이 과정을 50회 반복하도록 설정했다. 각 루프마다는 다른 사용자가 요청을 보내도록 지정하여 테스트를 수행했다. 

<img width="1264" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/5e244d3c-d533-4531-ae89-0c25a63ab575">

_(@BatchSize 적용 전: 1+N 쿼리 실행)_

<br>
<br>

<img width="1268" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/4eb88e04-1585-4a4b-9972-b651738f4f4d">

_(@BatchSize 적용 후: 1+1 쿼리 실행)_


N+1 문제를 해결한 후 해당 로직을 사용하는 API 응답속도를 측정해보니 평균응답 속도가 1.491초에서 0.312초로 줄은 것을 확인할 수 있었다.

<br>


### BatchSize를 글로벌하게 적용하지 않은 이유

`@BatchSize`를 사용하는 대신, `hibernate.default_batch_fetch_size`를 설정 파일에 적어줌으로써 BatchSize 옵션을 전역적으로 설정할 수 있다.

```yaml
# application.yml
spring:
  jpa:
    properties:
      hibernate:
        default_batch_fetch_size: 100 # 사이즈 조정. 보통 100~1000 사용
```

각 엔티티에 대해서 별개로 size 옵션을 얼마로 설정할 지 매번 계산하는 것보다 DB부하가 심하지 않은 선에서 전역적으로 설정하는 것이 관리 비용이 줄어듦으로 전역 설정을 많이 사용하는 듯하다. 그러나 나는 다음과 같은 이유로 전역 설정 대신 `@BatchSize`를 사용했다.

1. 현재 팀에서는 `.yml`을 Git으로 관리하지 않고 있으므로, 변경 이력을 나타낼 수 없다는 점
2. 컬렉션 엔티티와 페이징을 같이 사용하는 경우가 한 군데 밖에 없다는 점

팀에서는 `.yml` 변경사항을 Git으로 관리하지 않으므로 쿼리 실행 결과의 변경 내용을 알아차리기 힘들고, 페이징이 필요한 곳 역시 현재는 한 군데밖에 없으므로 우선적으로 @BatchSize를 사용하기로 한 것이다.

<br>

### IN 쿼리를 이용했는데 쿼리가 2번 이상이 나간 경우

Batch Size를 100으로 설정하고 나서, Place의 조회 시 Place가 100개 이하로 조회됨에도 불구하고 추가적인 IN 쿼리가 실행되는 것을 확인했다.

```sql
# N개의 Place를 조회하는 SELECT 쿼리 1번. Place 14개가 조회된다.
select
    place0_.place_id as place_id1_4_,
    place0_.address as address2_4_,
    place0_.category as category3_4_,
    place0_.name as name4_4_,
    place0_.share_url as share_ur5_4_,
    place0_.station_name as station_6_4_ 
from
    place place0_ 
where
    place0_.station_name=? 
order by
    place0_.place_id asc limit ?

# 첫 번째 IN 쿼리 
select
    images0_.place_id as place_id4_5_1_,
    images0_.place_image_id as place_im1_5_1_,
    images0_.place_image_id as place_im1_5_0_,
    images0_.url as url2_5_0_,
    images0_.place_id as place_id4_5_0_,
    images0_.source as source3_5_0_
from
    place_image images0_
where
    images0_.place_id in ( # id 12개 조회
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? 
    )

# 두 번째 IN 쿼리 
select
    images0_.place_id as place_id4_5_1_,
    images0_.place_image_id as place_im1_5_1_,
    images0_.place_image_id as place_im1_5_0_,
    images0_.url as url2_5_0_,
    images0_.place_id as place_id4_5_0_,
    images0_.source as source3_5_0_
from
    place_image images0_
where
    images0_.place_id in ( # id 개 조회
        ?, ? 
    )
```

첫 번째 SELECT 문에서 조회되는 Place는 14개이고 Batch Size는 100이므로 IN쿼리는 단 한 번만 실행되어야 한다. 그러나 실제로는 IN 쿼리가 2번 실행되는데, 이것은 **한 번에 너무 많은 데이터가 조회되지 않도록 하이버네이트가 최적화를 해두었기 때문**이다. (IN 쿼리로 조회된 컬렉션 엔티티 들이 반드시 모두 사용된다는 보장이 없으므로 최적화를 해두었다.)

BatchSize를 100으로 잡았으므로, 컬렉션 엔티티 조회 개수의 기준은

```java
100
50 = 100 / 2
25 = 100 / 4
12 = 100 / 8
1~10
```

처럼 설정되며, Place가 14개 조회되었으므로 컬렉션 엔티티 12개를 조회하는 IN 쿼리와 컬렉션 엔티티 2개를 조회하는 IN 쿼리가 실행되었던 것이다. (관련하여 더욱 자세한 내용은 [여기](https://www.inflearn.com/questions/34469/default-batch-fetch-size-%EA%B4%80%EB%A0%A8%EC%A7%88%EB%AC%B8)서 확인할 수 있다.)

하이버네이트의 최적화 옵션은 `batch_fetch_style`을 통해 설정할 수 있으며, 값을 `dynamic`으로 지정하면 최적화 옵션을 끌 수도 있다.

```yaml
spring:
  jpa:
    properties:
      hibernate:
        batch_fetch_style: dynamic
```

나의 경우 역시 조회한 컬렉션 엔티티를 모두 사용하는 경우는 없으므로, 하이버네이트의 기본 설정을 사용하였다.

<br>

# 마치며

본론을 요약하자면,

- 컬렉션을 페치 조인하면 일대다 관계에서 `다`에 row수가 맞춰지므로 페이징이 불가능하다.
- Batch Size 옵션을 이용하여 프록시 초기화 발생 시 SQL의 IN절을 실행함으로써
  - N+1 문제를 해결하고,
  - 컬렉션 페치 조인과 페이징을 함께 사용 시 발생하는 메모리 페이징 문제를 해결한다.

정도로 요약할 수 있겠다.

**_앞으로 컬렉션 엔티티와 페이징을 사용할 일이 생긴다면 하이버네이트의 `BatchSize 옵션`을 고려해보면 어떨까?_**

끝.

### 추가적으로 공부할 것

- 성능 테스트
- JPA 엔티티와 프록시

### ※ Reference

- [실전! 스프링 부트와 JPA 활용2 - API 개발과 성능 최적화](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-API%EA%B0%9C%EB%B0%9C-%EC%84%B1%EB%8A%A5%EC%B5%9C%EC%A0%81%ED%99%94)
  - 페이징과 한계 돌파
- 자바 ORM 표준 JPA 프로그래밍
- [**JPA Fetch 조인(join)과 페이징(paging) 처리**](https://junhyunny.github.io/spring-boot/jpa/jpa-fetch-join-paging-problem/)
- [JPA Pagination, 그리고 N + 1 문제 - Tecoble](https://tecoble.techcourse.co.kr/post/2021-07-26-jpa-pageable/)
- [fetch join 과 pagination 을 같이 쓸 때 HHH000104: firstResult/maxResults specified with collection fetch; applying in memory](https://javabom.tistory.com/104)
- [(인프런 질답) - fetch join 시 distinct 와 페이징](https://www.inflearn.com/questions/14663/fetch-join-%EC%8B%9C-paging-%EB%AC%B8%EC%A0%9C)
- [(인프런 질답) Batch Size 원리](https://www.inflearn.com/questions/399849/batchsize-%EC%9B%90%EB%A6%AC%EC%97%90-%EB%8C%80%ED%95%B4%EC%84%9C-%EC%95%8C%EA%B3%A0-%EC%8B%B6%EC%8A%B5%EB%8B%88%EB%8B%A4)
- [(인프런 질답) - 스프링 데이터 JPA에서 fetch join이 들어간 경우 Count 쿼리를 정상적으로 만들어내지 못한다.](https://www.inflearn.com/questions/167730/%ED%8E%98%EC%9D%B4%EC%A7%95-%EC%A7%88%EB%AC%B8)
- https://joont92.github.io/jpa/JPA-%EC%84%B1%EB%8A%A5-%EC%B5%9C%EC%A0%81%ED%99%94/]()