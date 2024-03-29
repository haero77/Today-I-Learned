package main.java.books.modern_java_in_action.ch04_streams;

import java.util.Objects;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

import static books.modern_java_in_action.ch04_streams.Dish.Type.*;
import static books.modern_java_in_action.ch04_streams.Dish.Type.FISH;

@Getter
@ToString(of = {"name"})
public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public enum Type {
        MEAT, FISH, OTHER
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dish dish = (Dish) o;
        return vegetarian == dish.vegetarian && calories == dish.calories && Objects.equals(name, dish.name)
                && type == dish.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, vegetarian, calories, type);
    }

    public static class Menu {

        public static List<Dish> menu = Arrays.asList(
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

    }

}
