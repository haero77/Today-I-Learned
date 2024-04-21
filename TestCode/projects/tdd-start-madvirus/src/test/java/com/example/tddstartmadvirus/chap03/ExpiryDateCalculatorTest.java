package com.example.tddstartmadvirus.chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpiryDateCalculatorTest {

    @DisplayName("만원을 납부하면 한달 뒤가 만료일이다.")
    @Test
    void pay_10_000_() {
        LocalDate billingDate = LocalDate.of(2024, 3, 1);
        int payAmount = 10_000;

        ExpiryDateCalculator sut = new ExpiryDateCalculator();
        LocalDate result = sut.calculate(billingDate, payAmount);

        assertThat(result).isEqualTo(LocalDate.of(2024, 4, 1));

        LocalDate billingDate2 = LocalDate.of(2024, 2, 1);
        LocalDate result2 = sut.calculate(billingDate2, payAmount);

        assertThat(result2).isEqualTo(LocalDate.of(2024, 3, 1));
    }

}
