package com.github.xjs.iocdemo.inject2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoApp.class);
        Service3 s3 = ctx.getBean(Service3.class);
        System.out.println(s3.getService());
    }
}
