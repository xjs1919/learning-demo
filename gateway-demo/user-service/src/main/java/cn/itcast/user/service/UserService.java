package cn.itcast.user.service;

import cn.itcast.user.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    public User queryById(Long id) {
        log.info("===========查询id={}的用户", id);
        return new User(id, "hello", "bj");
    }
}