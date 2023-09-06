package com.github.xjs.feign;

import com.github.xjs.feign.fallback.UserFallbackFactory;
import com.github.xjs.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "userservice",
        url = "http://localhost:8081"
        ,fallbackFactory = UserFallbackFactory.class,
        configuration = FeignConfig.class
        )
public interface UserClient {
    @GetMapping("/user/{id}")
    public User queryById(@PathVariable("id") Long id);
}
