package com.github.xjs.aopdemo.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Service1 implements IService {
    @Override
    public void print(String msg){
        log.info("Service1 print:{}",  msg);
    }
}