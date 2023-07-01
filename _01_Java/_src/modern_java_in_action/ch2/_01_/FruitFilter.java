package _01_Java._src.modern_java_in_action.ch2._01_;

import java.util.ArrayList;
import java.util.List;

import _01_Java._src.modern_java_in_action.ch2.Apple;
import _01_Java._src.modern_java_in_action.ch2.Color;

public class FruitFilter {

	// GREEN 사과 필터링
	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();

		for (Apple apple : inventory) {
			if (Color.GREEN.equals(apple.getColor())) {
				result.add(apple);
			}
		}

		return result;
	}

	public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
		List<Apple> result = new ArrayList<>();

		for (Apple apple : inventory) {
			if (apple.getColor().equals(color)) {
				result.add(apple);
			}
		}

		return result;
	}

	// DRY(don't repeat yourself) 위배
	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();

		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}

		return result;
	}

	// 플래그를 사용한 방법
	public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
		List<Apple> result = new ArrayList<>();

		for (Apple apple : inventory) {
			if ((flag && apple.getColor().equals(color)) ||
				(!flag && apple.getWeight() > weight)) {
				result.add(apple);
			}
		}

		return result;
	}
}
