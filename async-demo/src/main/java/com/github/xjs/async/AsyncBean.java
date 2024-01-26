package com.github.xjs.async;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.*;

public class AsyncBean {

    public void printSync(){
        System.out.println(Thread.currentThread().getName()+",print");
    }

    @Async("helloExecutor")
    public void printAsync(){
        System.out.println(Thread.currentThread().getName()+",print");
    }


    @Async
    public Future<String> printAsyncFuture(){
        return CompletableFuture.supplyAsync(()->"Future<String>");
    }

    @Async
    public String printAsyncNoFuture(){
        return "String";
    }
}
