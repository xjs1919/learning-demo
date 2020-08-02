package com.github.xjs.hystrix.demo6;

import com.github.xjs.hystrix.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;

public class UserService {
    @HystrixCommand(
            fallbackMethod = "fallback",
            commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
            })
    public User getUserById(String id) {
        try{
            Thread.sleep(1000);
        }catch(Exception e){
        }
        return new User(id, "getUserById");
    }
    public User fallback(String id){
        return new User(id, "fallback");
    }
}
