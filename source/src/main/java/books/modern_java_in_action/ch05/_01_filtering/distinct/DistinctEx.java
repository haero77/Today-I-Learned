package books.modern_java_in_action.ch05._01_filtering.distinct;

import java.util.Arrays;
import java.util.List;

public class DistinctEx {

    public static void main(String[] args) {
        /**
         * distinct() 를 이용하여 고유요소 필터링 가능
         * 고유 여부는 스트림에서 만든 객체의 hashCode, equals 로 결정
         */
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

}
