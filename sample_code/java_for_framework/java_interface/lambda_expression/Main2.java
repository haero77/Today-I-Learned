package sample_code.java_for_framework.java_interface.lambda_expression;

public class Main2 {
    public static void main(String[] args) {
        new Main2().loop(
                10,
                (number) -> System.out.print(number)
        );
    }

    public void loop(int count, MyConsumer<Integer> consumer) {
        for (int i = 0; i < count; i++) {
            // 무엇인가 count만큼 수행해라!, 무엇을 구체적으로 할지는 호출하는 쪽에 위임
            consumer.accept(i);
        }
    }
}
