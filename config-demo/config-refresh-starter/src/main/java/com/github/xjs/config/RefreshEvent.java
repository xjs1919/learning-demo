package com.github.xjs.config;

import org.springframework.context.ApplicationEvent;

public class RefreshEvent extends ApplicationEvent {

    public RefreshEvent(Object source) {
        super(source);
    }
}
