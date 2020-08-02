package com.github.xjs.hystrix.demo5;

import com.github.xjs.hystrix.User;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args)throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = ctx.getBean(UserService.class);
        //首先得初始化context
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try{
            User u1 = userService.getUserById("id");
            User u2 = userService.getUserById("id");
            //true
            System.out.println(u1 == u2);
            userService.update(u1);
            User u3 = userService.getUserById("id");
            User u4 = userService.getUserById("id");
            //false
            System.out.println(u2 == u3);
            //true
            System.out.println(u3 == u4);
        }finally{
            context.shutdown();
        }
    }
}
