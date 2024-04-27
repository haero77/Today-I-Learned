package org.example.ch03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Currency;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @DisplayName("돈 생성 시 amount가 0이하면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1 , -100})
    void new_money_when_amount_is_negative(int amount) {
        assertThatThrownBy(() -> new Money(amount, Currency.getInstance(Locale.KOREA)))
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

    @DisplayName("금액을 추가한다.")
    @Test
    void add_amount() {
        // given
        final Money sut = new Money(0, Currency.getInstance(Locale.KOREA));

        // when
        sut.addAmount(300);

        // then
    	assertThat(sut.getAmount()).isEqualTo(300);
    }

}