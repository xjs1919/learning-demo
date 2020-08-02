package com.github.xjs.hystrix.demo7;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args)throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try{
            UserService userService = ctx.getBean(UserService.class);
            System.out.println(userService.getUserById("1"));
            System.out.println(userService.getUserById("2"));
            System.out.println(userService.getUserById("3"));
        }finally{
            context.close();
        }
    }
}
