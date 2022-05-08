package com.xjs1919.mybatis.mapper.chapter_2_6;


import com.xjs1919.mybatis.entity.chapter_2_6.User;

import java.util.List;

/**
 * @author 若鱼1919
 * @date 2022/3/7 0007 22:03
 */
public interface User2Mapper {

    User selectById(int id);

    List<User> selectByNameAndGender(String name, int gender);

}
