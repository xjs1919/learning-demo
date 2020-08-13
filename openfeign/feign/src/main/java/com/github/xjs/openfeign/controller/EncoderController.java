package com.github.xjs.openfeign.controller;

import com.github.xjs.openfeign.demo1.User;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/encoder")
public class EncoderController {

    private static AtomicInteger AGE = new AtomicInteger(20);

    @PostMapping("/add")
    public User add(@RequestBody User user){
        user.setAge(AGE.incrementAndGet());
        return user;
    }
}
