package com.github.xjs.springboot4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration(proxyBeanMethods = false)
public class ScheduledConfig {

//    @Bean
//    public TaskScheduler taskScheduler(){
//        return new SimpleAsyncTaskScheduler();
//    }

}
