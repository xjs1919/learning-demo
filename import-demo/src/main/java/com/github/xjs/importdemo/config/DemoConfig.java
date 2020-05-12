package com.github.xjs.importdemo.config;

import com.github.xjs.importdemo.service.Service2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {
    @Bean
    public Service2 service2(){
        return new Service2();
    }
}
