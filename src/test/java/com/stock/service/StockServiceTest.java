package com.stock.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stock.domain.Stock;
import com.stock.repository.StockRepository;

@SpringBootTest
class StockServiceTest {

	@Autowired
	private StockService stockService;

	@Autowired
	private StockRepository stockRepository;

	@BeforeEach
	public void beforeEach() {
		Stock stock = new Stock(1L, 100L);
		stockRepository.save(stock);
	}

	@AfterEach
	public void afterEach() {
		stockRepository.deleteAll();
	}

	@Test
	void stock_decrease() {
	    // when
		stockService.decrease(1L, 1L);

	    // then
		Stock findStock = stockRepository.findById(1L).orElseThrow();
		assertThat(findStock.getQuantity()).isEqualTo(99L);
	}

	/*
		[테스트 결과]
		org.opentest4j.AssertionFailedError:
		expected: 0L
		but was: 89L
		Expected :0L
		Actual   :89L

		👉 문제 원인:
			- Race Condition이 일어났기 때문.
			- 갱신 손실 문제 접근 발생.

		💡 Race Condition?
		- 둘 이상의 스레드가 공유 데이터에 접근할 수 있고,
		- 동시에 변경을 하려고 할 때, 발생하는 문제
	 */
	@Test
	@DisplayName("동시에 100개의 재고 감소 요청")
	void stock_decrease_100() {

		// given
		int threadCount = 100;
		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		CountDownLatch countDownLatch = new CountDownLatch(threadCount);

		// when
		for (int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {
					stockService.decrease(1L, 1L);
				} finally {
					countDownLatch.countDown();
				}
			});
		}

		// then
		Stock findStock = stockRepository.findById(1L).orElseThrow();
		assertThat(findStock.getQuantity()).isEqualTo(0L);
	}
}