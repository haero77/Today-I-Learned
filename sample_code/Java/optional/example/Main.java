package sample_code.Java.optional.example;

import java.util.Arrays;
import java.util.List;

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

public class Main {
    public static void main(String[] args) {

    }

    static Person findPerson(String name) {
        List<Person> people  =Arrays.asList(
                new Person("Kim"),
                new Person("Lee"),
                new Person("Park")
        );
    }
}
