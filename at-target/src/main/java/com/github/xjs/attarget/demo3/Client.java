package com.github.xjs.attarget.demo3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config3.class);
        Father father = ctx.getBean("father", Father.class);
        father.fun1();
        /**
         *  ---------@within @AnnoFather-------
         * ---------@target @AnnoFather-------
         * father fun1
         * */
        father.fun2();
        /**
         * ---------@within @AnnoFather-------
         * ---------@target @AnnoFather-------
         * father fun2
         * */
        Son son = (Son)ctx.getBean("son", Son.class);
        son.fun1();
        /**
         * ---------@within @AnnoSon-------
         * ---------@target @AnnoSon-------
         * son fun1
         * */
        son.fun2();
        /**
         * ---------@within @AnnoFather-------
         * ---------@target @AnnoSon-------
         * father fun2
         * fun2()的运行时对象是son，但是fun2()却是在father中定义的。
         * */
    }
}
