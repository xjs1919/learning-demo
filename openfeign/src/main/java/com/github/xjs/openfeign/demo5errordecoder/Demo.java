package com.github.xjs.openfeign.demo5errordecoder;

import feign.RequestLine;
public interface Demo {
    @RequestLine("GET /error/hello")
    String hello();
}
