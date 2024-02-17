package com.example.tddstartmadvirus.chap02.password;

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

}
