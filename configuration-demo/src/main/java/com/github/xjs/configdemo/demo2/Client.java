package com.github.xjs.configdemo.demo2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoConfiguration.class);
        DemoConfiguration demoConfig = ctx.getBean(DemoConfiguration.class);
        System.out.println(demoConfig);
        DemoComponent demoComponent = ctx.getBean(DemoComponent.class);
        System.out.println(demoComponent);

        //这里可以正常获取到Service5
        Service5 s5 = ctx.getBean(Service5.class);
        System.out.println(s5);
    }
}
