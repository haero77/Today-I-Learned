package stream.grouping;

import main.stream.CaloricLevel;
import main.stream.Dish;
import main.stream.DishType;
import main.stream.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class NestedGrouping {

	private final List<Dish> menu = Menu.menu;

	@DisplayName("다수준 그룹화")
	@Test
	void nested() {
		Map<DishType, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(
				groupingBy(Dish::getType,
						groupingBy(dish -> {
							if (dish.getCalories() < 400) {
								return CaloricLevel.DIET;
							}
							return CaloricLevel.FAT;
						})
				));

		System.out.println(dishesByTypeCaloricLevel);
	}

}
