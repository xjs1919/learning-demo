package com.github.xjs.schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.*;

@Configuration
@EnableScheduling
public class ScheduleApp {

    @Bean
    public ScheduleBean asyncBean() {
        return new ScheduleBean();
    }
    /******************以下几种方法都可以自定义调度器**********************/
//    @Bean
//    public TaskScheduler threadPoolTaskScheduler(){
//        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//        taskScheduler.setPoolSize(2);
//        taskScheduler.setThreadNamePrefix("1-");
//        return taskScheduler;
//    }
//
//    @Bean
//    public TaskScheduler taskScheduler(){
//        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//        taskScheduler.setPoolSize(2);
//        taskScheduler.setThreadNamePrefix("2-");
//        return taskScheduler;
//    }


//    @Bean
//    public ScheduledExecutorService scheduledExecutorService(){
//        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
//        return executor;
//    }

//    @Bean
//    public ScheduledExecutorFactoryBean taskScheduler(){
//        ScheduledExecutorFactoryBean factory = new ScheduledExecutorFactoryBean();
//        factory.setPoolSize(2);
//        factory.setThreadNamePrefix("4-");
//        return factory;
//    }

//    @Bean
//    public SchedulingConfigurer schedulingConfigurer(){
//        return new SchedulingConfigurerDemo();
//    }
//
//    public static class SchedulingConfigurerDemo implements SchedulingConfigurer{
//        @Override
//        public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//            ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//            taskScheduler.setThreadNamePrefix("5-");
//            taskScheduler.setPoolSize(1);
//            taskScheduler.initialize();
//            taskRegistrar.setTaskScheduler(taskScheduler);
//        }
//    }

}
