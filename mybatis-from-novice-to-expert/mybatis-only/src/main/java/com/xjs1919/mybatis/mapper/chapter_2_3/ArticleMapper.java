package com.xjs1919.mybatis.mapper.chapter_2_3;

import com.xjs1919.mybatis.entity.Article;

import java.util.List;

/**
 * @author jiashuai.xujs
 * @date 2022/2/28 19:01
 */
public interface ArticleMapper {

    Article selectById(int id);

    Article selectById2(int id);

    List<Article> selectByBlogId(Integer blogId);

    Article selectById3(int id);

    Article selectById4(int id);

}
