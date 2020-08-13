package com.github.xjs.openfeign.boot.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server/demo")
public class ServerDemoController {
    @GetMapping("/hello/{username}")
    public String hello(@PathVariable("username") String username, String msg){
        return "hello:" + username+",say:"+ msg;
    }
}
