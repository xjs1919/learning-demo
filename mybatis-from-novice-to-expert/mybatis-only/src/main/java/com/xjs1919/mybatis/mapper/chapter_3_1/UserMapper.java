package com.xjs1919.mybatis.mapper.chapter_3_1;

import com.xjs1919.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiashuai.xujs
 * @date 2022/3/14 14:08
 */
public interface UserMapper {

    User selectById(@Param("id")Integer id);

    List<User> selectByNameAndGender(@Param("name")String name, @Param("gender")Integer gender);

    List<User> selectByIds(@Param("ids")List<Integer> ids);

    List<User> selectByIdsAndNameAndGender(List<Integer> ids, String name, Integer gender);
}
