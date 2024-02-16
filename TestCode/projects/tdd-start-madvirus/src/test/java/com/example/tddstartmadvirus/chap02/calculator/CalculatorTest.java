package com.example.tddstartmadvirus.chap02.calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

	@Test
	void plus() {
		int result = Calculator.plus(1, 2);
		assertThat(result).isEqualTo(3);

		assertThat(Calculator.plus(1, 3)).isEqualTo(4);
	}

	@Test
	void plus2() {
		int result = Calculator.plus(3, 5);
		assertThat(result).isEqualTo(8);
	}

}
