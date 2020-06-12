package com.github.xjs.configdemo.demo2;

//这是Service2, 构造函数注入了Service1
public class Service2 {
    private Service1 s1;
    public Service2(Service1 s1){
        this.s1 = s1;
        System.out.println("construct s2:"+this);
    }
}