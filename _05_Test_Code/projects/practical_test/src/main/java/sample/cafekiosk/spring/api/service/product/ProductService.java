package sample.cafekiosk.spring.api.service.product;

import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;

/**
 * readOnly = ture: 읽기전용
 * CRUD 에서 CUD 동작 X / only Read
 * JPA : CUD 스냅샷 저장 안함, 변경감지 X (성능향상)
 *
 * CQRS: Command / Read
 * - Command 보다 Read 가 훨씬 많다.
 * - Read가 많다고 Command가 영향 받거나, Commnad 때문에 Read가 영향받으면 안 된다.
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;

	// 💡 동시성 이슈 발생 가능 - 상품을 여러명이서 동시에 등록하는 경우 -> UUID도 좋은 선택
	@Transactional
	public ProductResponse createProduct(ProductCreateRequest request) {
		String nextProductNumber = createNextProductNumber();

		Product product = request.toEntity(nextProductNumber);
		Product savedProduct = productRepository.save(product);

		return ProductResponse.of(savedProduct);
	}

	public List<ProductResponse> getSellingProducts() {
		List<Product> products = productRepository.findAllBySellingStatusIn(forDisplay());

		return products.stream()
			.map(ProductResponse::of)
			.collect(Collectors.toList());
	}

	private String createNextProductNumber() {
		String latestProductNumber = productRepository.findLatestProductNumber();
		if (latestProductNumber == null) {
			return "001";
		}

		int latestProductNumberInt = Integer.parseInt(latestProductNumber);
		int nextProductNumber = latestProductNumberInt + 1;

		return String.format("%03d", nextProductNumber);
	}

}
