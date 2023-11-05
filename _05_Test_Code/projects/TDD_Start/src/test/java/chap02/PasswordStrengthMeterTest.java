package chap02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {

    @DisplayName("암호가 모든 조건을 충족하면 암호강도는 강함이어야 함")
    @Test
    void meetsAllCriteria_Then_Strong() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength strength = meter.meter("ab12!@AB");
        assertThat(strength).isEqualTo(PasswordStrength.Strong);
    }

}
