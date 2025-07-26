package org.example.ch07_design_patterns.S02_strategy_pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorUsingStrategyTest {

	@DisplayName("첫 손님은 10% 할인한다.")
	@Test
	void first_guest() {
	    // given
		final CalculatorUsingStrategy sut = new CalculatorUsingStrategy(new FirstGuestDiscountStrategy());

		final List<Item> items = List.of(
			new Item(1000, true),
			new Item(2000, true),
			new Item(3000, true),
			new Item(4000, true)
		);

		// when
		final int actual = sut.calculate(items);

		// then
		assertThat(actual).isEqualTo(9000);
	}

}