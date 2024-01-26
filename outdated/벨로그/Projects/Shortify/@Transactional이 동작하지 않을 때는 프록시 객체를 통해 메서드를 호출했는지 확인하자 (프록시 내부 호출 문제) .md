> # @Transactional이 동작하지 않을 때는 프록시 객체를 통해 메서드를 호출했는지 확인하자 (프록시 내부 호출 문제)

<!-- TOC -->
* [들어가며](#)
* [@Transactional이 의도한대로 동작하지 않는 문제](#transactional----)
  * [상황](#)
  * [문제](#)
  * [원인](#)
    * [메서드가 트랜잭션 범위 안에 속하는지 확인하는 방법](#------)
  * [해결](#)
    * [클래스를 분리함으로써 프록시 내부 호출 피하기](#-----)
* [마치며](#)
    * [추가적으로 공부할 것](#--)
    * [※ Reference](#-reference)
<!-- TOC -->

<br>

![image](https://github.com/haero77/Today-I-Learned/assets/65555299/4ac944f0-372b-4bea-b854-33cd79179eff)

_(프록시 객체를 통해 @Transactional을 사용하자.)_

<br>

# 들어가며

단축 URL 프로젝트 [Shortify](https://github.com/haero77/Shortify)를 개발하던 중, `@Transactional`이 의도대로 실행되지 않는 문제가 발생했다. `변경 감지`를 이용해서 UPDATE 쿼리가 실행될 것을 예상했으나, 실제로는 쿼리가 실행되지 않아 테스트를 통과하지 못하는 경우가 발생하였다. 본 포스팅에서는 문제를 인지한 경로부터, 문제의 원인과 해결 방법을 담는다. 

<br>

# @Transactional이 의도한대로 동작하지 않는 문제

## 상황

필자는 아래와 같은 작업을 하고 싶은 상황이다. 

>   한 트랜잭션 안에서,
>   1.  엔티티를 객체를 생성하고 영속화한다.
>   2. 영속화된 객체에 필드를 할당함으로써 변경 감지가 일어나게 한다.

위 작업을 한 메서드 안에서 하는데, 해당 작업을 하는 메서드를 외부에서 호출함으로써 작업을 완수하고 싶다.

```java
@Slf4j
@Service
public class ShortUrlCreator {

	// ...

	// ShortUrlCreator 외부에서 실행하는 메서드
	public Long create1(ShortUrlCreateRequest request) {
		if (request.expirationExists()) {
			return create2(request.url(), ShortUrl.DEFAULT_EXPIRATION_PERIOD);
		}
		return create2(request.url(), ShortUrl.MAX_EXPIRATION_PERIOD);
	}

	@Transactional
	public Long create2(String originUrl, Period expirationPeriod) {
		// 엔티티 생성 
		ShortUrl shortUrl = ShortUrl.createWithoutShortenedUrl(originUrl, expirationPeriod);

		// 영속화 
		shortUrlRepository.save(shortUrl);

		// 엔티티 필드에 새로운 값 할당. 변경 감지로 UPDATE 쿼리 실행할 것으로 기대
		shortUrl.assignShortenedUrl(Base62Encoder.encode(shortUrl.id()));

		return shortUrl.id();
	}
}
```

<img width="865" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/75a428e1-2ab0-40e0-afd4-35c575ed6900">


내가 의도했던 흐름은 다음과 같았다.

1. `ShortUrlCreator` 객체가 아닌 다른 클라이언트 객체가 `create1()` 을 실행
2. `ShortUrlCreator`가 자기 자신의 `create2()` 호출
   - 트랜잭션 시작
   - 엔티티 생성
   - 엔티티에 필드 할당
   - 트랜잭션 커밋

`create2()` 메서드에 `@Transactional`을 적용했으니 당연히 트랜잭션이 적용될 것이라 생각했지만, 예상과는 달리 트랜잭션이 수행되지 않았다. 

<br>

## 문제 


```java
@Test
@DisplayName("ShortUrl이 생성된 뒤 단축된 URL이 존재한다.")
void createShortUrl() {
    // given
    ShortUrlCreateRequest createRequest =
        new ShortUrlCreateRequest("https://github.com/haero77", false);

    // when
    Long savedId = shortUrlCreator.create1(createRequest);

    // then
    ShortUrl findShortUrl = shortUrlRepository.findById(savedId).get();

    String actual = findShortUrl.shortenedUrl();

    assertThat(actual).isNotNull(); // 테스트 실패. actual이 null이다. 
}
```

예상대로라면 트랜잭션이 정상적으로 시작되고, 커밋 될 때 엔티티 매니저 내부에서 플러시가 호출되어 변경감지가 일어나야 하는데 그렇지 않았다. 

<br>

<img width="1175" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/bf705534-64c8-4d73-85bc-3df493a76d0e">


<img width="1140" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/694da62b-9b12-4afb-8da3-e55e3cf113c9">

실제로 UPDATE 쿼리가 발생하지 않은 것으로 보아, 변경 감지가 이루어지지 않음을 알 수 있다. 즉, `@Transactional`을 메서드에 적용해도, 의도와는 다르게 트랜잭션이 수행되지 않은 것이다.


<br>

## 원인

결론부터 말하면, 트랜잭션이 수행되지 않은 것은 스프링의 트랜잭션 AOP가 적용되지 않았기 때문이었다. 

`@Transactional`을 사용하면, `@Transactional`을 사용하는 객체를 상속하는 프록시 객체가 스프링 빈으로 등록되며, 의존관계 주입 시 스프링 컨테이너에 의해 프록시 객체가 주입된다.

<br>

<img width="1053" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/603f5946-f9a8-4ff0-a32f-11be7ec57546">

문제의 테스트를 디버깅 해보면, 실제 객체가 아닌  `ShortUrlCreator$$CGLIB`처럼 프록시 객체가  주입된 것을 확인할 수 있다. 프록시 객체를 통해서만 트랜잭션 프록시를 적용할 수 있는데, 프록시 객체의 `create1()`을 호출할 때 `create1()`은 `@Transactional`이 없으므로 트랜잭션 프록시가 적용되지 않은 것이다. 

<br>

<img width="1210" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/59f07e2f-034c-4488-b22c-3e11565bcdf9">

프록시 객체가 호출되는 과정을 빈 주입단계 부터 자세히 살펴보자.

- 가장 먼저, shortUrlCreator 객체를 주입할 때 실제 객체가 아닌 프록시 객체 `ShortUrlCreator$$CGLIB`가 주입된다.
  - `@Transactional`을 사용하는 메서드가 하나라도 존재하면 트랜잭션 AOP가 적용되는데, 트랜잭션 AOP는 기본적으로 프록시 AOP로 동작하기 때문에 프록시 객체가 주입된다.
- 클라이언트 객체를 통해 프록시 객체의 `create1()`을 호출한다.
- 트랜잭션 프록시가 호출되지만, `create1()`은 `@Transactional`이 없으므로 트랜잭션이 적용되지 않는다.
- 프록시 객체는 실제 객체의 `create1()`을 호출한다. 
- 실제 객체는 `this.create2()`를 호출한다. 
  - Java에서 메서드 앞에 별도 참조가 없을 때는 this를 사용하여 자기 자신의 인스턴스를 가리키기 때문에, `create1()` 내부에서 `create2()`를 호출할 때 `this.create()`로 실행된다.

결국 엔티티를 영속화하는 `shortUrlRepository.save(shortUrl)` 때만 트랜잭션이 수행되므로, 트랜잭션 범위 밖에서 필드를 할당하는 `shortUrl.assignShortenedUrl(Base62Encoder.encode(shortUrl.id()))`의 경우 변경 감지가 제대로 동작하지 않은 것이다.

<br>

### 메서드가 트랜잭션 범위 안에 속하는지 확인하는 방법

메서드가 실행되는 시점에, 트랜잭션에 속해있는지 확인하기 위해서는 

`org.springframework.transaction.support.TransactionSynchronizationManager`가 제공하는 `isActualTransactionActive()`를 사용하면 된다. 

<img width="647" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/bb32f8c0-29d6-4baa-ac62-213077d9a5c3">

`isActualTransactionActive()`는 해당 메서드를 호출하는 메서드가 트랜잭션에 속해 있는지를 boolean값으로 리턴한다. 

<br>

```java
@Slf4j
@Service
public class ShortUrlCreator {
	
    // ...
    
    public Long create1(ShortUrlCreateRequest request) {
		
        // 트랜잭션이 실행 중인지 확인
        boolean isTxActive = TransactionSynchronizationManager.isActualTransactionActive();
        log.info("create1() TxActive = " + isTxActive);
        
		// ...
    }
    
    @Transactional
    public Long create2(String originUrl, Period expirationPeriod) {
		
        // 트랜잭션이 실행 중인지 확인
        boolean isTxActive = TransactionSynchronizationManager.isActualTransactionActive();
        log.info("create2() TxActive = " + isTxActive);
    
        // ...
    }
}
```

`isActualTransactionActive()`를 이용하여, 기존의 `create1()`과 `create2()`에 각 메서드가 트랜잭션 범위에 속해있는지를 확인하는 코드를 추가했다.

<br>

<img width="1175" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/222aa7b0-2923-49f4-98d6-d1d864561706">

결과를 확인해보면 `create1()`, `create2()` 모두 트랜잭션에 속하지 않는 것을 알 수 있다.


<br>

## 해결

`create2()`가 트랜잭션 범위 안에 속하지 않아 발생한 문제이므로, `create2()`가 실행될 때 트랜잭션 범위 안에 속하도록 수정하자.

```java
@Slf4j
@Service
public class ShortUrlCreator {

    // create1() 호출 시 프록시 AOP가 호출되게 한다.
    @Transactional 
    public Long create1(ShortUrlCreateRequest request) {
		
        // ...
        
    }
    
    private Long create2(String originUrl, Period expirationPeriod) {
		
        // ...
        
    }
}
```

먼저 기존 `create2()`에 붙였던 `@Transactional`을 제거하여 `create1()`으로 옮겼다. 또한 `create2()`의 경우 현재 외부 객체에서 직접 호출되는 경우가 없으므로 접근 제어자를 `private`으로 변경하였다. 

<br>

<img width="1162" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/a750bd24-5a5e-4c44-ab07-f19a85e77eac">

위처럼 코드를 수정하고 실패했던 테스트를 다시 수행하면 정상적으로 통과하는 것을 확인할 수 있는데, 원리는 다음과 같다.

<img width="1221" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/ba998c3f-7523-48cb-90c1-b683b49a8012">

- 프록시 객체의 `create1()` 호출 
- 프록시 객체의 `create1()`은 `@Transactional`이 있으므로, 프록시 AOP가 호출된다.
- 프록시 AOP가 호출되어 트랜잭션 시작
- 실제 객체의 `create1()` 호출 
- `this.create2()` 호출
- 트랜잭션 커밋
- 변경 감지 동작 👉 UPDATE 쿼리 실행

이전의 경우 `create2()`가 트랜잭션에 속하지 않아 변경 감지가 일어나지 않았다. 그러나 현재의 경우 `create2()` 실행 전에 트랜잭션을 실행함으로써, 트랜잭션 범위에 속하게끔 변경했기 때문에 정상적으로 변경 감지가 일어난 것이다. 

문제는 해결했지만 위 방식의 경우 `create1()`, `create2()` 모두 트랜잭션에 속하게 된다. 가능한 트랜잭션의 범위를 좁히기 위해, `create2()`만 트랜잭션에 속하게 하려면 어떻게 해야할까?

<br>

### 클래스를 분리함으로써 프록시 내부 호출 피하기

`create2()`를 실제 객체에 의해 `내부 호출`이 되게 하지 않고, 프록시 객체에 의해 실행되도록 함으로써 트랜잭션 범위를 좁힐 수 있다. 

```java
@Slf4j
@Service
public class ShortUrlCreator1 {

    private final ShortUrlCreator2 shortUrlCreator2;
    
    // shortUrlCreator2를 상속하는 프록시 객체 'shortUrlCreator2$$CGLIB' 이 주입된다. 
    public ShortUrlCreator1(ShortUrlCreator2 shortUrlCreator2) {
        this.shortUrlCreator2 = shortUrlCreator2;
    }
    
    public Long create1(ShortUrlCreateRequest request) {
        if (request.expirationExists()) {
            return shortUrlCreator2.create2(request.url(), ShortUrl.DEFAULT_EXPIRATION_PERIOD); // 프록시 객체의 create2() 호출
        }
        return shortUrlCreator2.create2(request.url(), ShortUrl.MAX_EXPIRATION_PERIOD); // 프록시 객체의 create2() 호출
    }
}

@Service
public class ShortUrlCreator2 {
    
    @Transactional
    public Long create2(String originUrl, Period expirationPeriod) {
		
        // ...  
    }
}
```

기존의 `ShortUrlCreator` 클래스를 `ShortUrlCreator1`과 `ShortUrlCreator2`로 분리하고, 각각 `create1()`과 `create2()`를 갖게 했다. 

<br>

<img width="1053" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/67fe2598-df89-4a66-9180-b07481513a72">

실행과정을 빈 주입 단계부터 살펴보자. 

- `shortUrlCreator1`은 `@Transactional`을 사용하지 않았으므로, 프록시 객체가 빈으로 등록되는 것이 아니라 실제 객체가 빈으로 등록된다. 반면, `shortUrlCreator2`의 경우 메서드에 `@Transactional`을 사용하는 경우가 있으므로, `shortUrlCreator2`의 프록시 객체가 빈으로 등록된다. 
- 클라이언트 객체가 `shortUrlCreator1.create1()`을 호출한다.
- `shortUrlCreator1`가 참조하는 `shortUrlCreator2`는 실제로는 프록시 객체이므로, 프록시 객체의 `create2()`를 호출한다. 
- `create2()`는 `@Transactional`이 있으므로, 프록시 AOP가 적용된다. 따라서 트랜잭션이 시작된다.
- 실제 객체의 `create()`가 수행된다. 트랜잭션 범위에 속하므로 정상적으로 변경 감지가 일어난다. 

<br>

<img width="868" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/df36506f-527f-490d-a710-1d11211bc35c">

디버깅을 해보면, 실제로 `ShortUrlCreator`의 필드 `shortUrlCreator2`에는 프록시 객체 `shortUrlCreator2$$CGLIB`이 주입되는 것을 확인할 수 있다. 프록시 객체를 통해서 메서드를 호출하므로, 프록시 AOP가 동작하여 정상적으로 트랜잭션이 수행된다.

결국 `@Transactional`을 사용할 때 프록시 AOP가 동작하는가 / 안 하는 가의 기준은 `@Transactional`이 붙어있는 메서드를 호출할 때 프록시 객체를 통해서 호출하는가 / 실제 객체를 통해 호출하는가에 따라 달렸다. **_프록시 AOP가 동작하려면, 반드시 프록시 객체를 통해서만 메서드가 호출되어야한다._**

<br>

본문을 마치며, 문제를 해결할 수 있게 도와주신 영한님의 답변을 첨부한다.

<img width="684" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/9f83818a-9d0e-4026-bb2c-a58717e1d941">

[_(영한님 답변 내용 캡쳐)_](https://www.inflearn.com/questions/858567/transactional-%EC%97%90-%EB%94%B0%EB%A5%B8-%EB%B3%80%EA%B2%BD%EA%B0%90%EC%A7%80-%EC%9E%91%EB%8F%99%EC%97%AC%EB%B6%80-%EC%A7%88%EB%AC%B8)

<br>

# 마치며

이번 포스팅에서는 `@Transactional`이 의도대로 동작하지 않을 때 원인과 해결방법을 알아봤다.공부하며 `@Transactional`과 `프록시`, `AOP` 개념에 대해 부족함을 느꼈기에 이에 대해 추가적으로 공부해보려한다. 

**_`@Transactional`이 정상적으로 동작하지 않을 때는, 프록시 객체를 통해서 메서드를 호출한 것이 아니라 실제 객체를 통해 호출했는지 의심 해보자._** 

마침.


<br>

### 추가적으로 공부할 것 

- 스프링 프록시, AOP
- `@Transactional`

<br>

### ※ Reference

- [소스 코드](https://github.com/haero77/Shortify/blob/fix%2Fproxy/src/main/java/com/haero77/urlshortener/domain/shorturl/service/ShortUrlCreator.java)
- [인프런 질답 - @Transactional 에 따른 변경감지 작동여부 질문](https://www.inflearn.com/questions/858567/transactional-%EC%97%90-%EB%94%B0%EB%A5%B8-%EB%B3%80%EA%B2%BD%EA%B0%90%EC%A7%80-%EC%9E%91%EB%8F%99%EC%97%AC%EB%B6%80-%EC%A7%88%EB%AC%B8)
- [인프런 - 스프링 DB 2편](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-db-2)
- [[JPA] 변경감지 개념과 @Transactional 위치에 따른 변경감지 사용](https://beaniejoy.tistory.com/68)

<br>

> 잘못된 내용이 있다면 언제든지 댓글로 남겨주시면 감사하겠습니다! 🙇‍♂️ 