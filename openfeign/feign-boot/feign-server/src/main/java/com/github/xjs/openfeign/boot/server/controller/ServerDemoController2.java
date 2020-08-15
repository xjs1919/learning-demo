package com.github.xjs.openfeign.boot.server.controller;

import com.github.xjs.openfeign.boot.api.DemoApi;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServerDemoController2 implements DemoApi {

    @Override
    public String hello(String username, String msg){
        return "hello:" + username+",say:"+ msg;
    }


}
