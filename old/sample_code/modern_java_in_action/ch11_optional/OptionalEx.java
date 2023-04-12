package old.sample_code.modern_java_in_action.ch11_optional;

import java.util.Optional;

public class OptionalEx {
    public static void main(String[] args) {

    }

    // Optional을 쓰는게 무의미한 수준
//    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
//        if (person.isPresent() && car.isPresent()) {
//            return Optional.of(findCheapestInsurance(person.get(), car.get()));
//        } else {
//            return Optional.empty();
//        }
//    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    private Insurance findCheapestInsurance(Person person, Car car) {
        return new Insurance();
    }
}
