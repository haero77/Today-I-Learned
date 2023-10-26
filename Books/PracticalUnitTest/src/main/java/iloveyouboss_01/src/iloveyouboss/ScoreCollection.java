package iloveyouboss_01.src.iloveyouboss;

import java.util.ArrayList;
import java.util.List;

public class ScoreCollection {

    private List<Scoreable> scores = new ArrayList<>();

    public void add(Scoreable scoreable) {
        this.scores.add(scoreable);
    }

    public int arithmeticMean() {
        int total = this.scores.stream()
                .mapToInt(Scoreable::getScore)
                .sum();

        return total / this.scores.size();
    }

}
