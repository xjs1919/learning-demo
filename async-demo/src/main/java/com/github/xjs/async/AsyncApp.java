package com.github.xjs.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.TaskExecutorFactoryBean;

import java.util.concurrent.*;

@Configuration
@EnableAsync
public class AsyncApp {

    @Bean
    public AsyncBean asyncBean(){
        return new AsyncBean();
    }

//    @Bean //第一种方式
//    public Executor taskExecutor(){
//        ThreadPoolExecutorFactoryBean factoryBean = new ThreadPoolExecutorFactoryBean();
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(
//                1,
//                1,
//                30,
//                TimeUnit.MINUTES,
//                new LinkedBlockingQueue<>());
//        return executor;
//    }

//    @Bean //第二种方式
//    public TaskExecutor taskExecutorSpring(){
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setThreadNamePrefix("pool-2-");
//        executor.setCorePoolSize(2);
//        return executor;
//    }

//    @Bean //第三种方式，实际跟第一种是一样的
//    public ThreadPoolExecutorFactoryBean taskExecutor(){
//        ThreadPoolExecutorFactoryBean factory = new ThreadPoolExecutorFactoryBean();
//        factory.setThreadNamePrefix("pool-3-");
//        factory.setCorePoolSize(2);
//        factory.getObject();
//        return factory;
//    }

//    @Bean //第四种方式，实际跟第二种是一样的
//    public TaskExecutorFactoryBean taskExecutorFactory(){
//        TaskExecutorFactoryBean factory = new TaskExecutorFactoryBean();
//        factory.setPoolSize("2");
//        return factory;
//    }

    @Bean //第五种方式
    public CustomAsyncConfigure asyncConfigure(){
        return new CustomAsyncConfigure();
    }

    public static class CustomAsyncConfigure implements AsyncConfigurer{
        @Override
        public Executor getAsyncExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(2);
            executor.setThreadNamePrefix("pool-5-");
            executor.initialize();
            return executor;
        }
    }

}
