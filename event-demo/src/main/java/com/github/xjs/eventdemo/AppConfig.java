package com.github.xjs.eventdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

@EnableAsync
@Configuration
@ComponentScan
public class AppConfig {

    @Bean //另一种异步的方式，这样即使不加Async也可以异步
    public ApplicationEventMulticaster applicationEventMulticaster(){
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(this.taskExecutor().getObject());
        return eventMulticaster;
    }
    /**
     * 注意这个bean的名字，如果是taskExecutor，那么@Async也会使用这个线程池，否则@Async不会使用，原因请参考前一篇
     * */
    @Bean
    public ThreadPoolExecutorFactoryBean taskExecutor(){
        ThreadPoolExecutorFactoryBean result = new ThreadPoolExecutorFactoryBean();
        result.setThreadNamePrefix("event-pool-");
        result.setCorePoolSize(5);
        return result;
    }

}
