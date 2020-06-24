package com.github.xjs.attarget.demo3;

import com.github.xjs.attarget.demo2.Service2;
import com.github.xjs.attarget.demo2.Service3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
public class Config3 {

    @Bean
    public Advice3 advice3(){
        return new Advice3();
    }

    @Bean
    public Father father(){
        return new Father();
    }

    @Bean
    public Son son(){
        return new Son();
    }

}