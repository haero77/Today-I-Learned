package com.example.tddstartmadvirus.chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {

	public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
		/**
		 * 테스트 통과를 위해 한 번 더 상수를 추가해도 되지만,
		 * 예가 비교적 단순하므로 바로 구현을 일반화 했다.
		 */
		return billingDate.plusMonths(1);
	}

}
