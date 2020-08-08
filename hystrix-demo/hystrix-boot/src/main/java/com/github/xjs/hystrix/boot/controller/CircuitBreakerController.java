package com.github.xjs.hystrix.boot.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/circuit")
public class CircuitBreakerController {
    private static Logger log = LoggerFactory.getLogger(CircuitBreakerController.class);
    private static AtomicInteger cnt = new AtomicInteger(0);
    @GetMapping("/open")
    @HystrixCommand(
            fallbackMethod = "openFallback",
            commandProperties={
                    @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="5000"),
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="4"),
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="3000")
            }
    )
    public String open(){
        log.info("c={}", cnt.get());
        int c = cnt.incrementAndGet();
        if(c>2){
            throw new RuntimeException();
        }else{
            return "c="+c;
        }
    }
    public String openFallback(Throwable t){
        return "fallback:"+cnt.get()+","+t.getMessage();
    }
}
