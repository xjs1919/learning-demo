package com.github.xjs.ribbonboot.controller;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/8/20 15:41
 */
@FeignClient(name="userService",path="/user")
public interface FeignUserClient {

    @GetMapping("/getById/{id}")
    public String getById(@PathVariable("id") String id);

}
