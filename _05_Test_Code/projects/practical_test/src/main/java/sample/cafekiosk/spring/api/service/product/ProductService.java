package sample.cafekiosk.spring.api.service.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;

	/**
	 * 💡 보통 Create(또는 Save) 행위를 했을 때는 어떤 것이 생성되었는지 응답으로 주는 경우가 많다.
	 */
	public ProductResponse createProduct(ProductCreateRequest request) {
		String latestProductNumber = productRepository.findLatestProductNumber();
		// productNumber
		// 001 002 003 004
		// DB에서 마지막 저장된 Product의 상품 번호를 읽어와서 + 1
		// 009 -> 010

		return null;
	}

	public List<ProductResponse> getSellingProducts() {
		List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

		return products.stream()
			.map(ProductResponse::of)
			.collect(Collectors.toList());
	}

}
