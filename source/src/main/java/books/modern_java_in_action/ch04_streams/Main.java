package books.modern_java_in_action.ch04_streams;

import java.util.Arrays;
import java.util.List;

import static books.modern_java_in_action.ch04_streams.Dish.Type.*;

public class Main {

    public List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, MEAT),
            new Dish("beef", false, 700, MEAT),
            new Dish("chicken", false, 400, MEAT),
            new Dish("french fries", true, 530, OTHER),
            new Dish("rice", true, 350, OTHER),
            new Dish("season fruit", true, 120, OTHER),
            new Dish("pizza", true, 550, OTHER),
            new Dish("prawns", false, 300, FISH),
            new Dish("salmon", false, 450, FISH)
    );

    public static void main(String[] args) {
        System.out.println("something");
    }

}
