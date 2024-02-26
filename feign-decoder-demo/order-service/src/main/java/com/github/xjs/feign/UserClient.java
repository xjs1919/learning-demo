package com.github.xjs.feign;

import com.github.xjs.pojo.R;
import com.github.xjs.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service", url = "http://localhost:8081", configuration = FeignConfig.class)
public interface UserClient {
    @GetMapping("/user/{id}")
    public R<User> queryById(@PathVariable("id") Long id);
}
