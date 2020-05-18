package com.github.xjs.aopdemo.springdemo;

import com.github.xjs.aopdemo.service.IService;
import com.github.xjs.aopdemo.service.Service1;
import com.github.xjs.aopdemo.service.Service2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class DemoApp {
    @Bean
    public DemoAdvice demoAdvice(){
        return new DemoAdvice();
    }
    @Bean
    public IService iService(){
        return new Service1();
    }
    @Bean
    public Service2 service2(){
        return new Service2();
    }
}