package com.xjs1919.mybatis.mapper.chapter_3_3;

import com.xjs1919.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author jiashuai.xujs
 * @date 2022/3/14 14:08
 */
public interface UserMapper {

    User selectById(@Param("id")Integer id);

}
