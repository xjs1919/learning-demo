package com.github.xjs.eventdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.Future;

public class AsyncMain {
    public static void main(String[] args)throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        UserRegisterService service = ctx.getBean(UserRegisterService.class);
        service.userRegister("xjs");
    }
}
