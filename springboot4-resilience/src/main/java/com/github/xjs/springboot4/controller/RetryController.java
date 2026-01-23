package com.github.xjs.springboot4.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.retry.RetryPolicy;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.resilience.retry.MethodRetryPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.time.Duration;

@Slf4j
@RestController
@RequestMapping("api/retry/")
public class RetryController {

    @Retryable(
            maxRetries = 3,
            delay = 1000,
            maxDelay = 5000,
            multiplier = 3,
            jitter = 100,
            includes = RuntimeException.class,
            excludes = ArithmeticException.class
    )
    @GetMapping(path = "/demo")
    public String demo(){
        log.info("RetryController demo");
        int a = 1/0;
        return "demo";
    }


    @GetMapping(path = "/demo2")
    public String demo2() throws Exception{
        var retryPolicy = RetryPolicy.builder()
                .maxRetries(3)
                .delay(Duration.ofMillis(1000))
                .maxDelay(Duration.ofMillis(5000))
                .multiplier(3)
                .jitter(Duration.ofMillis(100))
                .includes(RuntimeException.class)
                //.excludes(ArithmeticException.class)
                .build();
        RetryTemplate retryTemplate = new RetryTemplate(retryPolicy);
        return retryTemplate.execute(()->{
            log.info("RetryController demo2");
            int a = 1/0;
            return "demo2";
        });
    }
}


