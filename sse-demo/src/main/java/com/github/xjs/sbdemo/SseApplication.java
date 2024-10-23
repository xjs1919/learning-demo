package com.github.xjs.sbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SseApplication.class, args);
	}

}
