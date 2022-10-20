package sample_code.java_for_framework.collection;

import java.util.Arrays;

public class MyCollectionEx {
    public static void main(String[] args) {
        // method chaining
        int size = new MyCollection<>(Arrays.asList("A", "BB", "CCC", "DDDD", "EEEEE"))
                .map(String::length)
                .filter(i -> i % 2 == 0)
                .size();

        System.out.println(size);
    }
}
