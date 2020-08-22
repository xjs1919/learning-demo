package com.github.xjs.error.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/hello")
    public String hello(){
        throw new RuntimeException("服务端异常，请稍后再试！");
    }
}
