package testdemo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class PasswordMeterTest {

    private final PasswordMeter meter = new PasswordMeter();

    @DisplayName("모든 규칙 만족 시 암호 강도는 강함")
    @Test
    void all_pass() {
        assertStrength("ABCabc123", PasswordStrength.STRONG);
    }

    @DisplayName("길이가 8글자 미만이고 나머지 조건 충족 시 강도는 보통")
    @Test
    void length_under_8() {
        assertStrength("ABCabc1", PasswordStrength.NORMAL);
    }

    @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족하는 경우 강도는 보통")
    @Test
    void not_contain_number() {
        assertStrength("ABCabc!@#", PasswordStrength.NORMAL);
    }

    @DisplayName("값이 없는 경우")
    @ParameterizedTest
    @NullAndEmptySource
    void null_and_empty_invalid(String password) {
        assertStrength(password, PasswordStrength.INVALID);
    }

    @DisplayName("대문자를 포함하지 않고 나머지 조건은 충족하는 경우 강도는 보통")
    @Test
    void not_contain_capital() {
        assertStrength("abc123!@#", PasswordStrength.NORMAL);
    }

    @DisplayName("길이가 8 이상인 조건만 충족하는 경우 강도는 약함")
    @Test
    void only_meet_length_criterion() {
        assertStrength("abcde!@#$", PasswordStrength.WEAK);
    }

    @DisplayName("숫자 포함 조건만 충족하는 경우 강도는 약함")
    @Test
    void only_meet_number_contain_criterion() {
        assertStrength("12!@", PasswordStrength.WEAK);
    }

    private void assertStrength(String password, PasswordStrength expected) {
        assertThat(meter.meter(password)).isEqualTo(expected);
    }

}
