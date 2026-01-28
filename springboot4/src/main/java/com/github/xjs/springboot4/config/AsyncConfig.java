package com.github.xjs.springboot4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration(proxyBeanMethods = false)
@EnableAsync
public class AsyncConfig {

//    @Bean
//    public TaskExecutor taskExecutor(){
//        return new VirtualThreadTaskExecutor("virtual-");
//    }
}
