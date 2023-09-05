package com.github.xjs.service;

import com.github.xjs.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    public User queryById(Long id) {
        log.info("==========查询id={}的用户", id);
        if(id % 2 == 0){
            return new User(id, "hello", "bj");
        }else{
            log.error("查询用户信息异常");
            throw new RuntimeException("查询用户信息异常");
        }
    }
}