package sample.cafekiosk.unit;

import java.time.LocalDateTime;

import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;

public class CafekioskRunner {

	public static void main(String[] args) {
		Cafekiosk cafekiosk = new Cafekiosk();

		cafekiosk.add(new Americano());
		System.out.println(">>> 아메리카노 추가");

		cafekiosk.add(new Latte());
		System.out.println(">>> 라떼 추가");

		// int totalPrice = cafekiosk.calculateTotalPrice();
		// System.out.println("총 주문 가격: " + totalPrice);

		/**
		 * 프로덕션 코드에서는 LocalDateTime.now()를 쓰지만,
		 * 테스트 코드에서는 LocalDateTime.now()를 쓰지 않는다. 👉 테스트하기 어려운 영역을 분리하기
		 */
		Order order = cafekiosk.createOrder(LocalDateTime.now());
	}
}
