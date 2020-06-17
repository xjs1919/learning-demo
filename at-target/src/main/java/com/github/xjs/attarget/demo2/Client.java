package com.github.xjs.attarget.demo2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config2.class);
        Service2 s2 = ctx.getBean(Service2.class);
        s2.hello();
        System.out.println(s2.getClass().getName());
        Service3 s3 = ctx.getBean(Service3.class);
        s3.hello();
        System.out.println(s3.getClass().getName());
    }
}
