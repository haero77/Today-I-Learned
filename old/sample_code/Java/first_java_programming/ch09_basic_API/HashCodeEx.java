package old.sample_code.Java.first_java_programming.ch09_basic_API;

import java.util.Arrays;
import java.util.List;

public class HashCodeEx {
    public static void main(String[] args) {
        List<Object> objects = Arrays.asList(
                new Object(),
                new Object(),
                new Object()
        );

        // 서로 다른 인스턴스는 해시 코드 값이 같을 수 없다.
        objects.forEach(object -> System.out.println(object.hashCode()));
        /* 실행 결과
            1435804085
            1784662007
            997110508
        */
    }
}
