package com.github.xjs.openfeign.controller;

import com.github.xjs.openfeign.demo7hystrix.BizException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hystrix")
public class HystrixController {
    @GetMapping("/hello/{id}/{username}")
    public String hello(@PathVariable("id") int id, @PathVariable("username")String username){
        return id+","+username;
    }
    @GetMapping("/getById/{id}")
    public String getById(@PathVariable("id") Integer id){
        throw new RuntimeException();
    }
    @GetMapping("/getByName/{username}")
    public String getByName(@PathVariable(value="username") String username,
                            @RequestHeader(value="token", required = false)String token){
        if(StringUtils.isEmpty(token)){
            throw new BizException(1, "参数token不能为空");
        }
        return "getByName:"+username;
    }
}
