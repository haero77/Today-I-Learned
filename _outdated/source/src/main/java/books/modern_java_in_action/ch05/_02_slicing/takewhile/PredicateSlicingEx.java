package main.java.books.modern_java_in_action.ch05._02_slicing.takewhile;

import static main.java.books.modern_java_in_action.ch04_streams.Dish.Type.FISH;
import static main.java.books.modern_java_in_action.ch04_streams.Dish.Type.MEAT;
import static main.java.books.modern_java_in_action.ch04_streams.Dish.Type.OTHER;
import static java.util.stream.Collectors.toList;

import main.java.books.modern_java_in_action.ch04_streams.Dish;
import java.util.Arrays;
import java.util.List;

public class PredicateSlicingEx {

    public static void main(String[] args) {

        // 아래 요소 중 어떻게 320 칼로리 미만의 요리만 필터링 가능할까?
        List<Dish> specialMenu = Arrays.asList(
                new Dish("seasonal fruit", true, 120, OTHER),
                new Dish("prawns", false, 300, FISH),
                new Dish("prawns", false, 300, FISH),
                new Dish("prawns", false, 300, FISH),
                new Dish("prawns", false, 300, FISH),
                new Dish("rice", true, 350, OTHER),
                new Dish("chicken", false, 400, MEAT),
                new Dish("french fries", true, 530, OTHER)
        );

        // 방법 1. 기초적인 filtering -> 전체 스트림을 반복하면서 각 요소에 프레디케이트를 적용. 요소가 많다면 상당한 차이 발생 가능.
        List<Dish> filteredMenu = specialMenu.stream()
                .filter(dish -> dish.getCalories() < 320)
                .collect(toList());

        // 방법 2. '이미 리스트가 칼로리 기준 오름차순으로 정렬 되어있다'는 사실을 활용하자 -> 'takeWhile'
        List<Dish> slicedMenu1 = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .distinct()
                .collect(toList());// [seasonal fruit, prawns]

        System.out.println(slicedMenu1);
    }

}
