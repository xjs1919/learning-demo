package com.github.xjs.openfeign.boot.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/server/demo2")
public interface DemoApi {

    @GetMapping("/hello/{username}")
    public String hello(@PathVariable("username") String username,
                        @RequestParam("msg") String msg);
}

