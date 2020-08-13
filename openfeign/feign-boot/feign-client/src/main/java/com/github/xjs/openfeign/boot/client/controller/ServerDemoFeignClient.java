package com.github.xjs.openfeign.boot.client.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="ServerDemoFeignClient", url="http://localhost:8080")
public interface ServerDemoFeignClient {
    @GetMapping("/server/demo/hello/{username}")
    public String hello(@PathVariable("username") String username,
                        @RequestParam("msg") String msg);
}
