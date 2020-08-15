package com.github.xjs.openfeign.boot.server.controller;

import com.github.xjs.openfeign.boot.api.DemoApi;
import com.github.xjs.openfeign.boot.api.User;
import com.github.xjs.openfeign.boot.api.UserApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerUserController implements UserApi {

    @Override
    public User getById(String id){
        return new User(id, "Joshua"+id);
    }

    @Override
    public User addUser(User user) {
        System.out.println("用户添加成功:"+user);
        return user;
    }

    @GetMapping("/updateUser")
    public User updateUser(String id, String username){
        return new User(id, "update:" + username);
    }
}
