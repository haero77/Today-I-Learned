package com.example.tddstartmadvirus.chap02.password;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.tddstartmadvirus.chap02.password.PasswordStrength.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordStrengthMeterTest {

	private final PasswordStrengthMeter meter = new PasswordStrengthMeter();

	@DisplayName("값이 없는 경우 강도는 INVALID")
	@Test
	void null_input_then_invalid() {
		assertStrength("", PasswordStrength.INVALID);
		assertStrength(null, PasswordStrength.INVALID);
	}

	@Test
	void meetsAllCriteria_then_strong() {
		assertStrength("0934AB!@", STRONG);
		assertStrength("abc1234A", STRONG);
	}

	@DisplayName("길이가 8글자 미만이고 나머지 충족하는 경우 강도는 보통.")
	@Test
	void meetsOtherCriteria_except_for_length_then_normal() {
		assertStrength("1234AB!", NORMAL);
	}

	@DisplayName("숫자를 포함하지 않고 나머지 조건을 충족하는 경우 강도는 보통")
	@Test
	void meetsOtherCriteria_except_for_number_then_normal() {
		assertStrength("ab!@ABqwer", NORMAL);
	}

	@DisplayName("대문자를 포함하지 않고 나머지 조건을 충족하는 경우 강도는 보통")
	@Test
	void meetsOtherCriteria_except_for_uppercase_then_normal() {
		assertStrength("ab!@5678", NORMAL);
	}

	@DisplayName("길이가 8글자 이상인 조건만 충족하는 경우 강도는 약함")
	@Test
	void meets_only_length_criteria_then_weak() {
		assertStrength("abcd!@#$", WEAK);
	}
	
	@DisplayName("숫자 포함 조건만 충족하는 경우 강도는 약함")
	@Test
	void meets_only_num_criteria_then_weak() {
		assertStrength("12!@", WEAK);
	}

	@DisplayName("대문자 포함 조건만 만족하는 경우 강도는 약함")
	@Test
	void meets_only_uppercase_then_weak() {
		assertStrength("A!", WEAK);
	}

	private void assertStrength(String password, PasswordStrength strength) {
		PasswordStrength result = meter.meter(password);
		assertThat(result).isEqualTo(strength);
	}

}
