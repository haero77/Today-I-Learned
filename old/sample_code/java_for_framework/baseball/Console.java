package old.sample_code.java_for_framework.baseball;

import old.sample_code.java_for_framework.baseball.engine.io.Input;
import old.sample_code.java_for_framework.baseball.engine.io.Output;
import old.sample_code.java_for_framework.baseball.engine.model.BallCount;

import java.util.Scanner;

public class Console implements Input, Output {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String input(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public void ballCount(BallCount ballCount) {
        System.out.println(ballCount.getStrike() + " Strikes, " + ballCount.getBall() + " Balls");
    }

    @Override
    public void inputError() {
        System.out.println("입력이 잘못되었습니다.");
    }

    @Override
    public void correct() {
        System.out.println("정답입니다.");
    }
}
