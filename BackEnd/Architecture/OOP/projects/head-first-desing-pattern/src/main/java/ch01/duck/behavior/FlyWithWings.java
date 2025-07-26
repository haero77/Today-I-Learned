package ch01.duck.behavior;

import ch01.behavior.FlyBehavior;

public class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("날고 있어요!!");
    }
}
