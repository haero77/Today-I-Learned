package com.example.concurrency.domain.optimistic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockOptimisticRepository extends JpaRepository<StockOptimistic, Long> {

}
