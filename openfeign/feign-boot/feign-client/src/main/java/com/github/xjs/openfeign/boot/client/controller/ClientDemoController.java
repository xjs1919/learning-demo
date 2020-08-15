package com.github.xjs.openfeign.boot.client.controller;

import com.github.xjs.openfeign.boot.api.User;
import com.github.xjs.openfeign.boot.client.controller.hystrix.ServerHystrixFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/client/demo")
public class ClientDemoController {

    @Autowired
    private ServerDemoFeignClient serverClient;

    @Autowired
    private ServerDemoFeignClient2 serverClient2;

    @Autowired
    private ServerUserFeignClient userFeignClient;

    @Resource
    private ServerHystrixFeignClient serverHystrixFeignClient;

    @GetMapping("/hello")
    public String hello(String msg){
        return serverClient.hello("Joshua", msg);
    }

    @GetMapping("/hello2")
    public String hello2(String msg){
        return serverClient2.hello("Joshua2", msg);
    }

    @GetMapping("/user")
    public User getById(String id){
        return userFeignClient.getById(id);
    }

    @GetMapping("/addUser")
    public User addUser(String id, String username){
        return userFeignClient.addUser(new User(id, username));
    }

    @GetMapping("/updateUser")
    public User updateUser(String id, String username){
        return userFeignClient.updateUser(new User(id, username));
    }

    @GetMapping("/hystrix/hello")
    public String hystrix(){
        return serverHystrixFeignClient.hello();
    }

}
