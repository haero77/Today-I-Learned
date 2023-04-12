package sample_code.Java.exception_handling.best_practice_top5;

/**
 * Throwable은 모든 Exception의 Base Class 임과 동시에,
 * JVM specific errors 의 Base Class 이다.
 * 따라서 외부에서 발생하는 에외에 대한 예외처리를 애플리케이션 내부에서 하는 것은 말이 되지 않기 때문에,
 * Throwable은 catch하지 말아라.
*/

public class E03_DONT_CATCH_THROWABLE {
    public static void main(String[] args) {

    }
}
