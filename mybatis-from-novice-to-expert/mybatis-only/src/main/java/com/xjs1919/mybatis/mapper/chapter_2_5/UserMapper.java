package com.xjs1919.mybatis.mapper.chapter_2_5;

import com.xjs1919.mybatis.entity.User;

import java.util.List;

/**
 * @author 若鱼1919
 * @date 2022/2/27 0027 13:45
 */
public interface UserMapper {

    List<User> selectByIds(List<Integer> ids);

    int update(User u);

    int insert(User u);

    int insertBatch(List<User> users);

    int updateBatch(List<User> users);

    int batchUpdateUseUpdateMultiTables(List<User> users);

    int updateBatchUseCaseWhen(List<User> users);
}
