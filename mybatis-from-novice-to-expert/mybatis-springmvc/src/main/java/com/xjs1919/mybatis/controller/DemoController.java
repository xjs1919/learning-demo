package com.xjs1919.mybatis.controller;

import com.xjs1919.mybatis.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiashuai.xujs
 * @date 2022/3/24 13:46
 */
@RequestMapping("/demo")
@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/user")
    public User user(){
        return new User(1, "xjs");
    }
}
