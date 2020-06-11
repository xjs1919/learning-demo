package com.github.xjs.aopdemo.beantype;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoApp.class);
        System.out.println(ctx.getType("serviceFactory"));
        System.out.println(ctx.getType("randomService"));
    }
}
