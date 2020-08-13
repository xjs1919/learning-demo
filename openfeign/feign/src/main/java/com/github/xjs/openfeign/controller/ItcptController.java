package com.github.xjs.openfeign.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/itcpt")
@RestController
public class ItcptController {
    @GetMapping("/getByUsername/{username}")
    public String getByUsername(@PathVariable("username") String username,
                                @RequestHeader("token")String token){
        return "getByUsername:"+username+",token:"+token;
    }
}
