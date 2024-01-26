> # @Transactional과 synchronized를 같이 사용할 때의 문제점 

![@Transactional과_synchronized를_같이_사용할_때의_문제점](https://github.com/haero77/Today-I-Learned/assets/65555299/21a4401c-4812-4b2d-990c-349c6d503601)

> synchronized 는 메서드 시그니처가 아니라서 상속되지 않는다.

<br>

> 🎯GOAL 
> - `@Transactional`과 `synchronized`를 같이 사용할 때 생기는 문제와 그 원인을 이해한다.
> - `synchronized`는 메서드 시그니처가 아니라서 상속되지 않음을 이해한다.
> - `synchronized`가 한 프로세스 내에서만 동시성을 제어할 수 있음을 이해한다.
> - `synchronized`를 사용하면 무조건 갱신 손실 문제를 피할 수 있는지 고민해본다.

# 들어가며

[동시성 이슈 관련 강의](https://www.inflearn.com/course/%EB%8F%99%EC%8B%9C%EC%84%B1%EC%9D%B4%EC%8A%88-%EC%9E%AC%EA%B3%A0%EC%8B%9C%EC%8A%A4%ED%85%9C)를 듣다가, **`synchonized`를 사용했음에도 불구하고 `갱신 손실 문제`를 해결하지 못하는 케이스**를 발견하게 되었다. 이번 포스팅을 통해 그 원인을 정리하고, `synchronized`의 문제점은 어떤 것들이 있는지 살펴보고자 한다.

<br>

# 본문

문제가 발생한 로직에 대해 먼저 살펴보고, 정확히 어떤 문제가 발생하는지 확인한다. 그 후 문제의 해결법을 알아보고 해당 해결법은 만능인지에 대해 고민해보자.

<br>

## 재고감소 로직

다음과 같이 `재고` 도메인이 있다.

```java
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long quantity;
    
    public void decrease(Long quantity) {
        if (this.quantity - quantity < 0) {
            throw new IllegalArgumentException(
                    "Cannot decrease quantity. Current quantity is less than request to decrease."
            );
        }
      
        this.quantity -= quantity;
    }
}
```

`id` 와 `quantity`를 필드로 가지며, 인스턴스 메서드 `stock.decrease()`를 통해 `quantity` 값을 감소시킨다. 

<br>

```java
// Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}

// Service
@RequiredArgsConstructor
@Service
public class StockService {

    private final StockRepository stockRepository;

    @Transactional
    public void decrease(Long id, Long quantity) {
        Stock findStock = stockRepository.findById(id).orElseThrow();
        
        findStock.decrease(quantity);
        
        stockRepository.saveAndFlush(findStock); // 명시적으로 save를 나타내려고 사용.
    }
}
```

Repository는 JpaRepository를 상속하며, `StockService`의 `decrease()`를 이용하여 재고감소 로직을 수행한다. `decrease()`에서 `영속성 컨텍스트`의 `변경 감지`를 이용해서 quantity를 감소시킬 수도 있겠지만, 예제에서는 명시적으로 값이 변경됨을 나타내기 위해 `JpaRepository.saveAndFlush()`를 사용하였다.

<br>

### 갱신 손실 문제

위 비즈니스 로직에서는 별도의 동시성 제어 처리를 해주지 않았으므로, 동시성과 관련된 문제가 발생할 수 있다. 어떤 문제가 발생하는지 직접 스레드를 여러 개 만들어 테스트해보자.

```java
@Test
@DisplayName("동시에 100개의 재고 감소 요청")
void decrease_concurrently() throws InterruptedException {
    // given
    Stock stock = new Stock(1L, 100L);
    stockRepository.save(stock);
    
    int threadCount = 100;
    ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
    CountDownLatch countDownLatch = new CountDownLatch(threadCount);
    
    // when
    for (int i = 0; i < threadCount; i++) {
        executorService.submit(() -> {
            try {
                stockService.decrease(1L, 1L);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });
    }
    
    countDownLatch.await(); // 모든 스레드의 작업이 끝날 때까지 대기
    
    // then
    Stock findStock = stockRepository.findById(1L).orElseThrow();
    assertThat(findStock.getQuantity()).isEqualTo(0L);
}
```

- 아이디가 `1L`인 엔티티 객체를 생성하여 DB에 저장한다.
- 100개의 스레드를 만들어 각 스레드가 `stockService.decrease()`를 호출한다. 각 스레드는 아이디가 `1L`인 `stock`의 `quantity`를 1개씩 감소시킨다. 
- 모든 스레드가 끝날 때까지 대기하고, 남은 `quantity`가 `0`인지 검증한다. 

위 테스트 결과는 아래와 같다. 

<img width="1658" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/0d2dc427-3e54-41b4-b8fd-2a79bebacc16">

각 스레드는 정상적으로 `quantity`를 감소시켰으나, `갱신 손실 문제`가 발생하여 테스트에 실패한다. 왜 갱신 손실 문제가 일어나는지 그림으로 이해해보자.

<br>

![image](https://github.com/haero77/Today-I-Learned/assets/65555299/eb9c256b-ace8-4bda-a0eb-12c4550c09e4)

- 스레드 1이 데이터를 읽는다. (quantity=100)
- 스레드 2가 데이터를 읽는다. (quantity=100)
- 스레드 1이 데이터를 갱신한다. (quantity=99)
- 스레드 2가 데이터를 갱신한다. (quantity=99)
  - 스레드 2가 데이터를 갱신할 때, `스레드 1`이 갱신했던 내역(quantity가 100에서 99로 감소)이 사라진다. 
   
👉 스레드 1의 '갱신'이 '손실' 되었으므로 `갱신 손실 문제` 발생 

그렇다면 이런 갱신 손실 문제를 막기 위해서는 어떻게 해야할까? 각 스레드가 공유 데이터를 읽어서 생긴 문제이므로, **공유 데이터에 두 개 이상의 스레드가 동시에 접근하지 못하도록 는 `synchronized`를 쓰면 되지 않을까?** 

<br>

## `@Transactional`과 `synchronized`를 같이 사용할 때의 문제점

```java
// StockService::decrease
@Transactional
public synchronized void decrease(Long id, Long quantity) {
    Stock findStock = stockRepository.findById(id).orElseThrow();
    
    findStock.decrease(quantity);
    
    stockRepository.saveAndFlush(findStock);
}
```

재고 감소 로직에 `synchronized` 키워드를 붙였다. 이제 `stock.quantity`는 하나의 스레드만 점유할 수 있으므로, 갱신 손실 문제를 해결할 수 있을 것이다.

<br>

<img width="1381" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/9519c4e4-0430-4f63-99b2-043925a3e4ff">

이게 웬걸, `synchronized`를 사용했음에도 불구하고 문제를 해결하지 못했다. 물론 `synchronized`를 사용하기 전보다 `quantity`가 의도했던 개수인 `0`에 더 가까워졌지만, 여전히 한참 못 미치는 수준이다. 분명 `synchronized`를 사용하면 하나의 스레드만 공유 자원을 점유할텐데 도대체 왜 이런 결과가 나오는 걸까?

<br>

## synchronized를 사용했음에도 갱신 손실 문제를 해결하지 못한 이유 

synchronized를 사용했음에도 갱신 손실 문제를 해결하지 못한 이유는 `Spring AOP` 때문이다. `@Transactional`을 사용하면 Spring AOP로 인해 프록시 객체가 만들어지고, 원래 객체인 `stockService`의 `decrease()`의 실행이 끝나고 트랜잭션이 커밋되기 전에 다른 스레드가 데이터를 읽었기 때문에 갱신 손실 문제를 해결할 수 없었던 것이다. 

프록시 객체가 어떻게 생겼는지 예상해보면서 더 자세히 이해해보자.

```java
// Proxy class
class StockServiceProxy extends StockService{

    private StockService stockService;

    @Override
    public void decrease(Long id, Long quantity) {
         try{
             tx.start();
             stockService.decrease();
         } catch (Exception e) {
             // ...
         } finally {
             tx.commit();
         }
    }
}

// Origin Class
@Service
class StockService {
	
    public synchronized void decrease(Long id, Long quantity) {
        // ...
    }
}
```

프록시 객체는 원래 객체인 `StockService`를 상속해서 만들어진다. 

그러나, **`synchronized`는 `메서드 시그니처(=메서드 이름 + 파라미터 타입과 개수)`가 아니기 때문에, 상속되지 않는다.** 따라서 프록시 객체의 `decrease()`는 여러 스레드가 사용할 수 있게 된다. 이 상황을 그림으로 이해해보자.

<img width="1200" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/74d33c24-ed97-4ca2-892f-63e643916dfa">

- 스레드 1의 트랜잭션이 시작하고, 원래 객체인 `stockService`의 `decrease()`를 호출한다.
- 스레드 1의 `stockService.decrease()`이 실행 중인 동안, 다른 스레드는 해당 메서드에 접근할 수 없다. 
- 스레드 1의 `stockService.decrease()`이 종료되고 `tx.commit()`을 호출하기 전, `stockService.decrease()`를 사용하기 위해 기다리고 있던 스레드 2가 해당 메서드를 실행한다. 
- 스레드 1의 트랜잭션이 커밋된다. (quantity = 99)
- 스레드 2의 트랜잭션이 커밋되면서, 스레드 1의 갱신 내역이 사라진다. 👉 갱신 손실 문제 발생

결국 원래 객체의 `decrease()`는 동시성 제어를 받지만, 프록시 객체의 `decrease()`는 동시성 제어를 받지 않으니 갱신 손실 문제가 해결되지 않은 것이다. 그럼 `synchronized`는 갱신 손실 문제를 '절대로' 해결할 수 없는 것일까?

<br>

## 해결 방법

### @Transactional을 쓰지 않는 방법

프록시 객체가 문제의 원인이었으니, `@Transactional`을 사용하지 않도록 하면 될 것이다. 

```java
// @Transactional // 사용하지 않기 위한 주석 처리
public synchronized void decrease(Long id, Long quantity) {
    Stock findStock = stockRepository.findById(id).orElseThrow();
    
    findStock.decrease(quantity);
    
    stockRepository.saveAndFlush(findStock);
}
```

<img width="1308" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/7f2d76e1-6cc8-4999-8390-17b4e24dc76e">

비로소 갱신 손실 문제를 해결했다. 그러나 이 방법은 문제가 많다. 일단, 성능이 너무 안 좋다.  그뿐만 아니라, 사실 **`synchronized`는 운영 환경에서는 근본적인 해결책이 될 수 없다.** 

아니 분명 테스트는 통과했고 문제도 해결했는데 근본적인 해결책이 될 수 없다니, 이상하지 않은가? 필자 역시 이에 의문을 가졌고, `synchronized`의 특성을 이해함으로써 납득할 수 있었다.  

<br>

### `synchronized`는 근본적인 해결책이 될 수 없다

**`synchronized`는 한 프로세스 내에서만 동시성 제어를 할 수 있다.** `synchronized`를 메서드에 사용한다면, 한 프로세스 내에서 한 번에 하나의 스레드만 해당 메서드에 접근하는 것을 보장할 수 있다. 

그런데 만약 두 개 이상의 프로세스를 사용한다면 어떻게 될까? 실제 운영환경에서는 서버를 한 대만 두고 쓰지는 않을텐데, 과연 이 상황에서도 `synchronized`가 `갱신 손실 문제`를 해결할 수 있을까? 

<br>

<img width="867" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/512e457e-05cc-41ad-807c-f5e1e9f6afb2">

_[(이미지 출처)](https://www.inflearn.com/course/lecture?courseSlug=%EB%8F%99%EC%8B%9C%EC%84%B1%EC%9D%B4%EC%8A%88-%EC%9E%AC%EA%B3%A0%EC%8B%9C%EC%8A%A4%ED%85%9C&unitId=114844&category=questionDetail&tab=curriculum)_

결론부터 말하면 해결할 수 없다. 위 그림에서 보듯, Server 1의 `synchronized`가 Server 2의 스레드에 대한 동시성을 제어할 수는 없다. 실무에서 서버는 보통 2대 이상 운영하기 때문에, 이러한 이유로 `synchronized`를 잘 사용하지 않는다고 한다. 

결국 애플리케이션 레벨에서의 동시성 제어로는 서버가 2대 이상일 때 `갱신 손실 문제`를 해결하기 어렵다. 데이터베이스 레벨에서의 동시성 제어가 필요해지는 순간이다.

<br>

### 대안을 찾아서

문제를 해결을 위해 DBMS가 제공하는 기능들을 살펴보자. 먼저, `트랜잭션 격리 수준`의 경우 한 트랜잭션은 읽고, 다른 트랜잭션이 쓸 때 생기는 문제인 `DIRTY READ`, `NON-REPEATABLE READ`, `PHANTOM READ`를 해결하기 위한 방법이다. ([트랜잭션 격리 레벨 관련 포스팅](https://velog.io/@balparang/트랜잭션-격리-수준이란-무엇이고-왜-필요할까)) 지금 발생하는 `갱신 손실 문제`는 두 개의 트랜잭션이 모두 쓰는 작업을 할 때 발생하는 문제이므로, 트랜잭션 격리 수준을 사용하는 것은 적합하지 않다.

결국, 데이터에 직접 락킹을 할 필요가 있다. 관련한 방법으로는 `Pessimistic Lock`, `Optimistic Lock` 등이 있으며, 이에 대해서는 다음 포스팅에서 다루도록 하겠다.
 

<br>

# 마치며

본문을 요약하자면, 

-  `@Transactional`과 `synchronized`를 같이 사용할 때, 프록시 객체가 생성되고 synchonized는 이 때 상속 되지 않으므로 프록시 객체의 메서드는 여러 스레드가 접근할 수 있다. 👉 갱신 손실 문제 발생
- `synchronized`는 한 프로세스 내에서만 스레드의 접근 제한을 보장한다. 따라서 2개 이상의 서버(프로세스)에서는 `갱신 손실 문제`를 해결할 수 없다.

가 되겠다. 

결국 `synchronized`를 이용한 동시성 제어는 성능과 운영 환경에서의 한계로 인해 실무에서는 사용하기 어려우며, 이에 따라 데이터베이스 레벨의 동시성 제어가 필요함을 확인했다. 그에 대한 방법은 차후 포스팅 해볼 것이다. 마지막으로 문제 해결에 도움을 주신 [최상용님 개발자님](https://www.inflearn.com/questions/907953/%ED%94%84%EB%A1%9D%EC%8B%9C-%EA%B0%9D%EC%B2%B4%EA%B0%80-%EC%83%9D%EC%84%B1%EB%90%A0-%EB%95%8C-synchronized-%EC%97%86%EC%9D%B4-%EB%A9%94%EC%84%9C%EB%93%9C%EA%B0%80-%EC%83%9D%EC%84%B1%EB%90%98%EB%8A%94-%EA%B2%83%EC%9D%B4-%EB%A7%9E%EC%9D%84%EA%B9%8C%EC%9A%94)께 감사드리며, 본 포스팅은 여기서 마친다.

_마침_

 
<br>

### 추가적으로 공부해볼 것 

- `synchronized`
- `Pessimistic Lock`, `Optimistic Lock`

<br>

### Ref.


- [재고시스템으로 알아보는 동시성이슈 해결방법](https://www.inflearn.com/course/%EB%8F%99%EC%8B%9C%EC%84%B1%EC%9D%B4%EC%8A%88-%EC%9E%AC%EA%B3%A0%EC%8B%9C%EC%8A%A4%ED%85%9C)
- [Is synchronized inherited in Java? - stackoverflow](https://stackoverflow.com/questions/15998335/is-synchronized-inherited-in-java)
- [Concurrent Programming in Java - Java Concurrency Constructs (Synchronization 섹션)](https://gee.cs.oswego.edu/dl/cpj/mechanics.html)
- [프록시 객체가 생성될 때 synchronized 없이 메서드가 생성되는 것이 맞을까요? - 인프런 질답](https://www.inflearn.com/questions/907953)
- [synchronized 와 @Transactional - 인프런 질답](https://www.inflearn.com/questions/887799/synchronized-%EC%99%80-transactional)

---

> 🧑‍💻 잘못된 내용에 대해 언제든지 댓글로 남겨주시면 감사하겠습니다 :)