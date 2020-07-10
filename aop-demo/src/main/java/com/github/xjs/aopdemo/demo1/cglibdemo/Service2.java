package com.github.xjs.aopdemo.demo1.cglibdemo;

public class Service2 {

    public void m1() {
        System.out.println("我是m1方法");
        this.m2(); //@1
    }

    public void m2() {
        System.out.println("我是m2方法");
    }
}
