package com.example.concurrency.domain.pessimistic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class StockServiceTest {

    @Autowired
    StockService stockService;

    @Autowired
    StockRepository stockRepository;

    @Test
    void decreaseQuantity() throws InterruptedException {
        // given
        final long stockQuantity = 2L;
        final Stock stock = new Stock(stockQuantity);
        stockRepository.save(stock);

        final int threadCount = (int) stockQuantity;
        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    stockService.decreaseQuantityUsingPessimisticReadLock(stock.getId(), 1L);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await(); // 모든 스레드의 작업이 끝날 때까지 대기

        // then
        final Stock findStock = stockRepository.findById(stock.getId()).orElseThrow();
        assertThat(findStock.getQuantity()).isEqualTo(0L);
    }
}
