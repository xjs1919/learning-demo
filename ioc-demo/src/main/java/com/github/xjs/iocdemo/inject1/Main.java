package com.github.xjs.iocdemo.inject1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoApp.class);

        Service3 s3 = ctx.getBean(Service3.class);
        System.out.println(s3.getIService());

        Service4 s4 = ctx.getBean(Service4.class);
        System.out.println(s4.getIService());
        System.out.println(s4.getServiceSet());

        Service5 s5 = ctx.getBean(Service5.class);
        System.out.println(s5.getIService1()+","+s5.getIService2());
    }
}
