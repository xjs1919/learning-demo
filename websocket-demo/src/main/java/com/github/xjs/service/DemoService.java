package com.github.xjs.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {
    public String hello(){
        System.out.println("hello");
        return "hello";
    }
}
