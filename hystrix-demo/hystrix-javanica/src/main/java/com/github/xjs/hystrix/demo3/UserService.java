package com.github.xjs.hystrix.demo3;

import com.github.xjs.hystrix.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

//默认的fallback
@DefaultProperties(defaultFallback = "fallback")
public class UserService {
    @HystrixCommand//无参
    public User getRandomUser(){
        throw new RuntimeException("getRandomUser");
    }
    @HystrixCommand//long参数
    public User getUserById(Long id) {
        throw new RuntimeException("getUserById");
    }
    @HystrixCommand//String参数
    public User getUserByName(String name){
        throw new RuntimeException("getUserByName");
    }
    //默认的fallback不能有参数，
    public User fallback(Throwable t){
        System.out.println("exception message:" + t.getMessage());
        return new User();
    }
}
