package com.github.xjs.openfeign.demo5errordecoder;

public class BizException extends RuntimeException {
    public BizException(String msg){
        super(msg);
    }
}
