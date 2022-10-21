package sample_code.java_for_framework.baseball;

import sample_code.java_for_framework.baseball.engine.io.NumberGenerator;

public class Application {
    public static void main(String[] args) {
        NumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        Console console = new Console();

        new Baseball(randomNumberGenerator, console, console).run();
    }
}
