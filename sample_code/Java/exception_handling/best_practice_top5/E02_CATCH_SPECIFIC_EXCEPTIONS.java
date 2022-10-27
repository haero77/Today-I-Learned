package sample_code.Java.exception_handling.best_practice_top5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 더 specific 한 exception을 catch 하면,
 * 예외가 발생한 원인에 대해 더 자세히 알 수 있다.
 */

public class E02_CATCH_SPECIFIC_EXCEPTIONS {
    public static void main(String[] args) {
        // NOT SPECIFIC EXCEPTION
        try {
            InputStream stream = new FileInputStream(new File("filePath"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // SPECIFIC EXCEPTION
        try {
            InputStream stream = new FileInputStream(new File("filePath"));
        } catch (FileNotFoundException e) { // `FileNotFoundException` is more specific than `Exception`
            e.printStackTrace();
        }
    }
}
