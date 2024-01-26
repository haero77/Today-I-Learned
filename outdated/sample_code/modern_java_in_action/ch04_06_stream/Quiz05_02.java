package old.sample_code.modern_java_in_action.ch04_06_stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Quiz05_02 {
    /*
        Q1. 두 개의 숫자 리스트가 잇을 때 모든 숫자 쌍의 리스트를 반환하시오.
        예를 들어 두 개의 리스트 [1, 2, 3]과 [3, 4]가 주어지면 [(1, 3), (1, 4), ... , (3, 4)] 를 반환해야 한다.
    */

    /*
        Q2. 이전 예제에서 합이 3으로 나누어떨어지는 쌍만 반환하려면 어떻게 해야할까?
        예를 들어 (2, 4), (3, 3)을 반환해야 한다.
     */

    static List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    static List<Integer> numbers2 = Arrays.asList(3, 4);

    public static void main(String[] args) {
        // Answer 1.
        // flatMap을 사용하지 않은 경우
        List<Stream<int[]>> pairs1_1 = numbers1.stream() // Stream<Integer>
                .map(i -> numbers2.stream()
                        .map(j -> new int[]{i, j})
                ) // Stream<Stream<int[]>>
                .collect(Collectors.toList()); // List<Stream<int[]>>

        for (Stream<int[]> arr : pairs1_1) {
            arr.forEach((elem) -> System.out.println(elem[0] + ", " + elem[1]));
        }
        System.out.println();

        // flatMap 사용한 경우
        List<int[]> pairs1_2 = numbers1.stream() // Stream<Integer>
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i, j})
                ) // Stream<int[]>
                .collect(Collectors.toList()); // List<int[]>

        pairs1_2.forEach(each -> System.out.printf("(%d, %d)\n", each[0], each[1]));
        System.out.println();

        // Answer 2.
        List<int[]> pairs2 = numbers1.stream() // Stream<Integer>
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 ==0)
                        .map(j -> new int[] {i, j})
                ) // Stream<int[]>
                .collect(Collectors.toList());

        pairs2.forEach(each -> System.out.printf("(%d, %d)\n", each[0], each[1]));
        System.out.println();
    }
}
