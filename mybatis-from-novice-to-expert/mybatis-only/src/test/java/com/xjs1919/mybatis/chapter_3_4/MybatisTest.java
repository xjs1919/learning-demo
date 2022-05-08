package com.xjs1919.mybatis.chapter_3_4;

import com.xjs1919.mybatis.entity.User;
import com.xjs1919.mybatis.mapper.chapter_3_3.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author 若鱼1919
 * @date 2022/02/10 19:49
 */
public class MybatisTest {

    @Test
    public void testSelectById() throws Exception{
        InputStream inputStream = Resources.getResourceAsStream("chapter_3_4/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User u = sqlSession.selectOne("userMapper.selectById", 1);
        System.out.println(u);
        sqlSession.close();
    }
}
