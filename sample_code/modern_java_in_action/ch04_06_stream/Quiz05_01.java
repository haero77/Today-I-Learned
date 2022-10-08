package sample_code.modern_java_in_action.ch04_06_stream;

import java.util.List;
import java.util.stream.Collectors;

public class Quiz05_01 {
    // 스트림을 이용하여 처음 등장하는 두 고기 요리를 필터링한다.

    public static void main(String[] args) {
        List<Dish> firstTwoBeefDishes = new Menu().menu.stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(Collectors.toList());

        firstTwoBeefDishes.forEach(System.out::println);
    }
}
