package books.modern_java_in_action.ch04_streams.quiz;

import books.modern_java_in_action.ch04_streams.Dish;

import java.util.List;
import java.util.stream.Collectors;

public class Answer_04_01 {

    public static void main(String[] args) {

        List<Dish> menu = Dish.Menu.menu;

        List<Dish> highCaloricDishes = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .collect(Collectors.toList());

        highCaloricDishes.forEach(dish -> System.out.println(dish.toString()));

    }

}
