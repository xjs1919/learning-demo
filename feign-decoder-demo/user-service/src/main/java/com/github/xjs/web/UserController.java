package com.github.xjs.web;

import com.github.xjs.pojo.R;
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
    public R<User> queryById(@PathVariable("id") Long id) {
        int a = 1/0;
        return R.ok(userService.queryById(id));
    }
}
