package org.example.common.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.example.common.utils.validator.NumericValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StringConvertorTest {

    @Test
    void toInt_success() {
        // when
        int result = StringConvertor.toInt("123");

        // then
        assertThat(result).isEqualTo(123);
    }

    @DisplayName("0~9 사이의 숫자로만 이루어지지 않았다면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "\n", " ", "-1", "21314abc", "00o000"})
    void toInt(String source) {
        // when & then
        assertThatThrownBy(() -> StringConvertor.toInt(source))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("toInt 에서 null 체크는 안 한다.")
    @Test
    void toInt_null() {
        // when & then
        assertThatThrownBy(() -> StringConvertor.toInt(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void isBlank() {
        assertThat(" ".isBlank()).isTrue();
    }

}