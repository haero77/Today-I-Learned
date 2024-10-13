package ch01.duck;

import ch01.behavior.FlyRocketPowered;
import org.junit.jupiter.api.Test;

class DuckTest {

    @Test
    void mallardDuck() {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        Duck modelDuck = new ModelDuck();
        modelDuck.performFly();
        modelDuck.setFlyBehavior(new FlyRocketPowered());
        modelDuck.performFly();
    }
}