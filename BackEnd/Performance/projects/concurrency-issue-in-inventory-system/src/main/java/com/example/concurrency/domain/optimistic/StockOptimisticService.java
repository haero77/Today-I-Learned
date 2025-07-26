package com.example.concurrency.domain.optimistic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockOptimisticService {

    private final StockOptimisticRepository stockRepository;

    public void decreaseQuantity(final Long stockId, final long quantityToDecrease) {
        final StockOptimistic stock = stockRepository.findById(stockId).orElseThrow();

        stock.decreaseQuantity(quantityToDecrease);

        stockRepository.save(stock);
    }
}
