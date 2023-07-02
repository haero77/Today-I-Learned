package Books._src.modern_java_in_action.ch2._02_;

import static Books._src.modern_java_in_action.ch2.Color.*;

import Books._src.modern_java_in_action.ch2.Apple;

public class AppleRedAndHeavyPredicate implements ApplePredicate{

	@Override
	public boolean test(Apple apple) {
		return RED.equals(apple.getColor()) &&
			apple.getWeight() > 150;
	}

}
