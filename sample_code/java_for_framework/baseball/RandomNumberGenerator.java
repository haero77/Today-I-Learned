package sample_code.java_for_framework.baseball;

import sample_code.java_for_framework.baseball.engine.io.NumberGenerator;
import sample_code.java_for_framework.baseball.engine.model.Numbers;

import java.util.Arrays;
import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public Numbers generate(int count) {
        Numbers randomNumbers = new Numbers(
                new Random().ints(1, 9 + 1)
                        .distinct()
                        .limit(count)
                        .boxed()
                        .toArray(Integer[]::new)
        );

        System.out.print("cheat: ");
        Arrays.asList(randomNumbers.getNumbers()).forEach(System.out::print);
        System.out.println();

        return randomNumbers;
    }
}
