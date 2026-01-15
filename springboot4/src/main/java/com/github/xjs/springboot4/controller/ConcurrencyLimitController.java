package com.github.xjs.springboot4.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

//@EnableResilientMethods
@RestController
@RequestMapping("api/concurrency-limit/")
public class ConcurrencyLimitController {

    private static Logger log = LoggerFactory.getLogger(ConcurrencyLimitController.class);

    @ConcurrencyLimit(2)
    @GetMapping(path = "/demo")
    public String demo() throws Exception{
        Thread.sleep(1000);
        log.info(Thread.currentThread().getName() + " ConcurrentLimitController demo");
        return "demo";
    }

}
