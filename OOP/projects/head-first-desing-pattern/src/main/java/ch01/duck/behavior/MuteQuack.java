package ch01.duck.behavior;

import ch01.behavior.QuackBehavior;

public class MuteQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("<< 조용~ >>");
    }
}
