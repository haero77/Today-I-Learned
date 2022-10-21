package sample_code.java_for_framework.baseball.engine.io;

import sample_code.java_for_framework.baseball.engine.model.BallCount;

public interface Output {
    void ballCount(BallCount ballCount);

    void inputError();

    void correct();
}
