package com.github.xjs.conditiondemo.demo4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config1 {

    @OnMissingBean(beanType= IService.class)
    @Bean
    public IService service1(){
        return new Service1();
    }

    @OnMissingBean(beanType= IService.class)
    @Bean
    public IService service2(){
        return new Service2();
    }


}
