package com.github.xjs.configdemo.demo2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//这是配置类，这里用@Bean把Service1和Service2注入到容器
@Configuration
@ComponentScan
public class DemoConfiguration {
    @Bean
    public Service1 service1(){
        return new Service1();
    }
    @Bean
    public Service2 service2(){
        //注意这里的构造函数注入的方式，直接调用service1()方法
        return new Service2(service1());
    }
}