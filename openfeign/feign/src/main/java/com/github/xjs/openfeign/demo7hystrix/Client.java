package com.github.xjs.openfeign.demo7hystrix;

import com.netflix.hystrix.HystrixCommand;
import feign.AsyncFeign;
import feign.hystrix.HystrixFeign;

import java.util.concurrent.CompletableFuture;

public class Client {

    public static void main(String[] args)throws Exception {
        Demo demo = HystrixFeign.builder()
                .target(Demo.class,
                        "http://localhost:8080/");
        String result = demo.sync(100, "Joshua");
        System.out.println(result);
        HystrixCommand<String> command = demo.async1(100, "Joshua");
        System.out.println(command.execute());
        CompletableFuture<String> future = demo.async2(100, "Joshua");
        System.out.println(future.get());
    }
}
