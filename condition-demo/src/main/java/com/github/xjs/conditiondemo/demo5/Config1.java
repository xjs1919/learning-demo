package com.github.xjs.conditiondemo.demo5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(Integer.MAX_VALUE-1)
public class Config1 {

    @Bean
    public Service1 service1(){
        return new Service1();
    }

}
