package sample.cafekiosk.unit.order;

import java.time.LocalDateTime;
import java.util.List;

import sample.cafekiosk.unit.beverage.Beverage;

public class Order {

	private LocalDateTime orderDateTime;
	private List<Beverage> beverages;

	public Order(LocalDateTime orderDateTime, List<Beverage> beverages) {
		this.orderDateTime = orderDateTime;
		this.beverages = beverages;
	}
}
