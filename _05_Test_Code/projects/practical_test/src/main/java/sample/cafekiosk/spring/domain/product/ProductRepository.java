package sample.cafekiosk.spring.domain.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
