package com.github.xjs.openfeign.boot.client.controller.hystrix.isolate;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/8/15 13:36
 */
public class InterceptorConfigure {

    @Bean
    public RequestInterceptor requestInterceptor(){
         return new UserInterceptor();
    }

    public static class UserInterceptor implements RequestInterceptor{
        @Override
        public void apply(RequestTemplate template) {
            String user = ClientIsolateController.UserHolder.getUser();
            System.out.println("InterceptorConfigure user:" + user);
        }
    }
}
