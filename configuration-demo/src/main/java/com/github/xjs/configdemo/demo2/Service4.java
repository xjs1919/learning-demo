package com.github.xjs.configdemo.demo2;
//Service4跟Service2一模一样
public class Service4 {
    private Service3 s3;
    public Service4(Service3 s3){
        this.s3 = s3;
        System.out.println("construct s4:"+this);
    }
}