package com.github.xjs1919;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujiashuai
 * @date 2022/5/25 13:34
 **/
@RestController
public class DemoController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("hello")
    public String hello(){
        redisTemplate.boundValueOps("hello").set("world");
        return "world";
    }
}
