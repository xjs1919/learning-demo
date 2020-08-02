package com.github.xjs.hystrix.demo2;

import com.github.xjs.hystrix.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

public class UserService {
    @HystrixCommand(fallbackMethod = "defaultUser")
    public User getUserById(String id) {
        throw new RuntimeException("getUserById");
    }
    @HystrixCommand(fallbackMethod = "defaultUserSecond")
    public User defaultUser(String id, Throwable t){
        System.out.println("exception message:" + t.getMessage());
        throw new RuntimeException("defaultUser");
    }
    public User defaultUserSecond(String id, Throwable t){
        System.out.println("exception message:" + t.getMessage());
        return new User();
    }
}
