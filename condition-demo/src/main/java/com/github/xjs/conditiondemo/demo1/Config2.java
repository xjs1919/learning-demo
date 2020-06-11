package com.github.xjs.conditiondemo.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Conditional(Condition1.class)
public class Config2 {

    @Bean
    public String username(){
        return "Joshua";
    }

}
