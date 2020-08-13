package com.github.xjs.openfeign.boot.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/demo")
public class ClientDemoController {

    @Autowired
    private ServerDemoFeignClient serverClient;

    @Autowired
    private ServerDemoFeignClient2 serverClient2;

    @GetMapping("/hello")
    public String hello(String msg){
        return serverClient.hello("Joshua", msg);
    }

    @GetMapping("/hello2")
    public String hello2(String msg){
        return serverClient2.hello("Joshua2", msg);
    }

}
