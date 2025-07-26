package ch01.duck;

import ch01.behavior.QuackBehavior;

public class Squeak implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("삑삑");
    }
}
