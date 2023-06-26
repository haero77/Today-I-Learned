# 레이어별 의존성을 고려한 서비스용 DTO 만들기

```java
// Contoller 레이어의 Request DTO를 의존하는 Service 레이어
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest; 

@Service
public class OrderService {
    
	public OrderResponse createOrder(OrderCreateRequest request,
            // ...
	}
	
}
```

- 서비스에서, 컨트롤러의 Request DTO를 의존하고 있다. 
- 서비스가 컨트롤러를 알고 있기 때문에, 
  - 나중에 서비스가 엄청 커져 서비스와 컨트롤러를 분리하거나,
  - 모듈을 분리하고 싶은 경우에 분리하기 어렵게 된다.
- 👉 하위 레이어가 서비스용 DTO를 따로 만들자

### 서비스용 DTO 따로 만들기

```java
// 서비스용 DTO
@Getter
@NoArgsConstructor
public class OrderCreateServiceRequest {

	// 서비스에서 Validation 할 책임이 없다.
	private List<String> productNumbers;

	@Builder
	private OrderCreateServiceRequest(List<String> productNumbers) {
		this.productNumbers = productNumbers;
	}

}
```

- 서비스용 DTO를 따로 만듦으로써,
- Service to Controller의 의존성이 사라졌다.
- `Validation` 부분이 사라졌다.
  - 나중에 모듈로 분리할 때 편해진다.
  - Bean Validation이 사라졌으므로 springboot-starter 의존성을 더 이상 안 들고 가도 된다.
  - _**👉서비스의 DTO는 클린한 `POJO` 형태로 관리할 수 있게 된다.**_
  - Controller DTO 에서만 Validation 하도록 책임을 분리할 수 있게된다.

<br>

### 서비스 DTO를 사용함으로써 편리해지는 경우

- 주문 받는 채널이 많아지는 경우
  - 지금은 `키오스크`에서만 주문을 받지만,
  - 나중에는 `POS`나 `사이렌 오더`에서도 주문받을 수 있다.


- **주문 받는 채널이 많아져도 같은 서비스 객체를 사용하고 싶다!**
  - 특정 컨트롤러의 DTO를 사용하는 것이 아니라, 서비스 자체의 DTO를 사용함으로써 **컨트롤러를 갈아끼울 수 있게 된다.**
  - **_서비스가 특정 컨트롤러의 DTO를 사용하게 되면, 다른 컨트롤러도 특정 컨트롤러의 DTO를 모두 알아야된다!!._**

<br>

### 핵심은 변경에 유연한 설계

- **`Presentation Layer`가 변경되어도, `Service Layer`는 영향받지 않도록 하는 것이 좋은 설계**이다.
- 서비스 DTO를 분리하는 것이 의존성이나 책임분리 측면에서 좋다!




