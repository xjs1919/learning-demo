package com.github.xjs.web;

import com.github.xjs.pojo.User;
import com.github.xjs.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        if(id % 2 == 0){
            return userService.queryById(id);
        }else {
            throw new RuntimeException("查询用户异常");
        }
    }
}
