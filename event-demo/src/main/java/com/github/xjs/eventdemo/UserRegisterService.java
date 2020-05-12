package com.github.xjs.eventdemo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public boolean userRegister(String username){
        System.out.println("用户"+username+"注册成功");
        applicationContext.publishEvent(new UserRegisterEvent(this, username));
        return true;
    }
}
