# Presentation Layer (1)

## Presentation Layer의 역할

- 외부 세계의 요청을 가장 먼저 받는 계층
- **파라미터에 대한 최소한의 검증**을 수행한다. 
  - 비즈니스 로직에 대한 것은 X

## 어떻게 테스트할 것인가 

<img width="925" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/351c860c-c198-4336-ba47-7441871a045c">

- Persistence Layer, Business Layer는 스프링을 통으로 띄워 테스트를 진행했다.
- Presentation Layer 테스트 할 때는 하위 레이어는 모킹처리

# Mock 

## MockMvc

- Mock(가짜) 객체를 사용해 스프링 MVC 동작을 재현할 수 있는 `테스트 프레임워크`

### 추가된 요구사항 

> ✅관리자 페이지에서 신규 상품을 등록할 수 있다. <br>
> ✅상품명, 상품 타입, 판매 상태, 가격 등을 입력받는다. 


### 💡 리포지토리 레이어의 구현에 관계없이 테스트를 작성하자

```java
/**
 * 일부러 native query로 작성한 이유
 *  👉 QueryDSL, Method Name 등 리포지토리의 구현에 관계없이 테스트를 작성해야함을 알려주기 위해
 */
@Query(value = "select p.product_number from Product p order by id desc limit 1", nativeQuery = true)
String findLatestProductNumber();
```

- _Native Query, QueryDSL 등 **리포지토리 구현에 관계없이 테스트를 작성하자**_

<br>

### 💡 서비스와 리포지토리 레이어의 테스트가 비슷하더라도 별도로 작성하자

- 얇은 로직을 갖는 서비스 레이어 테스트와 리포지토리 레이어 테스트는 거의 비슷하다.
  - 얇은 로직을 갖는 서비스 로직은 리포지토리에 대한 결이 그대로 따라오기 때문에 테스트코드가 둘이 거의 비슷하다.
 - **_서비스가 더 발전을 할수록 기능이 추가되기 때문에, 같은 테스트라 생각되더라도 작성하는 것이 좋다!_**
   - 서비스에서 검증할 항목이 더 많을 수도 있다.
   
 <br>

### 💡생성(또는 저장) 행위에 대해서는 보통 어떤 것을 생성했는지를 응답한다

```java
// Product 생성 
public ProductResponse createProduct(ProductCreateRequest request) {
        // ...
}
```

<br>

### 상품 생성 로직에서의 동시성 이슈 해결을 어떻게 할까

```java
// 💡 동시성 이슈 발생 가능 - 상품을 여러명이서 동시에 등록하는 경우
public ProductResponse createProduct(ProductCreateRequest request) {
    String nextProductNumber = createNextProductNumber();

    return PrductResponse.builder() ... 
}
```

- 데이터베이스 컬럼에 유니크 제약 조건을 준다.
  - 유니크 제약 조건 때문에 튕겼으면(다른사람이 선점), 3회 이상 재시도하는 로직 추가
  - 동시접속자가 적은 경우에는 이렇게 하면 된다. (크리티컬하지 않은 경우)

- 동시 접속자가 엄청 많은 경우는?
  - 정책을 세운다.
  - productNumber 를 증가하는 값이 아니라 `UUID`를 사용한다 등.

<br>

### 💡 readOnly를 통해 CQRS를 지켜보자

- 보통 Command 보다 Read가 훨씬 많음
- Read 가 많다고해서, Command가 안 되는 것은 문제이다.
  - 그 반대도 문제임
- AWS 오로라나 MySQL를 보면 `WRITE`랑 `READ`를 나눠서 쓴다.
  - `Master & Slave`
    - Master: WRITE
    - Slave: READ 전용. Master의 레플리카.
  - `readOnly=true`를 사용함으로써, 어떤 DB를 사용할지 정할 수 가 있음
    - true(Read) 👉 Slave DB
    - false(Command) 👉 Master DB


- 💡 **우빈쌤의 추천 방식**
  - Service 상단에 `readOnly=true`를 설정하고, CUD가 있다면 메서든 단위에 `@Transactional`
  - Command 용, Read 용 서비스를 분리해서 사용하자

<br>

---

# Presentation Layer (2)

### MockMvc: POST

- `POST`의 경우 message body 값을 넣다보니, 직렬화 & 역직렬화 과정을 거친다. 

