package org.example.ch07_design_patterns.S02_strategy_pattern;

public class Item {

	private final int price;
	private final boolean isFresh;

	public Item(int price, boolean isFresh) {
		this.price = price;
		this.isFresh = isFresh;
	}

	public int getPrice() {
		return price;
	}

	public boolean isFresh() {
		return isFresh;
	}

}
