package old.sample_code.java_for_framework.baseball;

import old.sample_code.java_for_framework.baseball.engine.io.Input;
import old.sample_code.java_for_framework.baseball.engine.io.NumberGenerator;
import old.sample_code.java_for_framework.baseball.engine.io.Output;
import old.sample_code.java_for_framework.baseball.engine.model.BallCount;
import old.sample_code.java_for_framework.baseball.engine.model.Numbers;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Baseball implements Runnable{
    private final int COUNT_OF_NUMBERS = 3;

    private NumberGenerator generator;
    private Input input;
    private Output output;

    public Baseball(NumberGenerator generator, Input input, Output output) {
        this.generator = generator;
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        Numbers answerNumbers = generator.generate(COUNT_OF_NUMBERS);

        while (true) {
            String inputString = input.input("숫자를 맞춰보세요 : ");
            Optional<Numbers> inputNumbers = parse(inputString);
            if (inputNumbers.isEmpty()) {
                output.inputError();
                continue;
            }

            BallCount ballCount = ballCount(answerNumbers, inputNumbers.get());
            output.ballCount(ballCount);

            if (ballCount.getStrike() == COUNT_OF_NUMBERS) {
                output.correct();
                break;
            }
        }
    }

    private BallCount ballCount(Numbers answerNumbers, Numbers inputNumbers) {
        AtomicInteger strike = new AtomicInteger();
        AtomicInteger ball = new AtomicInteger();

        answerNumbers.indexedForEach((eachAnswerNumber, i) -> {
            inputNumbers.indexedForEach((eachInputNumber, j) -> {
                if (!eachAnswerNumber.equals(eachInputNumber)) return;
                if (i.equals(j)) {
                    strike.incrementAndGet();
                    return;
                }
                ball.incrementAndGet();
            });
        });

        return new BallCount(strike.get(), ball.get());
    }

    private Optional<Numbers> parse(String inputString) {
        if (inputString.length() != COUNT_OF_NUMBERS) return Optional.empty();

        long count = inputString.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .filter(i -> i > 0)
                .distinct()
                .count();
        if (count != COUNT_OF_NUMBERS) return Optional.empty();

        return Optional.of(
                new Numbers(
                        inputString.chars()
                                .map(Character::getNumericValue)
                                .boxed()
                                .toArray(Integer[]::new)
                )
        );
    }
}
