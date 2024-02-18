package com.example.tddstartmadvirus.chap02.password;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordStrengthMeterTest {

	@Test
	void meetsAllCriteria_then_strong() {
		PasswordStrengthMeter meter = new PasswordStrengthMeter();

		PasswordStrength result = meter.meter("0934AB!@");
		assertThat(result).isEqualTo(PasswordStrength.STRONG);

		PasswordStrength result2 = meter.meter("abc1234A");
		assertThat(result2).isEqualTo(PasswordStrength.STRONG);
	}

	@DisplayName("길이가 8글자 미만이고 나머지 충족하는 경우 강도는 보통.")
	@Test
	void meetsOtherCriteria_except_for_length_then_normal() {
		PasswordStrengthMeter meter = new PasswordStrengthMeter();
		PasswordStrength result = meter.meter("1234AB!");
		assertThat(result).isEqualTo(PasswordStrength.NORMAL);
	}

}
