package sample.cafekiosk.spring.api.service.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;

@RequiredArgsConstructor
@Service
public class OrderService {

	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;

	public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTime) {
		List<String> productNumbers = request.getProductNumbers();

		// 중복이 제거된 프로덕트가 조회된다.
		List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);

		// productNumber를 기반으로 Product를 빨리 찾을 수 있는 Map을 만든다.
		Map<String, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getProductNumber, product -> product));

		// productNumber -> Product 변환
		List<Product> duplicateProducts = productNumbers.stream()
			.map(productMap::get)
			.collect(Collectors.toList());

		// LocalDateTime.now()가 서비스 레이어에 있으면 테스트 하기 어려우므로 파라미터로 추출
		Order order = Order.create(duplicateProducts, registeredDateTime);
		Order savedOrder = orderRepository.save(order);

		return OrderResponse.of(savedOrder);
	}

}
