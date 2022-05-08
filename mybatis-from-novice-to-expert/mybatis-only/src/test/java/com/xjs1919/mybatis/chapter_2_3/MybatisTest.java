package com.xjs1919.mybatis.chapter_2_3;

import com.xjs1919.mybatis.entity.Article;
import com.xjs1919.mybatis.entity.Blog;
import com.xjs1919.mybatis.entity.User;
import com.xjs1919.mybatis.mapper.chapter_2_2.UserMapper;
import com.xjs1919.mybatis.mapper.chapter_2_3.ArticleMapper;
import com.xjs1919.mybatis.mapper.chapter_2_3.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 若鱼1919
 * @date 2022/02/10 19:49
 */
public class MybatisTest {

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    @Before
    public void before() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession(true);
    }

    @After
    public void after() throws Exception {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }


    /**
     *
     * 一对一关联
     */
    @Test
    public void testOneToOneRelation(){
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        Article article = articleMapper.selectById(2);
        System.out.println(article);
    }

    /**
     * n+1查询 不推荐
     * */
    @Test
    public void testOneToOneRelation2(){
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        Article article = articleMapper.selectById2(2);
        System.out.println(article);
    }

    /**
     *
     * 一对多关联
     */
    @Test
    public void testOneToManyRelation(){
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectById(1);
        System.out.println(blog);
    }

    /**
     *
     * 一n+1查询 不推荐
     */
    @Test
    public void testOneToManyRelation2(){
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectById2(1);
        System.out.println(blog);
    }

    /**
     * 自动映射 autoMapping
     * */
    @Test
    public void testAutoMapping(){
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        Article article = articleMapper.selectById3(2);
        System.out.println(article);
    }

    /**
     * 自动映射 autoMapping
     * */
    @Test
    public void testAutoMapping2(){
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        Article article = articleMapper.selectById4(2);
        System.out.println(article);
    }
}

