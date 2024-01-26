package modern_java_in_action.ch2.quiz_2_1;

import modern_java_in_action.ch2.Apple;

public class AppleWeightLightnessJudgePrinter implements ApplePrinter{

	@Override
	public String test(Apple apple) {
		if (apple.getWeight() > 150) {
			return "Heavy";
		}
		return "Light";
	}

}
