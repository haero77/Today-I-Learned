package sample_code.modern_java_in_action.ch11_optional;

import java.util.Optional;

public class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
