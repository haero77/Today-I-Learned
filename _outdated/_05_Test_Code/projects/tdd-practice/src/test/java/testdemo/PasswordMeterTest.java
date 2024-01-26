package testdemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordMeterTest {

    // 보통 테스트 대상은 필드로 추출한다.
    private final PasswordMeter passwordMeter = new PasswordMeter();

    @DisplayName("null 입력이면 익센션 발생")
    @Test
    void nullInput() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> passwordMeter.meter(null));
    }

    @DisplayName("빈 값 입력이면 익센션 발생")
    @Test
    void emptyInput() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> passwordMeter.meter(""));
    }

    private void assertPasswordStrength(String password, PasswordStrength expected) {
        PasswordStrength result = passwordMeter.meter(password);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("모든 조건을 충족하면 강함")
    @Test
    void meetAllRules() {
        assertPasswordStrength("abcABC123", PasswordStrength.STRONG);
        assertPasswordStrength("123abcABC", PasswordStrength.STRONG);
    }

    @DisplayName("길이가 8미만, 다른 조건 충족")
    @Test
    void digitAndUppercase() {
        assertPasswordStrength("abcC123", PasswordStrength.NORMAL);
        assertPasswordStrength("123abcC", PasswordStrength.NORMAL);
    }

    @DisplayName("대문자 없음, 다른 조건 충족")
    @Test
    void digitAndLength() {
        assertPasswordStrength("123abcC", PasswordStrength.NORMAL);
    }

}
