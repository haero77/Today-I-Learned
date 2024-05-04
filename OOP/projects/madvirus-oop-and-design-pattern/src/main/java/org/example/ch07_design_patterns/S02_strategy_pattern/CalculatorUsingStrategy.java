package org.example.ch07_design_patterns.S02_strategy_pattern;

import java.util.List;

/**
 * 가격 계산에 모듈에 할인 정책 구현이 포함된 코드
 */
public class CalculatorUsingStrategy {

	private final DiscountStrategy discountStrategy;

	public CalculatorUsingStrategy(DiscountStrategy discountStrategy) {
		this.discountStrategy = discountStrategy;
	}

	public int calculate(List<Item> items) {
		int sum = 0;

		for (Item item : items) {
			sum += discountStrategy.getDiscountPrice(item);
		}

		return sum;
	}

}
