package com.github.xjs.iocdemo.methodinjection.lookupmethod;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;

public class Main3 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Lookup3App.class);
        CommandManager3 manager = ctx.getBean(CommandManager3.class);
        System.out.println(manager);
        manager.process(new HashMap());
        manager.process(new HashMap());
    }
}
