package com.example.stresstestbasic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping(value = "/hello")
    public String sayHello() {
        return "Hello, World!";
    }

}
