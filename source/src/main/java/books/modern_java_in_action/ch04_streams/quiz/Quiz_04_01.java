package books.modern_java_in_action.ch04_streams.quiz;

import books.modern_java_in_action.ch04_streams.Dish;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Quiz_04_01 {

    public static void main(String[] args) {

        List<Dish> menu = Dish.Menu.menu;

        List<String> highCaloricDishes = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Dish dish = iterator.next();
            if (dish.getCalories() > 300) {
                highCaloricDishes.add(dish.getName());
            }
        }

    }

}
