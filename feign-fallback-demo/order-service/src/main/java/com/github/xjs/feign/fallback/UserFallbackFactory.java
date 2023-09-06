package com.github.xjs.feign.fallback;

import com.github.xjs.feign.UserClient;
import com.github.xjs.pojo.User;
import feign.hystrix.FallbackFactory;


public class UserFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable cause) {

        return new UserClient(){
            @Override
            public User queryById(Long id) {
                return new User(0L, "fallback user", "fallback address");
            }
        };
    }
}
