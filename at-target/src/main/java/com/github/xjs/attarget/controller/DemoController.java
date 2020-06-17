package com.github.xjs.attarget.controller;

import com.github.xjs.attarget.service.Service1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private Service1 service1;

    @GetMapping("/hello")
    public String hello(){
        System.out.println("service1.getClass():"+service1.getClass().getName());
        return "hello";
    }

}
