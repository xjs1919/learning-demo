package com.xjs1919.mybatis.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.spi.WebServiceFeatureAnnotation;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jiashuai.xujs
 * @date 2022/4/1 13:42
 */
@Configuration
public class AppConfig {

    @Bean
    public ExecutorService ttlExecutor(){
        int coreSize = Runtime.getRuntime().availableProcessors();
        int maxSize = coreSize * 2;
        ExecutorService executor = new ThreadPoolExecutor(coreSize,
                maxSize,
                1800, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10), new ThreadPoolExecutor.CallerRunsPolicy());
        return TtlExecutors.getTtlExecutorService(executor);
    }



}
