package sample_code.java_for_framework.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new LinkedList<>();

        list1.addAll(Arrays.asList(1, 2, 3));

        list1.forEach(System.out::println);
    }
}
