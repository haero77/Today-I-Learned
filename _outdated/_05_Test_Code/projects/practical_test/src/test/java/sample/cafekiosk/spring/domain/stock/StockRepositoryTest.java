package sample.cafekiosk.spring.domain.stock;

import static org.assertj.core.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductType;

@DataJpaTest
class StockRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StockRepository stockRepository;

	@Test
	@DisplayName("상품번호 리스트로 재고를 조회한다.")
	void findAllByProductNumberIn() {
		// given
		Stock stock1 = Stock.create("001", 1);
		Stock stock2 = Stock.create("002", 2);
		Stock stock3 = Stock.create("003", 3);
		stockRepository.saveAll(List.of(stock1, stock2, stock3));

		// when
		List<Stock> stocks = stockRepository.findAllByProductNumberIn(List.of("001", "002"));

		// then
		assertThat(stocks).hasSize(2)
			.extracting("productNumber", "quantity")
			.containsExactlyInAnyOrder(
				tuple("001", 1),
				tuple("002", 2)
			);
	}

	/**
	 * 직접 만들어본 테스트
	 */
	@DisplayName("상품번호 리스트로 재고를 조회할 수 있다.") // "조회한다"로 끝맺어도 좋을 듯.
	@Test
	void findAllByProductNumberIn_DIY() {
		// given

		// 👉 실제 연관관계를 맺거나 한 것은 아니기 때문에, product를 굳이 save할 필요는 없다.
		// List<Product> products = List.of(
		// 	createProduct("001", 1000),
		// 	createProduct("002", 2000)
		// );
		// productRepository.saveAll(products);

		Stock stock1 = Stock.create("001", 1);
		Stock stock2 = Stock.create("002", 2);
		stockRepository.saveAll(List.of(stock1, stock2));

		List<String> productNumbers = List.of("001", "002");

		// when
		List<Stock> stocks = stockRepository.findAllByProductNumberIn(productNumbers);

		// then
		assertThat(stocks).hasSize(2)
			.extracting("productNumber", "quantity")
			.containsExactlyInAnyOrder(
				tuple("001", 1),
				tuple("002", 2)
			);
	}

	private Product createProduct(String productNumber, int price) {
		return Product.builder()
			.type(ProductType.BOTTLE)
			.productNumber(productNumber)
			.price(price)
			.sellingStatus(SELLING)
			.name("menu name")
			.build();
	}
}