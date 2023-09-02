package com.github.xjs.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignRelayUserInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        Long userId = UserContext.getUserId();
        if (userId == null) {
            return;
        }
        template.header("userId", userId.toString());
    }
}
