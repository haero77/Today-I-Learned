package old.sample_code.java_for_framework.baseball.engine.io;

import old.sample_code.java_for_framework.baseball.engine.model.BallCount;

public interface Output {
    void ballCount(BallCount ballCount);

    void inputError();

    void correct();
}
