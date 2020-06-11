package com.github.xjs.conditiondemo.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config1 {
    @Bean
    public String name(){
        return "爪哇优太儿";
    }
}
