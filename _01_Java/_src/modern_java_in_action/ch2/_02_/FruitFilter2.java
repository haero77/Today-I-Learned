package _01_Java._src.modern_java_in_action.ch2._02_;

import java.util.ArrayList;
import java.util.List;

import _01_Java._src.modern_java_in_action.ch2.Apple;

public class FruitFilter2 {

	// 동작 파라미터화
	public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
		List<Apple> result = new ArrayList<>();

		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}

		return result;
	}

}
