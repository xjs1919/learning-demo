package com.xjs1919.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ShardDataSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardDataSourceApplication.class, args);
	}

}
