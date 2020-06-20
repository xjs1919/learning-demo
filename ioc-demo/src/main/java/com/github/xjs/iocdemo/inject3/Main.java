package com.github.xjs.iocdemo.inject3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoApp.class);
        Service6 s3 =  ctx.getBean(Service6.class);
        System.out.println(s3.getS1());
        System.out.println(s3.getS2());

    }
}
