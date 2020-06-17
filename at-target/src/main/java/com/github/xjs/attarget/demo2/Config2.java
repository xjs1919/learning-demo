package com.github.xjs.attarget.demo2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
public class Config2 {

    @Bean
    public Advice2 advice2(){
        return new Advice2();
    }

    @Bean
    public Service2 service2(){
        return new Service2();
    }

    @Bean
    public Service3 service3(){
        return new Service3();
    }
}