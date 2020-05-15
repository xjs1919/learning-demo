package com.github.xjs.aopdemo.springdemo;

import com.github.xjs.aopdemo.service.IService;
import com.github.xjs.aopdemo.service.Service2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoApp.class);
        IService s1 = ctx.getBean(IService.class);
        Service2 s2 = ctx.getBean(Service2.class);
        //class com.sun.proxy.$Proxy17
        System.out.println(s1.getClass());
        //class com.github.xjs.aopdemo.service.Service2$$EnhancerBySpringCGLIB$$de245da0
        System.out.println(s2.getClass());
    }
}
