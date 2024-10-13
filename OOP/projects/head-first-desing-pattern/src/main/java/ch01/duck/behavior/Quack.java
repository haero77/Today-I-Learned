package ch01.duck.behavior;

import ch01.behavior.QuackBehavior;

public class Quack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("꽥" +
                "");
    }
}
