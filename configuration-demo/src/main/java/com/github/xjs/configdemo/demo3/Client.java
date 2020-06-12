package com.github.xjs.configdemo.demo3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoConfiguration.class);
    }
}
