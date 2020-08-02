package com.github.xjs.hystrix.demo4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args)throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = ctx.getBean(UserService.class);
        try{
            System.out.println(userService.getUserById(100L));
        }catch(Exception e){
            System.out.println(e.getClass().getName());
        }
        System.out.println("-----------------------------------------");
        try{
            System.out.println(userService.getUserByName("Joshua"));
        }catch(Exception e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getCause().getClass().getName());
        }

    }
}
