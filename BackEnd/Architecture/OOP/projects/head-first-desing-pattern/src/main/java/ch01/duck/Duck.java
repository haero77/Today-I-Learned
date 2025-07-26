package ch01.duck;

import ch01.behavior.FlyBehavior;
import ch01.behavior.QuackBehavior;

public abstract class Duck {

    private QuackBehavior quackBehavior;
    private FlyBehavior flyBehavior;

    public Duck(final QuackBehavior quackBehavior, final FlyBehavior flyBehavior) {
        this.quackBehavior = quackBehavior;
        this.flyBehavior = flyBehavior;
    }

    abstract void display();

    void performQuack() {
        quackBehavior.quack();
    }

    void performFly() {
        flyBehavior.fly();
    }

    public void setQuackBehavior(final QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void setFlyBehavior(final FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    void swim() {
        System.out.println("모든 오리는 물에 뜹니다. 가짜 오리도 뜨죠.");
    }
}
