package com.github.xjs.iocdemo.methodinjection.lookupmethod;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public abstract class CommandManager3 {

    public Object process(Object commandState) {
        // grab a new instance of the appropriate Command interface
        Command3 command = createCommand();
        // set the state on the (hopefully brand new) Command instance
        command.setState(commandState);
        return command.execute();
    }

    @Lookup
    protected abstract Command3 createCommand();
}
