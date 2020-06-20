package com.github.xjs.iocdemo.inject3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class DemoApp {

    @Bean
    public StringService stringService(){
        return new StringService();
    }

    @Bean
    public IntegerService integerService(){
        return new IntegerService();
    }

}
