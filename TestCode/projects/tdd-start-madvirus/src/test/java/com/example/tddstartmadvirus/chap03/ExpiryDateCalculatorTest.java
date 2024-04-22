package com.example.tddstartmadvirus.chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpiryDateCalculatorTest {

    @DisplayName("만원을 납부하면 한달 뒤가 만료일이다.")
    @Test
    void pay_10_000_() {
        assertExpiryDate(LocalDate.of(2024, 3, 1), 10_000, LocalDate.of(2024, 4, 1));
        assertExpiryDate(LocalDate.of(2024, 2, 1), 10_000, LocalDate.of(2024, 3, 1));
    }

    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator sut = new ExpiryDateCalculator();
        LocalDate actualExpiryDate = sut.calculate(billingDate, payAmount);
        assertThat(actualExpiryDate).isEqualTo(expectedExpiryDate);
    }

}
