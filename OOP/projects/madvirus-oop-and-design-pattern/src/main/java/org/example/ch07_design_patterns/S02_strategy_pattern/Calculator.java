package org.example.ch07_design_patterns.S02_strategy_pattern;

import java.util.List;

/**
 * 가격 계산에 모듈에 할인 정책 구현이 포함된 코드
 */
public class Calculator {

	/**
	 * 서로 다른 계산 정책들이 한 코드에 섞여 있어, 정책이 추가될 수록 코드 분석을 어렵게 만든다.
	 * 가격 정책 (할인 정책) 이 추가 될 때마다, 메서드를 수정하는 것이 어려워짐.
	 */
	public int calculate(boolean isFirstGuest, List<Item> items) {
		int sum = 0;

		for (Item item : items) {

			if (isFirstGuest) {
				sum += (int) (item.getPrice() * 0.9); // 첫 손님 10% 할인
			} else if (!item.isFresh()) { // 덜 신선한 것 20% 할인
				sum += (int) (item.getPrice() * 0.8);
			} else  {
				sum += item.getPrice();
			}
		}

		return sum;
	}

}
