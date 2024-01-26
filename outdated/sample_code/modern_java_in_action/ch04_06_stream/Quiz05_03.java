package old.sample_code.modern_java_in_action.ch04_06_stream;

public class Quiz05_03 {
    public static void main(String[] args) {
        Menu menu = new Menu();

        Integer count = menu.menu.stream()
                .map(dish -> 1)
                .sorted()
                .reduce(0, (a, b) -> a + b);
    }
}
