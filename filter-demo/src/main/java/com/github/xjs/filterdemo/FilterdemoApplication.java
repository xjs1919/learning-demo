package com.github.xjs.filterdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class FilterdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilterdemoApplication.class, args);
	}

}
