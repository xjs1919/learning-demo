package com.github.xjs.openfeign.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("/hello")
    public String hello(){
        throw new RuntimeException("服务端异常");
    }
}
