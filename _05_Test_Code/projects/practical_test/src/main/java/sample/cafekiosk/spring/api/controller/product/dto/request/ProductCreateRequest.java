package sample.cafekiosk.spring.api.controller.product.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {

	@NotNull(message = "상품 타입은 필수입니다.")
	private ProductType type;

	@NotNull(message = "상품 판매상태는 필수입니다.")
	private ProductSellingStatus sellingStatus;

	/**
	 * '상품 이름은 20자 제한' 이라는 비즈니스 정책이 추가되었다고 하자.
	 * - @Max(20) 을 통해 검증할 수 도 있지만, 이게 여기서 검증해야하는 것이 맞나? 라고 고민해봐야한다.
	 * - 문자열이라면 당연히 가져야하는 조건(NotNull, NotBlank, NotEmpty)에 대한 검증과 도메인 성격에 맞는 검증을 분리하는 시야를 키워라
	 * - 비즈니스 정책이라면 더 안쪽의 서비스나 도메인 객체 생성시에 검증하는 것을 추천.
	 */
	@NotBlank(message = "상품 이름은 필수입니다.")
	// @Max(20)
	private String name;

	@Positive(message = "상품 가격은 양수여야 합니다.")
	private int price;

	@Builder
	private ProductCreateRequest(ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
		this.type = type;
		this.sellingStatus = sellingStatus;
		this.name = name;
		this.price = price;
	}

	public ProductCreateServiceRequest toServiceRequest() {
		return ProductCreateServiceRequest.builder()
			.type(type)
			.sellingStatus(sellingStatus)
			.name(name)
			.price(price)
			.build();
	}
}
