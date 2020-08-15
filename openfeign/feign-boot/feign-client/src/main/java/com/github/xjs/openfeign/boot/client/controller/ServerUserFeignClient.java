package com.github.xjs.openfeign.boot.client.controller;

import com.github.xjs.openfeign.boot.api.DemoApi;
import com.github.xjs.openfeign.boot.api.HystrixApi;
import com.github.xjs.openfeign.boot.api.User;
import com.github.xjs.openfeign.boot.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="ServerUserFeignClient",
        url="http://localhost:8080")
public interface ServerUserFeignClient extends UserApi {

    @GetMapping("/updateUser")
    public User updateUser(@SpringQueryMap User user);
}
