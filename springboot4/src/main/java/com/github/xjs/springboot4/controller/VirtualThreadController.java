package com.github.xjs.springboot4.controller;

import com.github.xjs.springboot4.service.VirtualThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/virtual")
public class VirtualThreadController {

    @Autowired
    private VirtualThreadService virtualThreadService;

    @GetMapping("/demo")
    public String demo(){
        return "is virtual:" + Thread.currentThread().isVirtual();
    }

    @GetMapping("async")
    public String async(){
        virtualThreadService.async();
        return "OK";
    }


}
