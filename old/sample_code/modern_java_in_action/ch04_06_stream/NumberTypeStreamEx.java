package old.sample_code.modern_java_in_action.ch04_06_stream;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumberTypeStreamEx {
    public static void main(String[] args) {
        List<Dish> menu = new Menu().menu;

        /* 5.7.1  기본형 특화 스트림 */
        // 박싱비용이 숨어있다.
        int calories1 = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum); // Integer를 기본형으로 언박싱 해야한다.

        // 숫자 스트림으로 매핑
        int calories2 = menu.stream() // Stream<Dish>
                .mapToInt(Dish::getCalories) // IntStream. Stream<Integer>이 아니다.
                .sum();

        // 객체 스트림으로 복원
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories); // 스트림을 숫자스트림으로 변환
        Stream<Integer> stream = intStream.boxed(); // 숫자스트림을 스트림으로 변환

        // 기본값 : OptionalInt
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max = maxCalories.orElse(1); // 최댓값이 없는 상황에 사용할 기본값을 명시적으로 정의

        /* 5.7.2 숫자 범위 */
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(number -> number % 2 == 0);
        System.out.println("evenNumbers.count() = " + evenNumbers.count());

        /* 숫자 스트림 활용 : 피타고라스 수 */
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
    }
}
