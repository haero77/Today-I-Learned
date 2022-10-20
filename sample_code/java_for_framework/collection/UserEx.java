package sample_code.java_for_framework.collection;

import java.util.Arrays;

public class UserEx {
    public static void main(String[] args) {
        new MyCollection<User>(
                Arrays.asList(
                        new User(18, "AAA"),
                        new User(19, "BBB"),
                        new User(20, "CCC"),
                        new User(21, "DDD"),
                        new User(22, "EEE")
                )
        )
                .filter(user -> user.getAge() >= 19)
                .forEach(System.out::println);
    }
}
