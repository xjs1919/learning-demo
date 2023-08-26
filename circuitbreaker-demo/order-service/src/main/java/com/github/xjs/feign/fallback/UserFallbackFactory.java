package com.github.xjs.feign.fallback;

import com.github.xjs.feign.UserClient;
import com.github.xjs.pojo.User;
import org.springframework.cloud.openfeign.FallbackFactory;


public class UserFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable cause) {

        return new UserClient(){
            @Override
            public User queryById(Long id) {
                return new User(0L, "fallback user", null);
            }
        };
    }
}
