package modern_java_in_action.ch2.quiz_2_1;

import java.util.List;

import modern_java_in_action.ch2.Apple;

public class Quiz {

	public static void prettyPrintApple(List<Apple> inventory, ApplePrinter printer) {
		for (Apple apple : inventory) {
			String output = printer.test(apple);
			System.out.println(output);
		}
	}

}