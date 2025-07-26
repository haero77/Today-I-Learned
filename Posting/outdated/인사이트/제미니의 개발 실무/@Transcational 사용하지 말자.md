> 출처: https://youtu.be/mB3g3l-EQp0?si=g_yf4T_Lj1iavJHQ

- 선 요약
  - **_비즈니스 레이어에서 불필요한 트랜잭션 걸어서 리소스 낭비하지말고, 구현 레이어에서 트랜잭션이 필요한 메서드만 트랜잭션 걸어서 사용하자._**

<!-- TOC -->
* [비즈니스 레이어의 무지성 @Transactional](#비즈니스-레이어의-무지성-transactional)
  * [생각없이 사용할 경우에, 리소스 부족을 겪었다.](#생각없이-사용할-경우에-리소스-부족을-겪었다)
  * [그래서 나는 비즈니스 레이어에서 무지성 트랜잭션 안 걸었으면 한다](#그래서-나는-비즈니스-레이어에서-무지성-트랜잭션-안-걸었으면-한다)
    * [각 구현 레이어에서 트랜잭션 거는게 낫다](#각-구현-레이어에서-트랜잭션-거는게-낫다)
  * [트랜잭션으로 꼭 묶여야 한다면?](#트랜잭션으로-꼭-묶여야-한다면)
    * [참고](#참고)
<!-- TOC -->

# 비즈니스 레이어의 무지성 @Transactional

- 서비스 레이어 로직에서, 무조건 `@Transactional`을 거는 경우가 있더라.
  - 큰 고민 없이 걸고, 메서드 마다 무지성 `@Transactional`거는 레거시가 있다.

```java
@Service 
public class TestService {
	
    private final TestTxProcessor testTxProcessor;
    
    public TestService(TestTxProcessor testTxProcessor) {
        this.testTxProcessor = testTxProcessor;
    }

    @Transactional // 비즈니스 레이어의 무지성 @Transactional 
    public void append(String date) {
        testTxProcessor.appendNoTran(data + "_1");
    
        smsSender.send()
    
        testTxProcessor.appendTranWithException(data + "_2");
    
        slackNotifier.notify(~~)
    }
	
}
```

<br>

## 생각없이 사용할 경우에, 리소스 부족을 겪었다.

- 고민 없이 달려있던 코드로 인해, 리소스 부족 등을 경험했다.
  - 생각보다 많이 나오고, 짜증 UP.
- _무조건적으로 달지 않았으면 함._


```java
@Transactional
public void append(String date) {
    testTxProcessor.appendNoTran(data + "_1");
    testTxProcessor.appendTranWithException(data + "_2");
    
    smsSender.send()
    slackNotifier.notify(~~)
}
```

- smsSender 컴포넌트에 타임아웃 30초 걸어놓은 경우,
  - read timeout에서 30초 동안 기다리고 있고.. ㅠㅠ...
- 이 `append()` API가 엄청 많이 호출된다고 하면, 
  - **문자를 보내려고 자원을 엄청 점유하고 있고(트랜잭션 때문에)** .. 
    - 당연히 리소스 부족으로 이어진다.

<br>

## 그래서 나는 비즈니스 레이어에서 무지성 트랜잭션 안 걸었으면 한다

- 여기서 재민님이 말씀하신 비즈니스 레이어는 구현 레이어 사용해서 비즈니스 로직 전개하는 레이어

```java
public void append(String date) {
    testTxProcessor.appendNoTran(data + "_1");
    testTxProcessor.appendTranWithException(data + "_2");
    
    smsSender.send()
    slackNotifier.notify(~~)
}
```

- 비즈니스 레이어에서 `@Transactional`을 제거 했으니, 
  - 👉 smsSender, slackNotifier에 트랜잭션이 안 걸릴 것이다.
    - 즉, 리소스적으로 낭비가 없어진다.

### 각 구현 레이어에서 트랜잭션 거는게 낫다


```java
@Component
public class TestTxProcessor {
    
    private final TestAppender testAppender;
    
    public TestTxProcessor(TestAppender testAppender) {
        this.testAppender = testAppender;
    }
    
    public void appendNoTran(String data) {
        testAppender.appendNormal(data + "_1");
        testAppender.appendNormal(data + "_2");
    }
    
    @Transactional
    public void appendTranWithException(String data) {
        testAppender.appendNormal(data + "_1");
        testAppender.appendException(data + "_2");
    }
	
}
```

## 트랜잭션으로 꼭 묶여야 한다면?

```java
public void append(String date) {
    testTxProcessor.appendNoTran(data + "_1"); // (1)
    testTxProcessor.appendTranWithException(data + "_2"); // (2)
    
    smsSender.send()
    slackNotifier.notify(~~)
}
```

- (1), (2) 가 꼭 묶여야한다면?
  - 비즈니스 레이어에서 `@Transactional` 달 것인가?
- 만약에 트랜잭션이 묶여야 하면, 비즈니스 레이어가 아닌 구현 레벨에서 같은 구현으로 가있는 경우가 십중팔구.
- (1), (2) 자체가 하나의 구현 컴포넌트로 묶여야할 가능성이 높음.
  - 트랜잭션으로 같이 묶여야 한다는 것 자체가, 결합도가 높다는 것이므로.

```java
testTxProcessor.appendNoTran(data + "_1"); // (1)
testTxProcessor.appendTranWithException(data + "_2"); // (2)
```

- 위 둘을 묶은 하나의 새로운 구현의 개념, 재사용성이 높은 구현 객체가 나올 수 있음.



### 참고

- [토비] 테스트에서의 @Transactional 사용에 대해 질문이 있습니다 - Toby vs ChatGPT (2)
  - https://youtu.be/-961J2c1YsM?si=9beq6KS6YNUJDo-G