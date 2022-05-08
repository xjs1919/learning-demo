package com.xjs1919.mybatis.mapper.chapter_2_7;


import com.xjs1919.mybatis.entity.User;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 若鱼1919
 * @date 2022/3/7 0007 22:03
 */
public interface UserMapper {

    @Select("<script>" +
            "select * from users where id in" +
            "<foreach collection=\"list\" open=\"(\" close=\")\" separator=\",\" item=\"id\">#{id}</foreach>" +
            "</script>")
    List<User> selectByIds(List<Integer> ids);

    @Select("select * from users where id in (#{ids}) ")
    @Lang(ForEachInLanguageDriver.class)
    List<User> selectByIdsUseLanguageDriver(@Param("ids") List<Integer> ids);

}
