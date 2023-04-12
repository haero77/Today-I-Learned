package old.sample_code.Java.optional.example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalEx {

    public static void main(String[] args) {

        Optional<Person> optionalPerson = findPersonByName("Sophia");

        if (optionalPerson.isPresent()) {
            System.out.println(optionalPerson.get().getAge());
        } else {
            System.out.println(0);
        }

    }

    static Optional<Person> findPersonByName(String name) {
        List<Person> people = Arrays.asList(
                new Person("John", 25),
                new Person("Alice", 27),
                new Person("Dave", 30)
        );

        for (Person person : people) {
            if (person.getName().equals(name)) {
                return Optional.of(person);
            }
        }

        return Optional.empty();
    }
}
