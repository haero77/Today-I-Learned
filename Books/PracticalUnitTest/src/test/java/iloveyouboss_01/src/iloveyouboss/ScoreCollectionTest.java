package iloveyouboss_01.src.iloveyouboss;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreCollectionTest {

    @DisplayName("두 숫자에 대해 산술 평균을 구할 수 있다.")
    @Test
    void answersArithmeticMeanOfTwoNumbers() {
        // given
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        // when
        int actualResult = collection.arithmeticMean();

        // then
        assertThat(actualResult).isEqualTo(6);
    }

}