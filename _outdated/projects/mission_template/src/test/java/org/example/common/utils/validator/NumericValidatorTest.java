package org.example.common.utils.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.example.utils.NumericValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumericValidatorTest {

    @DisplayName("0~9 사이의 숫자로만 이루어지지 않았다면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "\n", " ", "-1", "21314abc", "00o000"})
    void validateNumeric(String input) {
        // when & then
        assertThatThrownBy(() -> NumericValidator.validateNumeric(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1~9 사이의 숫자로만 이루어지지 않았다면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void validatePositive(int number) {
        // when & then
        assertThatThrownBy(() -> NumericValidator.validatePositive(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

}