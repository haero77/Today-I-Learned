package com.example.tddstartmadvirus.chap02.password;

import static com.example.tddstartmadvirus.chap02.password.PasswordStrength.*;

public class PasswordStrengthMeter {

	/**
	 * 리팩토링 이후: 전반적인 로직이 눈에 들어온다.
	 */
	public PasswordStrength meter(String password) {
		if (isEmptyString(password)) {
			return INVALID;
		}

		// 암호 규칙이 궁금하면 getMetCriteriaCounts() 메서드를 보면된다.
		int metCounts = getMetCriteriaCounts(password);

		return meterStrength(metCounts);
	}

	private boolean isEmptyString(String password) {
		return password == null || password.isEmpty();
	}

	private int getMetCriteriaCounts(String password) {
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

		return metCounts;
	}

	private PasswordStrength meterStrength(int metCounts) {
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
