package com.github.xjs.hystrix.demo3;

import com.github.xjs.hystrix.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

//默认的fallback
@DefaultProperties(defaultFallback = "globalFallbackMethod")
public class UserService2 {

    @HystrixCommand(defaultFallback = "commandFallbackMethod")
    public User getUserByName(String name){
        throw new RuntimeException("getUserByName");
    }

    public User globalFallbackMethod(Throwable t){
        System.out.println("globalFallback, exception message:" + t.getMessage());
        return new User();
    }

    public User commandFallbackMethod(Throwable t){
        System.out.println("commandFallback, exception message:" + t.getMessage());
        return new User("id","commandFallbackMethod");
    }
}
