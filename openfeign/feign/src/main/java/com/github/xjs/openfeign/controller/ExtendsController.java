package com.github.xjs.openfeign.controller;

import com.github.xjs.openfeign.demo4extend.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/extend")
public class ExtendsController  {

    @GetMapping("/getById/{id}")
    public User getById(@PathVariable("id")String id){
        return new User(id, "username");
    }
}
