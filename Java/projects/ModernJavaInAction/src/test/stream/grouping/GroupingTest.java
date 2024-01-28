package stream.grouping;

import main.stream.CaloricLevel;
import main.stream.Dish;
import main.stream.DishType;
import main.stream.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

public class GroupingTest {

	List<Dish> menu = Menu.menu;

	@DisplayName("grouping by field - type")
	@Test
	void grouping_by_field() {
		Map<DishType, List<Dish>> dishesByType = this.menu.stream()
				.collect(groupingBy(dish -> dish.getType()));

		System.out.println("something");
	}

	@DisplayName("grouping by field - counting")
	@Test
	void count() {
		Map<DishType, Long> dishCountByType = menu.stream()
				.collect(groupingBy(Dish::getType, counting()));

		System.out.println(dishCountByType);
	}

	@DisplayName("그룹화 2")
	@Test
	void grouping2() {
		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = this.menu.stream()
				.collect(groupingBy(dish -> {
					if (dish.getCalories() < 400) {
						return CaloricLevel.DIET;
					}
					return CaloricLevel.FAT;
				}));
	}

	@DisplayName("500 칼로리가 넘는 요소 필터링")
	@Test
	void filter() {
		// 문제: 500 칼로리를 넘기는 FISH 가 없으므로, 결과 맵에서 해당 키(FISH) 자체가 사라진다.
		Map<DishType, List<Dish>> caloricDishesByType = menu.stream()
				.filter(dish -> dish.getCalories() > 500)
				.collect(groupingBy(Dish::getType));

		System.out.println(caloricDishesByType);

		// groupingBy의 팩토리 메서드를 오버로드해 문제를 해결
		Map<DishType, List<Dish>> dishes2 = menu.stream()
				.collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));

		System.out.println(dishes2);

		// 맵핑
		Map<DishType, List<String>> dishNamesByType = menu.stream()
				.collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));

		System.out.println(dishNamesByType);
	}


	@DisplayName("flatMapping")
	@Test
	void flatMapping() {
		Map<String, List<String>> dishTags = new HashMap<>();
		dishTags.put("pork", asList("greasy", "salty"));
		dishTags.put("beef", asList("salty", "roasted"));
		dishTags.put("chicken", asList("fried", "crisp"));
		dishTags.put("french fries", asList("greasy", "fried"));
		dishTags.put("rice", asList("light", "natural"));
		dishTags.put("season fruit", asList("fresh", "natural"));
		dishTags.put("pizza", asList("tasty", "salty"));
		dishTags.put("prawns", asList("tasty", "roasted"));
		dishTags.put("salmon", asList("delicious", "fresh"));

		Map<DishType, Set<String>> dishNamesByType = menu.stream().collect(groupingBy(
				Dish::getType,
				Collectors.flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())
		));

		Map<DishType, Set<Stream<String>>> collect = menu.stream().collect(groupingBy(
				Dish::getType,
				mapping(dish -> dishTags.get(dish.getName()).stream(), toSet())
		));

		System.out.println(dishNamesByType);
	}

}
