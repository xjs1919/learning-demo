package com.github.xjs.conditiondemo.demo1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config1.class, Config2.class);
        Map<String, String> beanMap=ctx.getBeansOfType(String.class);
        beanMap.forEach((name, bean)-> System.out.println(name+":"+bean));
        //输出：name:爪哇优太儿
    }
}
