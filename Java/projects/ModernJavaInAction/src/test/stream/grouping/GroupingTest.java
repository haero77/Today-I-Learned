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

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

public class GroupingTest {

	List<Dish> dishes = Menu.menu;

	@DisplayName("grouping by field - type")
	@Test
	void asd() {
		Map<DishType, List<Dish>> dishesByType = this.dishes.stream()
				.collect(groupingBy(dish -> dish.getType()));

		System.out.println("something");
	}

	@DisplayName("그룹화 2")
	@Test
	void grouping2() {
		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = this.dishes.stream()
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
		// 문제: 500 칼로리를 넘기는 FISH 가 없으므로, 결과 맵에서 해당 키(FISH) 자체가 사라진다.ff
		Map<DishType, List<Dish>> caloricDishesByType = dishes.stream()
				.filter(dish -> dish.getCalories() > 500)
				.collect(groupingBy(Dish::getType));

		System.out.println(caloricDishesByType);

		// groupingBy의 팩토리 메서드를 오버로드해 문제를 해결
		Map<DishType, List<Dish>> dishes2 = dishes.stream()
				.collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));

		System.out.println(dishes2);

		// 맵핑
		Map<DishType, List<String>> dishNamesByType = dishes.stream()
				.collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));

		System.out.println(dishNamesByType);
	}
	
	
	@DisplayName("flatMapping")
	@Test
	void flatMapping() {
		Map<String, List<String>> dishTags = new HashMap<>();
		dishTags.put("pork", asList("greasy", "salty"));
		dishTags.put("beef", asList("fried", "crisp"));
	}

}
