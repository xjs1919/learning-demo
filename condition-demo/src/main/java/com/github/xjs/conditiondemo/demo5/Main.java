package com.github.xjs.conditiondemo.demo5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config2.class, Config1.class);
        Map<String, String> beanMap=ctx.getBeansOfType(String.class);
        beanMap.forEach((name, bean)-> System.out.println(name+":"+bean));
        //name:爪哇优太儿
    }
}
