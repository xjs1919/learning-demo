package com.xjs1919.mybatis.mapper.chapter_3_6;

import com.xjs1919.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiashuai.xujs
 * @date 2022/3/14 14:08
 */
public interface UserMapper {

    List<User> selectByIds(List<Integer> ids);

    int update(User u);

    int updateBatchUseCaseWhen(List<User> users);

}
