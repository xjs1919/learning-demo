package com.github.xjs.openfeign.boot.server.controller;

import com.github.xjs.openfeign.boot.api.HystrixApi;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerHystrixController implements HystrixApi {
    @Override
    public String hello() {
        throw new NullPointerException("hello NPE");
    }

    @GetMapping("/prototype")
    public String prototype(){
        throw new NullPointerException("prototype NPE");
    }

    @GetMapping("/hystrixCommand")
    public HystrixCommand<String> hystrixCommand(){
        throw new NullPointerException("HystrixCommand NPE");
    }

    @GetMapping("/isolate/hello")
    public String isolateHello(){
        return "ServerHystrixController isolateHello";
    }

    @GetMapping("/error/hello")
    public String errorHello(){
        throw new RuntimeException("ServerHystrixController RuntimeException");
    }
}
