package com.github.xjs.openfeign.demo7hystrix;

public class BizException extends RuntimeException{
    private int errorType;
    private String msg;
    public BizException(int errorType, String msg){
        super(errorType+":"+msg);
        this.errorType = errorType;
        this.msg = msg;
    }
    public int getErrorType() {
        return errorType;
    }

    public String getMsg() {
        return msg;
    }
}
