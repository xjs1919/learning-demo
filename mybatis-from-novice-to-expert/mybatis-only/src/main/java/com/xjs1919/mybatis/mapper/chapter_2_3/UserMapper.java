package com.xjs1919.mybatis.mapper.chapter_2_3;

import com.xjs1919.mybatis.entity.User;

/**
 * @author jiashuai.xujs
 * @date 2022/2/28 19:02
 */
public interface UserMapper {

    User selectById(Integer id);
}
