package com.stock.facade;

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

@SpringBootTest
class OptimisticLockStockFacadeTest {

	@Autowired
	private OptimisticLockStockFacade optimisticLockStockFacade;

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

	@DisplayName("동시에 100개의 재고 감소 요청")
	@Test
	void stock_decrease_100() throws InterruptedException {
		// given
		int threadCount = 100;
		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		CountDownLatch countDownLatch = new CountDownLatch(threadCount);

		// when
		for (int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {
					optimisticLockStockFacade.decrease(1L, 1L);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				} finally {
					countDownLatch.countDown();
				}
			});
		}

		countDownLatch.await();

		// then
		Stock findStock = stockRepository.findById(1L).orElseThrow();
		assertThat(findStock.getQuantity()).isEqualTo(0L);
	}

}