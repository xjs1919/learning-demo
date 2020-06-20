package com.github.xjs.iocdemo.methodinjection.coupled;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Command {

    private Map commandState;

    public void setState(Map commandState) {
        this.commandState = commandState;
    }

    public Object execute() {
        System.out.println(this);
        return commandState;
    }
}
