package old.sample_code.java_for_framework.baseball.engine.model;

public class BallCount {
    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    private int strike;
    private int ball;

    public BallCount(int strike, int ball) {
        this.strike = strike;
        this.ball = ball;
    }
}
