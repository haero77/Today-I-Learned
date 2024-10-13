package ch01.duck;

import ch01.duck.behavior.FlyNoWay;
import ch01.duck.behavior.Quack;

public class ModelDuck extends Duck {

    public ModelDuck() {
        super(new Quack(), new FlyNoWay());
    }

    @Override
    void display() {
        System.out.println("저는 모형 오리 입니다.");
    }
}
