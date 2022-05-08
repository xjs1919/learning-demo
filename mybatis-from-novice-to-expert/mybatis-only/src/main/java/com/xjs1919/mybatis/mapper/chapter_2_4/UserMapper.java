package com.xjs1919.mybatis.mapper.chapter_2_4;

import com.xjs1919.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 若鱼1919
 * @date 2022/2/27 0027 13:45
 */
public interface UserMapper {

    List<User> selectByNameAndGender(@Param("name")String name,
                                     @Param("gender")Integer gender);

    List<User> selectByNameThenGender(@Param("name")String name,
                                     @Param("gender")Integer gender);


    List<User> selectByIds(List<Integer> ids);

    List<User> selectByNameAndGenderUsWhere(@Param("name")String name,
                                      @Param("gender")Integer gender);


    int update(User u);

    int insert(User u);

    List<User> selectByNameAndGenderUsTrimInsteadOfWhere(@Param("name")String name,
                                      @Param("gender")Integer gender);

    int updateUsTrimInsteadOfSet(User u);


}
