package com.github.xjs.iocdemo.methodinjection.lookupmethod;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;

public class Main2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Lookup2App.class);
        CommandManager2 manager = ctx.getBean(CommandManager2.class);
        System.out.println(manager);
        manager.process(new HashMap());
        manager.process(new HashMap());
    }
}
