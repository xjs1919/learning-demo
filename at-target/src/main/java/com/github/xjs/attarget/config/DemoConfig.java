package com.github.xjs.attarget.config;

import com.github.xjs.attarget.aop.DemoAdvice;
import com.github.xjs.attarget.service.Service1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {

    @Bean
    public DemoAdvice demoAdvice(){
        return new DemoAdvice();
    }

    @Bean
    public Service1 service(){
        return new Service1();
    }
}