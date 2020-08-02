package com.github.xjs.openfeign.controller;

import com.github.xjs.openfeign.demo1.User;
import feign.Param;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/uriTemplate/{id}")
    public String id(@PathVariable("id")String id){
        return "uriTemplate:"+id;
    }

    @GetMapping("/paramMap")
    public String paramMap(String username, String password, Integer age){
        return username+":"+password+":"+age;
    }

    @GetMapping("/queryMapEncoder")
    public String queryMapEncoder(String username, String password){
        return "queryMapEncoder:" + username+":"+password;
    }

    @GetMapping("/expander/{username}")
    public String expander(@PathVariable("username") String username){
        return username;
    }

    @PostMapping("/body")
    public String body(String username, String password){
        return "body:" + username+":"+password;
    }

    @PostMapping("/bodyJson")
    public String bodyJson(@RequestBody User user){
        return "bodyJson:" + user.getUsername()+":"+user.getPassword();
    }

}
