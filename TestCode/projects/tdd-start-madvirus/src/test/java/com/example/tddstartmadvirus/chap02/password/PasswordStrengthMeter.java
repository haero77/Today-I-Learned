package com.example.tddstartmadvirus.chap02.password;

import static com.example.tddstartmadvirus.chap02.password.PasswordStrength.*;

public class PasswordStrengthMeter {

	public PasswordStrength meter(String password) {
		if (password == null || password.isEmpty()) {
			return INVALID;
		}

		boolean lengthEnough = password.length() >= 8;
		boolean meetsContainingNumberCriteria = meetsContainingNumberCriteria(password);
		boolean meetsContainingUpperCaseCriteria = meetsContainingUpperCaseCriteria(password);

		if (lengthEnough && !meetsContainingNumberCriteria && !meetsContainingUpperCaseCriteria) {
			return WEAK;
		}

		if (!lengthEnough) {
			return NORMAL;
		}

		if (!meetsContainingNumberCriteria) {
			return NORMAL;
		}

		if (!meetsContainingUpperCaseCriteria) {
			return NORMAL;
		}

		return STRONG;
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
