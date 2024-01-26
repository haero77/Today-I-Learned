package hello.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdvancedAopApplication {

	public static void main(String[] args) {
		// 웹(톰캣)이 없으므로 실행되고 바로 종료.
		SpringApplication.run(AdvancedAopApplication.class, args);
	}

}
