package com.github.xjs.iocdemo.methodinjection.coupled;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(CoupleApp.class);
        CommandManager manager = ctx.getBean(CommandManager.class);
        manager.process(new HashMap());
        manager.process(new HashMap());
    }
}
