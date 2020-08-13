package com.github.xjs.openfeign.demo2encoder;

import feign.Headers;
import feign.RequestLine;

public interface Encoder {

    @RequestLine("POST /encoder/add")
    @Headers("Content-Type: application/json")
    User add(User user);
}
