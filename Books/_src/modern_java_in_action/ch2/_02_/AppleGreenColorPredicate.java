package Books._src.modern_java_in_action.ch2._02_;

import static Books._src.modern_java_in_action.ch2.Color.*;

import Books._src.modern_java_in_action.ch2.Apple;

public class AppleGreenColorPredicate implements ApplePredicate{

	@Override
	public boolean test(Apple apple) {
		return GREEN.equals(apple.getColor());
	}

}
