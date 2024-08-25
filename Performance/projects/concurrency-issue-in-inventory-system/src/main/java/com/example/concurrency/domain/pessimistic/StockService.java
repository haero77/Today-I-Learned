package com.example.concurrency.domain.pessimistic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    @Transactional
    public void decreaseQuantityUsingPessimisticWriteLock(final Long stockId, final long quantityToDecrease) {
        final Stock stock = stockRepository.findByIdWithPessimisticWriteLock(stockId).orElseThrow();
        stock.decreaseQuantity(quantityToDecrease);
        stockRepository.save(stock);
    }

    @Transactional
    public void decreaseQuantityUsingPessimisticReadLock(final Long stockId, final long quantityToDecrease) {
        final Stock stock = stockRepository.findByIdWithPessimisticReadLock(stockId).orElseThrow();
        stock.decreaseQuantity(quantityToDecrease);
        stockRepository.save(stock);
    }
}
