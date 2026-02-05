package com.github.xjs.springboot4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.resilience.annotation.EnableResilientMethods;

@SpringBootApplication
public class Springboot4Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot4Application.class, args);
	}

}
