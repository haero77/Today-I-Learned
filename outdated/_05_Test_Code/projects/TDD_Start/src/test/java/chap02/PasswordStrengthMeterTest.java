package chap02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {

    private final PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expectedStrength) {
        assertThat(meter.meter(password)).isEqualTo(expectedStrength);
    }

    @DisplayName("암호가 모든 조건을 충족하면 암호강도는 강함이어야 함")
    @Test
    void meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @DisplayName("길이가 8글자 미만이고 나머지 조건은 충족하는 경우 암호강도는 보통이다.")
    @Test
    void meetOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
    }

    @DisplayName("숫자를 포함하지 않고 나머지 조건을 충족할 경우 암호 강도는 보통이다.")
    @Test
    void meetOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    @DisplayName("null의 암호 강도는 INVALID이다.")
    @Test
    void nullInput_then_invalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @DisplayName("빈 문자열의 암호 강도는 INVALID이다.")
    @Test
    void emptyInput_then_invalid() {
        assertStrength("", PasswordStrength.INVALID);
    }

}
