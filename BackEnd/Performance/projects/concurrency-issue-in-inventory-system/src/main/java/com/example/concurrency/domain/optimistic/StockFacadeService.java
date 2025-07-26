package com.example.concurrency.domain.optimistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockFacadeService {

    private final StockOptimisticService stockService;

    public void decreaseQuantity(final Long stockId, final long quantityToDecrease) throws InterruptedException {
        while (true) {
            try {
                stockService.decreaseQuantity(stockId, quantityToDecrease);
                break;
            } catch (ObjectOptimisticLockingFailureException e) {
                log.warn(e.getMessage());
                Thread.sleep(30);
            }
        }
    }
}
