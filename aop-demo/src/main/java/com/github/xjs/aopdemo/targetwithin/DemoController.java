package com.github.xjs.aopdemo.targetwithin;

import org.springframework.stereotype.Controller;

@Controller
public class DemoController {

    public String hello(){
        System.out.println("hello");
        return "world";
    }

}
