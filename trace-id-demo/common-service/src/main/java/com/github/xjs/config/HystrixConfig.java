package com.github.xjs.config;

import com.github.xjs.util.TraceIdCallable;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Callable;

@Configuration
@ConditionalOnClass(name={"com.netflix.hystrix.HystrixCommand"})
public class HystrixConfig {

    @Bean
    public HystrixConcurrencyStrategy hystrixConcurrencyStrategy(){
        return new TraceIdHystrixConcurrencyStrategy();
    }

    public static class TraceIdHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy{
        public TraceIdHystrixConcurrencyStrategy(){
            //注册成hystrix的插件
            HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
        }
        @Override
        public <T> Callable<T> wrapCallable(Callable<T> callable) {
            //先包装一下要执行的任务，在这里把ThreadLocal的值取出来
            return new TraceIdCallable<T>(callable);
        }
    }
}