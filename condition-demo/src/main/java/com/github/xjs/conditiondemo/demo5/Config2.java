package com.github.xjs.conditiondemo.demo5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(Integer.MAX_VALUE)
@OnBean(beanType= Service1.class)
public class Config2 {
    @Bean
    public String name() {
        return "爪哇优太儿";
    }
}
