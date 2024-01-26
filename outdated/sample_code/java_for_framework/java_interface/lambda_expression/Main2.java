package old.sample_code.java_for_framework.java_interface.lambda_expression;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main2 {
    public static void main(String[] args) {
        new Main2().loop(
                10,
                (number) -> System.out.print(number)
        );

        new Main2().filterNumbers(
                100,
                number -> number % 3 == 0,
                System.out::println
        );
    }

    public void loop(int count, MyConsumer<Integer> consumer) {
        for (int i = 0; i < count; i++) {
            // 무엇인가 count만큼 수행해라!, 무엇을 구체적으로 할지는 호출하는 쪽에 위임
            consumer.accept(i);
        }
    }

    void filterNumbers(int max, Predicate<Integer> p, Consumer<Integer> c) {
        for (int i = 1; i <= max; i++) {
            if (p.test(i)) {
                c.accept(i);
            }
        }
    }
}
