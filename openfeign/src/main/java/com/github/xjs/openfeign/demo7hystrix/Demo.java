package com.github.xjs.openfeign.demo7hystrix;

import com.netflix.hystrix.HystrixCommand;
import feign.Param;
import feign.RequestLine;

import java.util.concurrent.CompletableFuture;

public interface Demo {
    //同步调用
    @RequestLine("GET /hystrix/hello/{id}/{username}")
    String sync(@Param("id") int id, @Param("username")String username);
    //异步调用
    @RequestLine("GET /hystrix/hello/{id}/{username}")
    HystrixCommand<String> async1(@Param("id") int id, @Param("username")String username);
    //异步调用
    @RequestLine("GET /hystrix/hello/{id}/{username}")
    CompletableFuture<String> async2(@Param("id") int id, @Param("username")String username);

    @RequestLine("GET /hystrix/getById/{id}")
    String getById(@Param("id") Integer id);

    @RequestLine("GET /hystrix/getByName/{username}")
    String getByName(@Param("username") String username);


}
