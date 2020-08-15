package com.github.xjs.openfeign.boot.api;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/server/user")
public interface UserApi {

    @GetMapping("/getById/{id}")
    public User getById(@PathVariable("id") String id);

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user);

}

