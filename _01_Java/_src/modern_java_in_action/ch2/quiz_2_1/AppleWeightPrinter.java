package _01_Java._src.modern_java_in_action.ch2.quiz_2_1;

import _01_Java._src.modern_java_in_action.ch2.Apple;

public class AppleWeightPrinter implements ApplePrinter{

	@Override
	public String test(Apple apple) {
		return String.valueOf(apple);
	}

}
