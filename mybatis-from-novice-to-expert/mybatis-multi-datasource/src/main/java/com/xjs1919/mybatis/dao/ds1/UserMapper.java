package com.xjs1919.mybatis.dao.ds1;

import com.xjs1919.mybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author jiashuai.xujs
 * @date 2022/3/31 10:50
 */
@Mapper
public interface UserMapper {

    @Select("select * from users where id = #{id}")
    public User selectById(@Param("id")Integer id);

    public int update(User user);
}
