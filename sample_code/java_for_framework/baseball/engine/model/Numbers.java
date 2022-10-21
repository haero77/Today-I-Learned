package sample_code.java_for_framework.baseball.engine.model;

import java.util.function.BiConsumer;

public class Numbers {
    private Integer[] numbers;

    public Numbers(Integer[] numbers) {
        this.numbers = numbers;
    }

    public void forEach(BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < numbers.length; i++) {
            consumer.accept(numbers[i], i);
        }
    }
}
