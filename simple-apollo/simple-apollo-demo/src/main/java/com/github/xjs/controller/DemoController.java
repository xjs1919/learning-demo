package com.github.xjs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/29 14:08
 */
@RestController
public class DemoController {

    @Value("${user.username:Joshua}")
    private String username;

    @Value("${user.password:abcdefg}")
    private String password;

    @Value("${user.notexist:notexist}")
    private String notExist;

    @Autowired
    private DemoProperties properties;


    @GetMapping("/hello")
    public String hello(){
        String str = "@Value:" + username+","+password+","+notExist;
        str += "-------------";
        str += "@ConfigurationProperties:" + properties.getUsername() + "," + properties.getPassword();
        return str;
    }

}
