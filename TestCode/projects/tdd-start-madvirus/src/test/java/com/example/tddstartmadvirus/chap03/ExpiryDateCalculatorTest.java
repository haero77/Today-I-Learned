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

    @DisplayName("납부일과 한 달 뒤의 일자가 같지 않음.")
    @Test
    void pay_10_000_case_2() {
        assertExpiryDate(LocalDate.of(2023, 1, 31), 10_000, LocalDate.of(2023, 2, 28));
        assertExpiryDate(LocalDate.of(2024, 1, 31), 10_000, LocalDate.of(2024, 2, 29));
        assertExpiryDate(LocalDate.of(2024, 5, 31), 10_000, LocalDate.of(2024, 6, 30));
    }

    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
        PayData payData = PayData.builder()
                .billingDate(billingDate)
                .payAmount(payAmount)
                .build();
        assertExpiryDate(payData, expectedExpiryDate);
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator sut = new ExpiryDateCalculator();
        LocalDate actualExpiryDate = sut.calculate(payData);
        assertThat(actualExpiryDate).isEqualTo(expectedExpiryDate);
    }

}
