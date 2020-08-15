package com.github.xjs.openfeign.boot.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/server/hystrix")
public interface HystrixApi {

    @GetMapping("/hello")
    public String hello();
}

