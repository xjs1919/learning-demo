package com.github.xjs.iocdemo.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoApp.class);
        ctx.getBean(Service1.class);
        ctx.close();
        //如果不主动调用close()，也可以注册ctx.registerShutdownHook();
    }
}
