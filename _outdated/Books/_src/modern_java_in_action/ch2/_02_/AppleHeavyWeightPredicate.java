package modern_java_in_action.ch2._02_;

import modern_java_in_action.ch2.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate{

	@Override
	public boolean test(Apple apple) {
		return apple.getWeight() > 150;
	}

}
