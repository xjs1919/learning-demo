package com.github.xjs.attarget.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class DemoAdvice {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    //@Pointcut("@target(org.springframework.web.bind.annotation.RestController)")    	
    public void pointCut(){
    }

    @Before("pointCut()")
    public void before(){
        log.info("---------before-------");
    }
}