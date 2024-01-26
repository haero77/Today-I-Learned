package modern_java_in_action.ch3_lambda._03_around_pattern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReaderV1 {

	public String processFile() throws IOException {

		String fileName =
			"./data.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			return br.readLine(); // 실제 필요한 작업을 하는 행
		}
	}

}
