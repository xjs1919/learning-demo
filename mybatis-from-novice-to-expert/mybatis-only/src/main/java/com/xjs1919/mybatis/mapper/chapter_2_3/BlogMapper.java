package com.xjs1919.mybatis.mapper.chapter_2_3;

import com.xjs1919.mybatis.entity.Blog;

/**
 * @author jiashuai.xujs
 * @date 2022/2/28 19:00
 */
public interface BlogMapper {

    Blog selectById(int id);

    Blog selectById2(int id);
}
