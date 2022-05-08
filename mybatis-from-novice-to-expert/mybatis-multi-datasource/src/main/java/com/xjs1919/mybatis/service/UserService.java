package com.xjs1919.mybatis.service;

import com.xjs1919.mybatis.dao.ds1.UserMapper;
import com.xjs1919.mybatis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jiashuai.xujs
 * @date 2022/3/31 10:52
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User selectById(Integer id){
        return userMapper.selectById(id);
    }

}
