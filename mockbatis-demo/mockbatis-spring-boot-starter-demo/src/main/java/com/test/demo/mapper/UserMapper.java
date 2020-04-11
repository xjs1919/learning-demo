package com.test.demo.mapper;

import com.github.xjs.mockbatis.config.MockMapper;
import com.github.xjs.mockbatis.config.Select;
import com.test.demo.domain.User;

@MockMapper
public interface UserMapper {

    @Select("select * from user where id = #1")
    public User selectById(int id);

}
