package com.github.xjs.eventdemo;

import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class MailUserRegisterListener implements ApplicationListener<UserRegisterEvent> {
    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        System.out.println(Thread.currentThread().getName()+"-给用户"+event.getUsername()+"发送邮件！");
    }
}
