package cafekiosk.sample;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SampleTest {

	@DisplayName("sample - test!")
	@Test
	void test() {
		assertThat(true).isTrue();
	}

}
