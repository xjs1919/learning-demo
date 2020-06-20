package com.github.xjs.iocdemo.instantiating;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoApp.class);
        System.out.println(ctx.getType("serviceFactory"));
        System.out.println(ctx.getType("randomService"));
    }
}
