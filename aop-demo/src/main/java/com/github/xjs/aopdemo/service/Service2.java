package com.github.xjs.aopdemo.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Service2 {//它并没有实现IService接口
    public void print(String msg){
        log.info("Service2 print:{}",  msg);
    }
}