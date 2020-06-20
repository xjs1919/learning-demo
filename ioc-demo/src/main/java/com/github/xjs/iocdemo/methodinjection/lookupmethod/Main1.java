package com.github.xjs.iocdemo.methodinjection.lookupmethod;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;

public class Main1 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(LookupApp.class);
        CommandManager manager = ctx.getBean(CommandManager.class);
        System.out.println(manager);
        manager.process(new HashMap());
        manager.process(new HashMap());
    }
}
