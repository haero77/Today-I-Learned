package iloveyouboss_01.src.iloveyouboss;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
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

    // 셀프 테스트 추가
    @DisplayName("아무런 숫자가 주어지지 않았을 때 산술 평균을 구할 시 예외가 발생한다.")
    @Test
    void throws_exception_when_no_number_given() {
        // given
        ScoreCollection collection = new ScoreCollection();

        // when & then
        assertThatThrownBy(() -> collection.arithmeticMean())
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("/ by zero");
    }

}