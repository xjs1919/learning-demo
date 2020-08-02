package com.github.xjs.hystrix.demo5;

import com.github.xjs.hystrix.User;
import com.github.xjs.hystrix.demo4.BizException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixException;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;

public class UserService {
    @CacheResult
    @HystrixCommand(commandKey = "getUserById")
    //使用CacheKey
    public User getUserById(@CacheKey String id) {
        return new User(id, "getUserById");
    }
    //cacheKeyMethod
    @CacheResult(cacheKeyMethod = "getUserByNameCacheKey")
    @HystrixCommand
    public User getUserByName(String name) {
        return new User("id" ,name);
    }
    private String getUserByNameCacheKey(String name) {
        return name;
    }
    //清理缓存
    @CacheRemove(commandKey="getUserById" )
    @HystrixCommand
    public void update(@CacheKey("id") User user) {
        return;
    }
}
