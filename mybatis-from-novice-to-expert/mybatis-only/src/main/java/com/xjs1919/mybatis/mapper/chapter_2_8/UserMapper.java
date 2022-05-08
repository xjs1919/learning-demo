package com.xjs1919.mybatis.mapper.chapter_2_8;

import com.xjs1919.mybatis.entity.User;

import java.util.List;

/**
 * @author 若鱼1919
 * @date 2022/4/4 0004 11:40
 */
public interface UserMapper {
    List<User> selectById(Integer id);
}
