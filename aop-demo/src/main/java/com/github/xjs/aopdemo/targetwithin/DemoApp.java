package com.github.xjs.aopdemo.targetwithin;

import com.github.xjs.aopdemo.service.IService;
import com.github.xjs.aopdemo.service.Service1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan
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
    public String hello(){
        return "hello";
    }
}