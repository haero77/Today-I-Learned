package com.stock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stock.domain.Stock;
import com.stock.repository.StockRepository;

@Service
public class PessimisticLockStockService {

	private final StockRepository stockRepository;

	public PessimisticLockStockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Transactional
	public void decrease(Long id, Long quantity) {
		Stock stock = stockRepository.findByIdWithPessimisticLock(id);// SELECT 시에 락킹

		stock.decrease(quantity);

		stockRepository.saveAndFlush(stock);
	}

}
