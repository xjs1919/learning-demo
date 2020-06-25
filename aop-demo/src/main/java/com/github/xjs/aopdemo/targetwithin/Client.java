package com.github.xjs.aopdemo.targetwithin;

import com.github.xjs.aopdemo.service.IService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoApp.class);
        DemoController controller = ctx.getBean(DemoController.class);
        controller.hello();
        System.out.println(ctx.getBean(IService.class).getClass().getName());
        System.out.println(ctx.getBean(String.class).getClass().getName());
    }
}