### @MockBean

```java
/**
 * @MockBean:
 * - 컨테이너에 mockito로 만든 mock 객체를 넣어주는 역할
 * - 없으면 ProductController에서 productService 의존성 주입이 안되서 Failed to load ApplicationContext 에러 발생
 */
@MockBean
private ProductService productService;
```

- `mockito` 라이브러리는 Spring Boot start-test에 자동으로 포함되어있다. 

<br>

### Error creating bean with name 'jpaAuditingHandler' 해결 방법

```java
Error creating bean with name 'jpaAuditingHandler': Cannot resolve reference to bean 'jpaMappingContext' while setting constructor argument
```

- 테스트에는 `@WebMvcTest`를 사용했고, Application 클래스에 `@EnableJpaAuditing`가 붙어서 생긴 문제

```java
@EnableJpaAuditing 
@SpringBootApplication
public class CafekioskApplication {
}
```

👉 `Config`를 분리해줌으로써 해결한다.

```java
// 분리한 JpaAuditingConfig
@EnableJpaAuditing
@Configuration
public class JpaAuditingConfig {
}

// Application 클래스에서 기존의 @EnableJpaAuditing 는 삭제
@SpringBootApplication
public class CafekioskApplication {
}
```

- 실제 `@SpringBootTest`를 사용할 때는 `JpaAuditingConfig`가 적용되므로 괜찮고, `@WebMvcTest`에서는 `JpaAuditingConfig`와 상관없이 Context가 뜰 거기 때문에 괜찮다.

<br>

## 💡 규격화된 응답을 만들자

```java
public class ApiResponse<T> {

	private int code;
	private HttpStatus status;
	private String message;
	private T data;

	private ApiResponse(HttpStatus status, String message, T data) {
		this.code = status.value();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static <T> ApiResponse<T> of(HttpStatus status, String message, T data) {
		return new ApiResponse<>(status, message, data);
	}

	public static <T> ApiResponse<T> of(HttpStatus status, T data) {
		return of(status, status.name(), data);
	}

	public static <T> ApiResponse<T> ok(T data) {
		return of(HttpStatus.OK, data);
	}
	
	// badRequest, ... 

}
```

### ResponseEntity Vs. ApiResponse 에 대한 내 생각

- `ResponseEntity<ResponseDto>`를 쓸 때랑, `ApiRepsonse<ResponseDto>`와의 차이점이 뭘까 생각해봤는데, 
  - `ApiResponse`를 쓰면 성공/실패 요청 모두 일관적인 데이터 응답이 온다.
  - 클라이언트 입장에서, 응답을 활용하려면 **`data`에 접근해서 사용하도록 강제하는게 더 일관적인 사용이 될 것 같다.**

## `@NotNull`, `@NotEmpty`, `@NotBlank`

- `@NotNull`
  - null 이 아니어야 한다.
  - **빈 문자열 `""`, 공백이 있는 문자열 `" "` 통과**
  - **Enum은 `@NotNull`만 사용 가능**
- `@NotEmpty`
  - 빈 문자열 `""` 통과 불가능
  - **공백이 있는 문자열 `" "` 통과**
- `@NotBlank`
  - 공백이 있는 문자열 `" "` 통과 불가능
  - 문자가 필수가 있어야한다. 

문자열의 경우 공백 문자를 통과시키는 경우가 많이 없으므로 주로 `@NotBlank`를 사용한다.

<br>

## GET을 테스트 하기

```java
@DisplayName("판매 상품을 조회한다.")
@Test
void getSellingProducts() throws Exception {
    // given
    List<ProductResponse> result = List.of();

    when(productService.getSellingProducts()).thenReturn(result);

    // when // then
    mockMvc.perform(
            get("/api/v1/products/selling")
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value("200"))
        .andExpect(jsonPath("$.status").value("OK"))
        .andExpect(jsonPath("$.message").value("OK"))
        .andExpect(jsonPath("$.data").isArray());
}
```

- **왜 실제 데이터를 넣고, 그 값이 리턴되는지를 확인하지 않을까?**
  - **이미 하위 레이어(서비스)에서 전부 검증한 부분이기 때문!**
- 여기서는 리턴 타입이 array 인지만 확인하면 된다.