package com.github.xjs.attarget.demo3;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Advice3 {

    @Before("@within(com.github.xjs.attarget.demo3.AnnoFather)")
    public void before1(){
        System.out.println("---------@within @AnnoFather-------");
    }
    @Before("@target(com.github.xjs.attarget.demo3.AnnoFather))")
    public void before2() {
        System.out.println("---------@target @AnnoFather-------");
    }
    @Before("@within(com.github.xjs.attarget.demo3.AnnoSon)")
    public void before3(){
        System.out.println("---------@within @AnnoSon-------");
    }
    @Before("@target(com.github.xjs.attarget.demo3.AnnoSon)")
    public void before4(){
        System.out.println("---------@target @AnnoSon-------");
    }
}