package com.github.xjs.configdemo.demo1;

import org.springframework.context.annotation.Bean;

public interface IService {
    /**接口里面定义了@Bean*/
    @Bean
    default IService defaultService(){
        return new DefaultService();
    }
}