package old.sample_code.java_for_framework.baseball.engine.model;

import java.util.function.BiConsumer;

public class Numbers {
    private final Integer[] numbers;

    public Numbers(Integer[] numbers) {
        this.numbers = numbers;
    }

    public Integer[] getNumbers() {
        return numbers;
    }

    public void indexedForEach(BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < numbers.length; i++) {
            consumer.accept(numbers[i], i);
        }
    }
}
