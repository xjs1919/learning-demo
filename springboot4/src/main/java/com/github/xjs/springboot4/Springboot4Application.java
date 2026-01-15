package com.github.xjs.springboot4;

import com.github.xjs.springboot4.config.BeanRegistrarConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.resilience.annotation.EnableResilientMethods;

@Import(BeanRegistrarConfig.class)
@EnableResilientMethods
@SpringBootApplication
public class Springboot4Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot4Application.class, args);
	}

}
