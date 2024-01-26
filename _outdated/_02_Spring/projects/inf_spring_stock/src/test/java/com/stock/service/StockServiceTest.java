package com.stock.service;

import static org.assertj.core.api.Assertions.*;

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

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	void stock_decrease_100() throws InterruptedException {
		// given
		int threadCount = 100;

		// ExecutorService: 비동기로 실행하는 작업을 단순화하여 사용할 수 있게 도와주는 Java API
		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

		// CountDownLatch: 다른 스레드에서 수행 중인 작업이 완료될 때까지 대기할 수 있도록 도와주는 클래스
		CountDownLatch countDownLatch = new CountDownLatch(threadCount); // 100개의 요청이 끝날 때까지 기다리기 위한 CountDownLatch

		// when
		for (int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {
					stockService.decrease(1L, 1L);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();
				}
			});
		}

		countDownLatch.await(); // 100개의 스레드 요청이 종료될 때까지 대기

		// then
		Stock findStock = stockRepository.findById(1L).orElseThrow();
		assertThat(findStock.getQuantity()).isEqualTo(0L);
	}
}