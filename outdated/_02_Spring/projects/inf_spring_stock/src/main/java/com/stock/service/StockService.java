package com.stock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stock.domain.Stock;
import com.stock.repository.StockRepository;

@Service
public class StockService {

	private final StockRepository stockRepository;

	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	/**
	 * @Tx를 사용하면 프록시 AOP로 동작하므로,
	 * decrease() 메서드 종료후 DB에서 트랜잭션 커밋되기 전에 다른 스레드가 값을 읽을 수 있으므로 갱신손실 문제는 여전하다.
	 *
	 * @Tx를 사용하지 않고, saveAndFlush()의 @Tx로 업데이트 하는 전략을 취한다.
	 */
	// @Transactional
	public synchronized void decrease(Long id, Long quantity) {
		Stock findStock = stockRepository.findById(id).orElseThrow();

		findStock.decrease(quantity);

		stockRepository.saveAndFlush(findStock); // 명시적으로 save를 나타내려고 사용.
	}
}
