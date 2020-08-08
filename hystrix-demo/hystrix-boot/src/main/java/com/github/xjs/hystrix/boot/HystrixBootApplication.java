package com.github.xjs.hystrix.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class HystrixBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(HystrixBootApplication.class, args);
	}
}
