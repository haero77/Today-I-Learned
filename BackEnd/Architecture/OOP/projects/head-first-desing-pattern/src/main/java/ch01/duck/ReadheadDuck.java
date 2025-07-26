package ch01.duck;

import ch01.behavior.FlyBehavior;
import ch01.behavior.QuackBehavior;
import ch01.duck.Duck;

public class ReadheadDuck extends Duck {

    public ReadheadDuck(final QuackBehavior quackBehavior, final FlyBehavior flyBehavior) {
        super(quackBehavior, flyBehavior);
    }

    @Override
    void display() {

    }
}
