package com.example.concurrency.domain.pessimistic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public void decreaseQuantity(final Long stockId, final long quantityToDecrease) {
        final Stock stock = stockRepository.findById(stockId).orElseThrow();

        stock.decreaseQuantity(quantityToDecrease);

        stockRepository.save(stock);
    }
}
