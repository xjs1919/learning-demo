package com.github.xjs.iocdemo.methodinjection.lookupmethod;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Command3 {

    public Command3(){
        System.out.println(this);
    }

    public void setState(Object commandState) {
    }

    public Object execute() {
        return null;
    }
}
