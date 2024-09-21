package com.github.xjs.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User{
        private Integer id;
        private String name;
    }

    @GetMapping("jsonp")
    public User json(){
        return new User(1, "zhangsan");
    }

}
