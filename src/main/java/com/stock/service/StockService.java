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

	@Transactional
	public void decrease(Long id, Long quantity) {
		Stock findStock = stockRepository.findById(id).orElseThrow();

		findStock.decrease(quantity);

		stockRepository.saveAndFlush(findStock); // 명시적으로 save를 나타내려고 사용.
	}
}
