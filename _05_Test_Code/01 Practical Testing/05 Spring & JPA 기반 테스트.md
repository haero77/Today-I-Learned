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

---

# Business Layer 테스트 (2)

### @DataJpaTest의 @Transactional

- 서비스 레이어는 클렌징 해주지 않아 테스트가 실패한다.
- Repository 테스트는 `tearDown()`으로 클렌징 해주지 않아도 테스트가 통과함. 왜일까?
  - Repository 레이어는 `@DataJpaTest`를 사용. -> `@Transactional 붙어있음` -> 테스트가 끝나고 롤백된다.

---

# Business Layer 테스트 (3)

### 추가된 요구사항

> ✅ 주문 생성 시 재고 확인 및 개수 차감 후 생성하기 <br>
> ✅ 재고는 상품 번호를 가진다. <br>
> ✅ 재고와 관련 있는 상품 타입은 병 음료, 베이커리 이다.

👉 `BOTTLE`과 `BAKERY`만 재고 차감. `HANDMADE`는 재고 차감 X

### 이렇게 간단한 것도 테스트를 해야하나요?

```java
@Getter
@RequiredArgsConstructor
public enum ProductType {

	HANDMADE("제조 음료"),
	BOTTLE("병 음료"),
	BAKERY("베이커리");

	private final String text;

	// 간단한 것도 테스트를 한다.
	public static boolean containsStockType(ProductType type) {
		return List.of(BOTTLE, BAKERY).contains(type);
	}
}
```

당연. `BOTTLE` 과 `BAKERY`가 언제 달라질지 모른다.

- 👉 **항상 테스트로 미래시점에 대한 대비를 해두자.**

### 서비스 로직과 엔티티에서 같은 재고 감소 로직인데 예외 처리를 한 번 더 한 이유?

```java
// OrderService::createOrder - 재고 차감시도
for (String stockProductNumber : stockProductNumbers) {
      Stock stock = stockMap.get(stockProductNumber);
      int quantity = productCountingMap.get(stockProductNumber).intValue();

      // stock.deductQuantity 에서도 예외처리를 하는데 여기서도 예외처리를 한 이유?
      // 👉 서비스에서 진행한 체크는 주문 생성 로직을 수행하다가 재고에 대한 차감 시도를 하는 과정
      if (stock.isQuantityLessThan(quantity)) {
            throw new IllegalArgumentException("재고가 부족한 상품이 있습니다.");
      }
      stock.deductQuantity(quantity);
}

// Stock::deductQuantity
public void deductQuantity(int quantity) {
    if (isQuantityLessThan(quantity)) {
        throw new IllegalArgumentException("차감할 재고 수량이 없습니다.");
    }
    this.quantity -= quantity;
}
```

- 서비스에서 진행한 예외처리 
  - 👉 주문 생성 로직을 수행하다가 재고에 대한 차감 시도
- 엔티티의 예외처리
  - 밖에 어떻게 되어있는지 전혀 모른다. **단위 테스트**이다.
  - 올바른 재고 감소 로직을 보장해줘야한다.
  
👉 둘의 차이를 드러내기 위해 **일부러 메시지도 다르게 적었다.**   

예외를 사용하는 컨텍스트 자체가 다르다. 예외 메시지가 화면까지 내려간다면 사용자 친화적인 메시지를 적어줄 수도 있는거다. **예외를 핸들링하고자 하는 방향이 다르기 때문에** 두 번 체크를 하는 로직이 나오게 되었다.

### 테스트에 @Transactional Vs. repository.deleteAll()

```java
// @Transactional
@Transactional
@SpringBootTest 
class OrderServiceTest {
	// ...
}

// repository.deleteAll(deleteAllInBatch)
@AfterEach
void tearDown() {
    orderProductRepository.deleteAllInBatch();
    productRepository.deleteAllInBatch();
    orderRepository.deleteAllInBatch();
    stockRepository.deleteAllInBatch();
}
```

`@Transactional`을 사용할 때는 UPDATE 쿼리가 잘 나가는데, tearDown()에서 `repository.deleteAll`을 사용할 때는 UPDATE 쿼리가 안 나간다.

👉  실제 서비스 객체에 `@Transactional`이 없기 때문.

- 트랜잭션 경계가 있어야 스냅샷을 뜨고 변경감지를 한다.    
- 문제는, 실제 서비스 객체에 `Transactioanl`을 사용 안 했는데도 테스트가 통과해버린다는 것
  - **실제 프로덕션 코드에 경계가 설정되어있는 것처럼 보인다.**
  
**👉 쓰지말자라는게 아니라, 부작용을 인지하고 쓰자!**