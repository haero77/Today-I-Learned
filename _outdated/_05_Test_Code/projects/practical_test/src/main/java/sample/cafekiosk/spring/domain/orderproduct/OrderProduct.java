package sample.cafekiosk.spring.domain.orderproduct;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.domain.BaseEntity;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.product.Product;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderProduct extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Order order;

	// 양방향 필요없는 이유:
	// - '상품'은 내가 어느 Order에 담겨있는지 알 필요가 없다.
	// - "'주문'은 내가 어떤 상품들로 이루어진 주문이야"를 알 필요가 있기 때문에 양방향 매핑한다.
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;

	public OrderProduct(Order order, Product product) {
		this.order = order;
		this.product = product;
	}

}
