package com.github.xjs.attarget.demo3;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Advice3 {

    @Pointcut("@within(com.github.xjs.attarget.demo3.AnnoFather)")
    public void pointCut1(){
    }

    @Pointcut("@target(com.github.xjs.attarget.demo3.AnnoFather)")
    public void pointCut2(){
    }

    @Pointcut("@within(com.github.xjs.attarget.demo3.AnnoSon)")
    public void pointCut3(){
    }

    @Pointcut("@target(com.github.xjs.attarget.demo3.AnnoSon)")
    public void pointCut4(){
    }

    @Before("pointCut1()")
    public void before1(){
        System.out.println("---------@within @AnnoFather-------");
    }

    @Before("pointCut2()")
    public void before2(){
        System.out.println("---------@target @AnnoFather-------");
    }

    @Before("pointCut3()")
    public void before3(){
        System.out.println("---------@within @AnnoSon-------");
    }

    @Before("pointCut4()")
    public void before4(){
        System.out.println("---------@target @AnnoSon-------");
    }
}