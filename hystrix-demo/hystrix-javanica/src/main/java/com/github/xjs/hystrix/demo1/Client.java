package com.github.xjs.hystrix.demo1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args)throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = ctx.getBean(UserService.class);
        //同步调用
        System.out.println(userService.getUserById("Joshua"));
        //异步调用
        System.out.println(userService.getUserByName("Joshua").get());
    }
}
