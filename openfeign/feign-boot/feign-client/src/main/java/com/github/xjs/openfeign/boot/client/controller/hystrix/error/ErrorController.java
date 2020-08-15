package com.github.xjs.openfeign.boot.client.controller.hystrix.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/error")
public class ErrorController {

    @Autowired
    private ErrorClient errorClient;

    @GetMapping("/hello")
    public String hello(){
        return errorClient.hello();
    }
}
