package Books._src.modern_java_in_action.ch2._02_;

import Books._src.modern_java_in_action.ch2.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate{

	@Override
	public boolean test(Apple apple) {
		return apple.getWeight() > 150;
	}

}
