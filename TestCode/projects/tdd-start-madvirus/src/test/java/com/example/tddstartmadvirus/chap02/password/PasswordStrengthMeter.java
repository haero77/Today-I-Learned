package com.example.tddstartmadvirus.chap02.password;

import static com.example.tddstartmadvirus.chap02.password.PasswordStrength.*;

public class PasswordStrengthMeter {

	public PasswordStrength meter(String password) {
		if (password == null || password.isEmpty()) {
			return INVALID;
		}

		int metCounts = 0;

		if (meetsLengthCriteria(password)) {
			metCounts++;
		}
		if (meetsContainingNumberCriteria(password)) {
			metCounts++;
		}
		if (meetsContainingUpperCaseCriteria(password)) {
			metCounts++;
		}

		if (metCounts <= 1) {
			return WEAK;
		}
		if (metCounts == 2) {
			return NORMAL;
		}
		return STRONG;
	}

	private boolean meetsLengthCriteria(String password) {
		return password.length() >= 8;
	}

	private boolean meetsContainingUpperCaseCriteria(String password) {
		for (char ch : password.toCharArray()) {
			if (ch >= 'A' && ch < 'Z') {
				return true;
			}
		}
		return false;
	}

	private boolean meetsContainingNumberCriteria(String password) {
		for (char ch : password.toCharArray()) {
			if (ch >= '0' && ch <= '9') {
				return true;
			}
		}
		return false;
	}

}
