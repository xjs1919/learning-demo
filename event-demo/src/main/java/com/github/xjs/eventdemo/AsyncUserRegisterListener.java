package com.github.xjs.eventdemo;

import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class AsyncUserRegisterListener implements ApplicationListener<UserRegisterEvent> {

    @Override
    @Async
    public void onApplicationEvent(UserRegisterEvent event) {
        System.out.println(Thread.currentThread().getName()+"-收到用户注册事件，异步执行任务");
    }
}
