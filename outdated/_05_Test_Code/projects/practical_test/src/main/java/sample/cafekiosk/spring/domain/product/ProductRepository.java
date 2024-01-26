package sample.cafekiosk.spring.domain.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * select *
	 * from product
	 * where selling_status in ('SELLING', 'HOLD')
	 */
	List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> sellingTypes);

	// ["001", "001"] 로 조회시 IN절로 조회하기 때문에 결과 Product가 1개만 나온다.
	List<Product> findAllByProductNumberIn(List<String> productNumbers);

	/**
	 * 일부러 native query로 작성한 이유
	 *  👉 QueryDSL, Method Name 등 리포지토리의 구현에 관계없이 테스트를 작성해야함을 알려주기 위해
	 */
	@Query(value = "select p.product_number from Product p order by id desc limit 1", nativeQuery = true)
	String findLatestProductNumber();
}
