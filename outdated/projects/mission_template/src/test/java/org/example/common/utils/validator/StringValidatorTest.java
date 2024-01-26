package org.example.common.utils.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.example.utils.StringValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class StringValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {" ", "\n", "  "})
    @NullAndEmptySource
    void validateHasText_exception(String source) {
        assertThatThrownBy(() -> StringValidator.validateHasText(source))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void test() {
        // given
        StringValidator.validateHasText(null);

        // when

        // then
    }

}