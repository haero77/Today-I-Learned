package com.example.tddstartmadvirus.chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {

	/**
	 * 아직 파라미터가 두 개고, 파라미터가 더 많아지면 객체 형태로 바꿔서 파라미터를 한 개로 만들겠지만,
	 * 아직 파라미터가 더 추가될지 알 수 없음. 👉 발생하지 않았는데 미리 단정 지어 코드를 수정할 필요 없다.
	 */
	public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
		/**
		 * 테스트 통과를 위해 한 번 더 상수를 추가해도 되지만,
		 * 예가 비교적 단순하므로 바로 구현을 일반화 했다.
		 */
		return billingDate.plusMonths(1);
	}

}
