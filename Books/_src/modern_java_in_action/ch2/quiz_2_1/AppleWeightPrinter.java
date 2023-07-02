package Books._src.modern_java_in_action.ch2.quiz_2_1;

import Books._src.modern_java_in_action.ch2.Apple;

public class AppleWeightPrinter implements ApplePrinter{

	@Override
	public String test(Apple apple) {
		return String.valueOf(apple);
	}

}
