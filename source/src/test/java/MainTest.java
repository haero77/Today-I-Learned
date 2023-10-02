import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    @DisplayName("sample test case")
    @Test
    void sampleTest() {
        String sample = "sample";
        assertThat(sample).isEqualTo("sample");
    }


}