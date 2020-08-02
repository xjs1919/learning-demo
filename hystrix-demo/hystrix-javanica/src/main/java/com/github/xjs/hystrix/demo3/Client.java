package com.github.xjs.hystrix.demo3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args)throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = ctx.getBean(UserService.class);
        System.out.println(userService.getUserById(100L));
        System.out.println(userService.getUserByName("Joshua"));
        System.out.println("--------------------------------------");
        UserService2 userService2 = ctx.getBean(UserService2.class);
        System.out.println(userService2.getUserByName("Joshua"));

    }
}
