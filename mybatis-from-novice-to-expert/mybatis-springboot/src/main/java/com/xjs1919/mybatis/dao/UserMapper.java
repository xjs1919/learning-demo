package com.xjs1919.mybatis.dao;

import com.xjs1919.mybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 若鱼1919
 * @date 2022/2/27 0027 13:45
 */
@Mapper
public interface UserMapper {

    User selectById(Integer id);

    int insert(User u);

    int update(User u);

}
