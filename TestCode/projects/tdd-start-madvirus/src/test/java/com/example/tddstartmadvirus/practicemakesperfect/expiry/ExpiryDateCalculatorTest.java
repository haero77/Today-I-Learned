package com.example.tddstartmadvirus.practicemakesperfect.expiry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpiryDateCalculatorTest {

	private final ExpiryDateCalculator sut = new ExpiryDateCalculator();

	@DisplayName("1만원을 납부하면 만료일은 한 달 뒤이다.")
	@Test
	void when_10_000() {
		assertExpiryDate(LocalDate.of(2024, 2, 21), 10_000, LocalDate.of(2024, 3, 21));
		assertExpiryDate(LocalDate.of(2024, 3, 21), 10_000, LocalDate.of(2024, 4, 21));
	}

	private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expiryDateExpected) {
		assertThat(sut.calculate(billingDate, payAmount)).isEqualTo(expiryDateExpected);
	}

}
