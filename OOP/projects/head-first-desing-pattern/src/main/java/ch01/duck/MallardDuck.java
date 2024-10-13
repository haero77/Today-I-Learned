package ch01.duck;

import ch01.duck.behavior.FlyWithWings;
import ch01.duck.behavior.Quack;

public class MallardDuck extends Duck {

    public MallardDuck() {
        super(new Quack(), new FlyWithWings());
    }

    @Override
    void display() {
        System.out.println("저는 물오리입니다");
    }
}
