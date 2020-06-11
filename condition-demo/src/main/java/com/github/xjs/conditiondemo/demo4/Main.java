package com.github.xjs.conditiondemo.demo4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config1.class);
        Map<String, IService> beanMap=ctx.getBeansOfType(IService.class);
        beanMap.forEach((name, bean)-> System.out.println(name+":"+bean));
        //service1:com.github.xjs.conditiondemo.demo4.Service1@3b2cf7ab
    }
}
