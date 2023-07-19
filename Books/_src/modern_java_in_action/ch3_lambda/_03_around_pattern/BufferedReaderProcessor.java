package Books._src.modern_java_in_action.ch3_lambda._03_around_pattern;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedReaderProcessor {

	String process(BufferedReader b) throws IOException;

}
