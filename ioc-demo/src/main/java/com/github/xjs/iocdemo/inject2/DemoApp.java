package com.github.xjs.iocdemo.inject2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class DemoApp {

    @Bean
    @MyQualifier
    public IService service1(){
        return new Service1();
    }

    @Bean
    public IService service2(){
        return new Service2();
    }


    @Bean
    public Service3 service3(@MyQualifier IService service){
        return new Service3(service);
    }

}
