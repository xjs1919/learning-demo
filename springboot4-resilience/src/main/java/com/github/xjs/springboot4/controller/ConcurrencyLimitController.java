package com.github.xjs.springboot4.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.ConcurrencyThrottleInterceptor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/concurrency-limit/")
public class ConcurrencyLimitController {


    @ConcurrencyLimit(limit = 2)
    @GetMapping(path = "/demo")
    public String demo()throws Exception{
        log.info("ConcurrencyLimitController demo");
        Thread.sleep(1000);
        return "demo";
    }


    SyncTaskExecutor taskExecutor = new SyncTaskExecutor(){
        {
            this.setConcurrencyLimit(1);
        }
    };

    @GetMapping(path = "/demo2")
    public String demo2()throws Exception{
        return taskExecutor.execute(()->{
            log.info("ConcurrencyLimitController demo2");
            Thread.sleep(1000);
            return "demo";
        });
    }

}
