package com.github.xjs.configdemo.demo3;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

//这是@Configuration配置类
@Configuration
@ComponentScan
public class DemoConfiguration {
    /**@Bean返回了一个BeanFactoryPostProcessor*/
    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor(){
        return new DemoBeanFactoryPostProcessor();
    }
    /**Configuration类定义了@PostConstruct*/
    @PostConstruct
    public void postConstruct(){
        System.out.println(">>>>>>>DemoConfiguration#postConstruct");
    }
}
