package org.example.ch07_design_patterns.S02_strategy_pattern;

public class FirstGuestDiscountStrategy implements DiscountStrategy {

	@Override
	public int getDiscountPrice(Item item) {
		return (int) (item.getPrice() * 0.9);
	}

}
