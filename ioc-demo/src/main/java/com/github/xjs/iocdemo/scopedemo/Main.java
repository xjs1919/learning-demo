package com.github.xjs.iocdemo.scopedemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoApp.class);
        Service1 service1 = ctx.getBean(Service1.class);
        System.out.println(service1);
        Service1 service2 = ctx.getBean(Service1.class);
        System.out.println(service2);
        new Thread(()->{
            Service1 service3 = ctx.getBean(Service1.class);
            System.out.println(service3);
        }).start();
    }
}
