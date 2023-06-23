package sample.cafekiosk.spring.api.service.order;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;

@RequiredArgsConstructor
@Service
public class OrderService {

	private final ProductRepository productRepository;

	public OrderResponse createOrder(OrderCreateRequest request) {
		List<String> productNumbers =  request.getProductNumbers();

		// product
		List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);

		// order
		Order order = Order.create(products);

		return null;
	}

}
