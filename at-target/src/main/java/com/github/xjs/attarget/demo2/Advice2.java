package com.github.xjs.attarget.demo2;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Advice2 {

    @Pointcut("@within(com.github.xjs.attarget.demo2.MyAnno)")
    //@Pointcut("@target(com.github.xjs.attarget.demo2.MyAnno)")
    public void pointCut(){
    }

    @Before("pointCut()")
    public void before(){
        System.out.println("---------before-------");
    }
}