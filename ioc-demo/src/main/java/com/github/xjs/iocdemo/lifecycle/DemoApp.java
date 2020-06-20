package com.github.xjs.iocdemo.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoApp {

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public Service1 service1(){
        return new Service1();
    }
}
