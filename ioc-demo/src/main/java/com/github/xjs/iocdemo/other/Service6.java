package com.github.xjs.iocdemo.other;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Service6 {

    @PostConstruct
    public void PostConstruct(){
        System.out.println(">>>>>>>>>>>>>>>Service6#PostConstruct()");
    }
}
