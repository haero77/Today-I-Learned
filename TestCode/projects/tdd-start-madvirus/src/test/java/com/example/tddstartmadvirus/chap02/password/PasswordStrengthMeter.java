package com.example.tddstartmadvirus.chap02.password;

import static com.example.tddstartmadvirus.chap02.password.PasswordStrength.NORMAL;
import static com.example.tddstartmadvirus.chap02.password.PasswordStrength.STRONG;

public class PasswordStrengthMeter {

	public PasswordStrength meter(String password) {
		if (password.length() < 8) {
			return NORMAL;
		}

		return STRONG;
	}

}
