package com.example.javatraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JavaTrainingApplication {
	public static void main(String[] args) {
		SpringApplication.run(JavaTrainingApplication.class, args);
	}

	@GetMapping("/")
	public String HelloWorld(){
		return "Hello World João";
	}
}
