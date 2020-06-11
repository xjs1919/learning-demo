package com.github.xjs.conditiondemo.demo2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config1 {

    @Bean
    @Conditional(Condition1.class)
    public String name(){
        return "爪哇优太儿";
    }

    @Bean
    public String username(){
        return "Joshua";
    }
}
