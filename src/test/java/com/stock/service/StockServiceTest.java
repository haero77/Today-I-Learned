package com.stock.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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
}