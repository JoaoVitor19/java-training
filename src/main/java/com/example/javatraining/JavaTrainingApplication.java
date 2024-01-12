package com.example.javatraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "com.example")
public class JavaTrainingApplication {
	public static void main(String[] args) {
		SpringApplication.run(JavaTrainingApplication.class, args);
	}
}
