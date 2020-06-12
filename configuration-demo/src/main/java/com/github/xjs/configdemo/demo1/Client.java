package com.github.xjs.configdemo.demo1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    //这是入口类
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoApp.class);
        //这个肯定没问题
        IService impl = ctx.getBean(ServiceImpl.class);
        System.out.println(impl);
        //这里也能正常输出
        IService iService = (IService)ctx.getBean("defaultService");
        System.out.println(iService);
    }
}
