package chap02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {

    @DisplayName("암호가 모든 조건을 충족하면 암호강도는 강함이어야 함")
    @Test
    void meetsAllCriteria_Then_Strong() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab12!@AB");
        assertThat(result).isEqualTo(PasswordStrength.STRONG);

        PasswordStrength result2 = meter.meter("abc1!Add");
        assertThat(result2).isEqualTo(PasswordStrength.STRONG);
    }

    @DisplayName("길이가 8글자 미만이고 나머지 조건은 충족하는 경우 암호강도는 보통이다.")
    @Test
    void meetOtherCriteria_except_for_Length_Then_Normal() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab12!@A");
        assertThat(result).isEqualTo(PasswordStrength.NORMAL);
    }

    @DisplayName("숫자를 포함하지 않고 나머지 조건을 충족할 경우 암호 강도는 보통이다.")
    @Test
    void meetOtherCriteria_except_for_number_Then_Normal() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab!@ABqwer");
        assertThat(result).isEqualTo(PasswordStrength.NORMAL);
    }

}
