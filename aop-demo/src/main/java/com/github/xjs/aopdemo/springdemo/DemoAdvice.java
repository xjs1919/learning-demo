package com.github.xjs.aopdemo.springdemo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class DemoAdvice {
    @Pointcut("execution(* com.github.xjs.aopdemo.service.*.*(..))")
    public void pointCut(){
    }
    @Before("pointCut()")
    public void before(){
        log.info("---------before-------");
    }
}