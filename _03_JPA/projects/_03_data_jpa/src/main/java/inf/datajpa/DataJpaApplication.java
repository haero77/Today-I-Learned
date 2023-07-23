package inf.datajpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJpaApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		// @CreatedBy, @lastModifiedBy 가 이 메서드를 실행해서 값을 꺼내간다.
		// Security 또는 Session 등에서 Id를 꺼내서 쓰면 된다.
		return () -> Optional.of(UUID.randomUUID().toString());
	}

}
