package com.github.xjs.ajaxserver.controller;

public class ResultBean {

    private final Object data;

    public ResultBean(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
