package com.github.xjs.service.impl;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class LazyService implements InitializingBean {
    @SuppressWarnings("NullAway.Init")
    private String field;
    @Override
    public void afterPropertiesSet() {
        this.field = "fixed value";
    }
    public String getField(){
        return this.field;
    }
}
