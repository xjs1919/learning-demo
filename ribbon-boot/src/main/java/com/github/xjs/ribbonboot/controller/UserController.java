package com.github.xjs.ribbonboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/8/20 15:41
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getById/{id}")
    public String getById(@PathVariable("id") String id){
        return "name:"+id;
    }

}
