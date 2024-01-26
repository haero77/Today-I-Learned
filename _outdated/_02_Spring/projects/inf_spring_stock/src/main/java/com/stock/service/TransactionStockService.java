package com.stock.service;

import org.springframework.stereotype.Service;

@Service
public class TransactionStockService {

	private final StockService stockService;

	public TransactionStockService(StockService stockService) {
		this.stockService = stockService;
	}

	public void decrease(Long id, Long quantity) {
		startTransaction();

		stockService.decrease(id, quantity); // 1. Thread1 에서 10:00 에 decrease 완료

		// 2. Thread2가 10:00 ~ 10:05 사이에 값을 읽음 👉 갱신 손실 문제는 여전하다.

		endTransaction(); // 3.Thread1 에서 10:05 에 트랜잭션 완료. 이후 DB 커밋
	}

	private void endTransaction() {
	}

	private void startTransaction() {
	}
}
