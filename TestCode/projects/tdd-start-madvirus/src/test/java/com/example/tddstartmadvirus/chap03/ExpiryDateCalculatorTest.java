package com.example.tddstartmadvirus.chap03;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ExpiryDateCalculatorTest {

    @DisplayName("만원을 납부하면 한달 뒤가 만료일이다.")
    @Test
    void pay_10_000_() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 3, 1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2024, 4, 1)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 2, 1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2024, 3, 1)
        );
    }

    @DisplayName("납부일과 한 달 뒤의 일자가 같지 않음.")
    @Test
    void pay_10_000_case_2() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2023, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2023, 2, 28)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2024, 2, 29)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 5, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2024, 6, 30)
        );
    }

    @DisplayName("첫 납부일 일자와 만료일의 일자가 같지 않을 때 1만원을 납부하면 첫 납부일 기준으로 다음 만료일 정함")
    @Test
    void firstBillingDate_diff() {
        // given
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2024, 1, 31))
                .billingDate(LocalDate.of(2024, 2, 29))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2024, 3, 31));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        final ExpiryDateCalculator sut = new ExpiryDateCalculator();
        final LocalDate actual = sut.calculate(payData);
        assertThat(actual).isEqualTo(expectedExpiryDate);
    }

}
