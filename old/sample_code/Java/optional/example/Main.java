package old.sample_code.Java.optional.example;

import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        Person person = findPersonByName("Sophia");

        if (person != null) {
            System.out.println(person.getAge());
        } else {
            System.out.println(0);
        }

    }

    // 일종의 데이터베이스
    static Person findPersonByName(String name) {
        List<Person> people = Arrays.asList(
                new Person("John", 25),
                new Person("Alice", 27),
                new Person("Dave", 30)
        );

        for (Person person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }

        return null;
    }
}
