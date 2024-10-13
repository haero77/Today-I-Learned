package ch01.duck;

import ch01.behavior.FlyBehavior;
import ch01.behavior.QuackBehavior;
import ch01.duck.Duck;

public class RubberDuck extends Duck {

    public RubberDuck(final QuackBehavior quackBehavior, final FlyBehavior flyBehavior) {
        super(quackBehavior, flyBehavior);
    }

    @Override
    void display() {

    }
}
