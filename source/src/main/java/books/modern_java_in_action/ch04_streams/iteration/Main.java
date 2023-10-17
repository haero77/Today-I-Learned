package books.modern_java_in_action.ch04_streams.iteration;

import books.modern_java_in_action.ch04_streams.Dish;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Dish> menu = Dish.Menu.menu;

        List<String> names = new ArrayList<>();
//        Iterator<Dish> iterator = menu.iterator();
//        while (iterator.hasNext()) {
//            Dish dish = iterator.next();
//            names.add(dish.getName());
//        }

//        for (Dish dish : menu) {
//            names.add(dish.getName());
//        }
//
//        List<String> names = menu.stream()
//                .map(Dish::getName)
//                .collect(Collectors.toList());

    }

}
