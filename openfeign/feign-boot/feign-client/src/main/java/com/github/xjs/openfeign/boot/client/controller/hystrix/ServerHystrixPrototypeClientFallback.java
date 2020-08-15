package com.github.xjs.openfeign.boot.client.controller.hystrix;

import org.springframework.stereotype.Service;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/8/15 13:11
 */
@Service
public class ServerHystrixPrototypeClientFallback implements ServerHystrixPrototypeClient{
    @Override
    public String prototype() {
        return "ServerHystrixPrototypeClientFallabck prototype";
    }
}
