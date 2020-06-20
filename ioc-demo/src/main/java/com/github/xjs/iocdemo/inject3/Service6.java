package com.github.xjs.iocdemo.inject3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service6 {

    @Autowired
    private IService<String> s1;

    @Autowired
    private IService<Integer> s2;


    public IService<String> getS1(){
        return s1;
    }

    public IService<Integer> getS2(){
        return s2;
    }


}
