package com.github.xjs.openfeign.demo3interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class TokenInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header("token", "TK-1234567890");
    }
}
