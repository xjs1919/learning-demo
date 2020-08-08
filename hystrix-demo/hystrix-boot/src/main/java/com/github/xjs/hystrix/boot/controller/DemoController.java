package com.github.xjs.hystrix.boot.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private static Logger log = LoggerFactory.getLogger(DemoController.class);

    @HystrixCommand(fallbackMethod = "helloFallback")
    @GetMapping("/hello")
    public String hello(String msg){
        throw new RuntimeException("hello throw RuntimeException");
    }
    public String helloFallback(String msg, Throwable t) {
        return "helloFallback:" + msg + "," + t.getMessage();
    }

    @HystrixCommand(fallbackMethod = "helloFallback")
    @GetMapping("/timeout")
    public String timeout(String msg){
        try{
            Thread.sleep(5000);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "timeout";
    }

    @HystrixCommand(fallbackMethod = "helloFallback")
    @GetMapping("/command_key_test")
    public String commandKeyTest(String msg){
        try{
            Thread.sleep(5000);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "timeout";
    }

}
