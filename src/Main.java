package src;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

	public static void main(String[] args) {

		String now = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(LocalDateTime.now());

		System.out.println(now);


	}
}
