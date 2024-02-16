package com.example.tddstartmadvirus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestTddStartMadvirusApplication {

	public static void main(String[] args) {
		SpringApplication.from(TddStartMadvirusApplication::main).with(TestTddStartMadvirusApplication.class).run(args);
	}

}
