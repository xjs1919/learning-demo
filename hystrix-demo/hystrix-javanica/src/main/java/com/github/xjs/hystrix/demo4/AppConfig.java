package com.github.xjs.hystrix.demo4;

import com.github.xjs.hystrix.demo3.UserService2;
import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
    @Bean
    public HystrixCommandAspect hystrixAspect() {
        return new HystrixCommandAspect();
    }

    @Bean
    public UserService userService(){
        return new UserService();
    }

    @Bean
    public UserService2 userService2(){
        return new UserService2();
    }
}
