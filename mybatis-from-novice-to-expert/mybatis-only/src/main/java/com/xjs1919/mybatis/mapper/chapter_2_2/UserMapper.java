package com.xjs1919.mybatis.mapper.chapter_2_2;

import com.xjs1919.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 若鱼1919
 * @date 2022/2/27 0027 13:45
 */
public interface UserMapper {

    User selectById(Integer id);

    List<User> selectByNameAndGender(@Param("name")String name,
                                     @Param("gender")Integer gender);

    List<User> selectUseIn(@Param("ids") List<Integer> ids);

}
