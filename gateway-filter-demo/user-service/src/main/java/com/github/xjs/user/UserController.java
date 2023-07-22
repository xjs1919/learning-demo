package com.github.xjs.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    public String queryById(@PathVariable("id") Long id,
                            @RequestHeader(value = "auth",required = false)String auth) {
        return "user=" + id + ",auth=" + auth;
    }

}
