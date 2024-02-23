package stream.filtering;

import main.stream.Dish;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stream.StreamTestSupport;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FilteringTest extends StreamTestSupport {

	@DisplayName("프레디케이트로 필터링")
	@Test
	void filter() {
		List<Dish> vegetarianMenu = menu.stream()
				.filter(Dish::isVegetarian)
				.collect(toList());

		System.out.println(vegetarianMenu);
	}

	@DisplayName("고유 요소 필터링")
	@Test
	void distinct() {
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream()
				.filter(i -> i % 2 == 0)
				.distinct()
				.forEach(System.out::println);
	}

}
