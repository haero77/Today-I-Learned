package sample.cafekiosk.spring.api.controller.order;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sample.cafekiosk.spring.ApiResponse;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.order.OrderService;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;

@RequiredArgsConstructor
@RestController
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/api/v1/orders/new")
	public ApiResponse<OrderResponse> createOrder(@Valid @RequestBody OrderCreateRequest request) {
		LocalDateTime registeredDateTime = LocalDateTime.now(); // registeredDateTime를 명시적으로 나타내기 위해 로컬변수로 추출
		return ApiResponse.ok(orderService.createOrder(request, registeredDateTime));
	}

}
