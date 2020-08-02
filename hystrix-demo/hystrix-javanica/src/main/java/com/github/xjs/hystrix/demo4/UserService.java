package com.github.xjs.hystrix.demo4;

import com.github.xjs.hystrix.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixException;

public class UserService {

    @HystrixCommand(fallbackMethod = "fallback", ignoreExceptions = BizException.class)
    public User getUserById(Long id) {
        throw new BizException();
    }
    @HystrixCommand(raiseHystrixExceptions = HystrixException.RUNTIME_EXCEPTION)
    public User getUserByName(String name) {
        throw new BizException();
    }
    public User fallback(Long id, Throwable t){
        System.out.println("id:"+id+",exception message:" + t.getMessage());
        return new User(""+id,"fallback");
    }
}
