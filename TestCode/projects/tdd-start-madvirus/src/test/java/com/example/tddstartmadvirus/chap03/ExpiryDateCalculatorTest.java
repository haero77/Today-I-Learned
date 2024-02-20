package com.example.tddstartmadvirus.chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpiryDateCalculatorTest {

	@DisplayName("1만원 납부할 시, 만료일은 한달 뒤이다.")
	@Test
	void ten_thousand_won() {
		assertExpiryDate(
				LocalDate.of(2019, 3, 1), 10_000,
				LocalDate.of(2019, 4, 1));

		assertExpiryDate(
				LocalDate.of(2019, 5, 5), 10_000,
				LocalDate.of(2019, 6, 5));
	}

	/**
	 * 쉬운 구현을 했으니, 예외 상황을 찾아보자!
	 */
	@DisplayName("납부일과 한 달 뒤 일자가 같지 않음")
	@Test
	void date_not_same() {
		assertExpiryDate(
				LocalDate.of(2019, 1, 31), 10_000,
				LocalDate.of(2019, 2, 28)
		);

		assertExpiryDate(
				LocalDate.of(2019, 5, 31), 10_000,
				LocalDate.of(2019, 6, 30)
		);

		assertExpiryDate(
				LocalDate.of(2020, 1, 31), 10_000,
				LocalDate.of(2020, 2, 29)
		);
	}

	/**
	 * 보통은 중복을 제거하는 것이 좋지만, 테스트 코드의 중복 제거는 고민 필요.
	 * 👉 각 테스트 메서드는 스스로 무엇을 테스트하는지 명확하게 설명할 수 있어야 하기 때문.
	 */
	/**
	 * 첫 번째 파라미터와 세 번째 파라미터가 둘 다 LocalDate이므로,
	 * 둘 중 어떤 파라미터가 납부일이고 어떤 파라미터가 기댓값인지 구분하려면 이 메서드의 구현을 살펴봐야한다.
	 *
	 * 그래도 해당 메서드가 길지 않고,
	 * 파라미터 개수도 세 개여서 테스트 코드를 볼 때 어떤 것을 검증하는지 쉽게 확인가능하다.
	 * 👉 이 정도면 중복을 제거 해도 괜찮겠다고 판단.
	 */
	private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
		ExpiryDateCalculator cal = new ExpiryDateCalculator();
		LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);
		assertThat(expiryDate).isEqualTo(expectedExpiryDate);
	}

}
