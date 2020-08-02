package com.github.xjs.openfeign.controller;

import com.github.xjs.openfeign.demo1.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/async")
public class AsyncController {
    @GetMapping("/hello")
    public String hello(){
        try{Thread.sleep(3000);}catch (Exception e){}
        return "hello";
    }
}
