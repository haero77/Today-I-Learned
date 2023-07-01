package _01_Java._src.modern_java_in_action.ch2._02_;

import static _01_Java._src.modern_java_in_action.ch2.Color.*;

import _01_Java._src.modern_java_in_action.ch2.Apple;
import _01_Java._src.modern_java_in_action.ch2.Color;

public class AppleGreenColorPredicate implements ApplePredicate{

	@Override
	public boolean test(Apple apple) {
		return GREEN.equals(apple.getColor());
	}

}
