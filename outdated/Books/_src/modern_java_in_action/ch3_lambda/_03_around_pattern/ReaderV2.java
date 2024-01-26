package modern_java_in_action.ch3_lambda._03_around_pattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReaderV2 {

	public String processFile(BufferedReaderProcessor p) throws IOException {
		String fileName =
			"/Users/preferkim/IdeaProjects/Today-I-Learned/Books/_src/modern_java_in_action/ch3_lambda/_03_around_pattern/data.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			return p.process(br);
		}
	}

	public static void main(String[] args) throws IOException {

		ReaderV2 readerV2 = new ReaderV2();

		String oneLine = readerV2.processFile((BufferedReader br) -> br.readLine());
		System.out.println(oneLine);
		// String oneLine = readerV2.processFile(br -> br.readLine());
		// String oneLine = readerV2.processFile(BufferedReader::readLine);

		String twoLines = readerV2.processFile((BufferedReader br) -> br.readLine() + "\n" + br.readLine());
		System.out.println(twoLines);

/*
		File file = new File(".");
		System.out.println(file.getAbsolutePath());
*/

		BufferedReaderProcessor bufferedReaderProcessor = b -> b.readLine();
	}

}
