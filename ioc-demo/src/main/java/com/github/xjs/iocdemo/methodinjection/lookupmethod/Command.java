package com.github.xjs.iocdemo.methodinjection.lookupmethod;

public class Command {

    public Command(){
        System.out.println(this);
    }

    public void setState(Object commandState) {
    }

    public Object execute() {
        return null;
    }
}
