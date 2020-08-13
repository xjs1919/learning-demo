package com.github.xjs.openfeign.demo6async;

import feign.AsyncFeign;

import java.util.concurrent.CompletableFuture;

public class Client {

    public static void main(String[] args)throws Exception {
        Demo demo = AsyncFeign.asyncBuilder()
                .target(Demo.class, "http://localhost:8080/");
        CompletableFuture<String> result = demo.hello();
        System.out.println(result.get());
    }
}
