package com.github.xjs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanLifecycleTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("com.github.xjs.bean.lifecycle");
        ctx.refresh();
        ctx.close();
    }
}