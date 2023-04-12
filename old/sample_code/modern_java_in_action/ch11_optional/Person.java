package old.sample_code.modern_java_in_action.ch11_optional;

import java.util.Optional;

public class Person {
    private Optional<Car> car; // 사람이 차를 소유하지 않을 수도 있다.

    public Optional<Car> getCar() {
        return car;
    }
}
