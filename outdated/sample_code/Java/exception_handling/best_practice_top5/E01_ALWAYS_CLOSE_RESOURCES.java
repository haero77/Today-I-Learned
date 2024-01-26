package sample_code.Java.exception_handling.best_practice_top5;

import java.io.*;

/**
 * Java Exception Handling - 5 Best Practices That You Should Know!
 * https://www.youtube.com/watch?v=ujYW4Q9ZG-4
 */

/**
 * try-with-resources를 사용하면 리소스에 해제에 대한 여부를 신경쓰지 않을 수 있다. (자동으로 해주기 때문)
 */

public class E01_ALWAYS_CLOSE_RESOURCES {
    public static void main(String[] args) {
        // case: stream is still remain opened
        try {
            InputStream stream = new FileInputStream(new File("filePath"));

            // Reading the data, processing, etc.
            stream.close(); // 위의 'Reading Data...' 작업을 하는동안 exception이 발생하면 stream은 여전히 open 상태로 남는다.

        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
//            stream.close(); // stream 변수를 try-catch 블럭 밖에 작성해야하므로 여전히 좋지 않다.
        }

        // try-with-resources 가 우아한 해결책이 된다.
        try (InputStream stream = new FileInputStream(new File("filePath"))) {

            // Reading the data, processing, etc

            // try-with-resources를 사용하여 stream을 닫는 것에 대한 신경을 쓸 필요가 없어졌다.

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
