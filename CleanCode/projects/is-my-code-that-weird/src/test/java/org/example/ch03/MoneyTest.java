package org.example.ch03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Currency;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @DisplayName("돈 생성 시 amount가 0이하면 예외 발생")
    @Test
    void new_money_when_amount_is_negative() {
        assertThatThrownBy(() -> new Money(-100, Currency.getInstance(Locale.KOREA)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 0 이상의 값을 지정해주세요.");
    }

    @DisplayName("돈 생성 시 currency가 null이면 예외 발생")
    @Test
    void new_money_when_currency_is_null() {
        assertThatThrownBy(() -> new Money(100, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("통화 단위를 지정해주세요.");
    }

}