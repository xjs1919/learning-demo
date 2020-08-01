package com.github.xjs.hystrix.user.controller;

import com.github.xjs.hystrix.user.service.UserService;
import com.github.xjs.hystrix.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/registerTimeout")
    public String registerTimeout(@RequestBody User user){
        return userService.registerTimeout(user);
    }

    @PostMapping("/registerError")
    public String registerError(@RequestBody User user){
        return userService.registerError(user);
    }

    @PostMapping("/registerCircuitOpen")
    public String registerCircuitOpen(@RequestBody User user){
        return userService.registerCircuitOpen(user);
    }
}
