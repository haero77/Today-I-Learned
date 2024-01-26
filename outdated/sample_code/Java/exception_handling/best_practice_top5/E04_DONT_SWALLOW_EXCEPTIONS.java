package sample_code.Java.exception_handling.best_practice_top5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Exception을 꿀꺽 삼키지 말아라
 * catch 블럭으로 Exception을 잡았다면, 분명 해당 Exception을 handling 하기 위해서 그랬을 것이다.
 * 즉 catch한 Exception에 대해 아무런 행동을 하지 않는다는 것은 context를 잃어버리게 되는 것이다.
 */

public class E04_DONT_SWALLOW_EXCEPTIONS {
    public static void main(String[] args) {
        try {

            InputStream stream = new FileInputStream(new File("filePath"));

        } catch (FileNotFoundException e) {

            return null; // SWALLOWING EXCEPTION!!!

        }
    }
}
