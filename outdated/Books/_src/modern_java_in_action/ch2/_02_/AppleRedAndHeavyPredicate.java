package modern_java_in_action.ch2._02_;

import static modern_java_in_action.ch2.Color.*;

import modern_java_in_action.ch2.Apple;

public class AppleRedAndHeavyPredicate implements ApplePredicate{

	@Override
	public boolean test(Apple apple) {
		return RED.equals(apple.getColor()) &&
			apple.getWeight() > 150;
	}

}
