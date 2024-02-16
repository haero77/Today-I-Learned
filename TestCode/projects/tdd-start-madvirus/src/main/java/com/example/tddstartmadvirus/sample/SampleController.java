package com.example.tddstartmadvirus.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}

}
