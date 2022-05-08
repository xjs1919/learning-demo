package com.xjs1919.mybatis.chapter_3_1;

import com.xjs1919.mybatis.entity.User;
import com.xjs1919.mybatis.mapper.chapter_3_1.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author 若鱼1919
 * @date 2022/02/10 19:49
 * 运行时获取方法的参数名：https://blog.csdn.net/goldenfish1919/article/details/107123204
 */
public class MybatisTest {

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;


    @Before
    public void before() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("chapter_3_1/mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void after() throws Exception {
        if(sqlSession != null){
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User u = userMapper.selectById(1);
        System.out.println(u);

    }

    @Test
    public void testSelectByIds(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.selectByIds(Arrays.asList(1));
        System.out.println(list);

    }

    @Test
    public void testSelectByNameAndGender(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.selectByNameAndGender("xjs", 1);
        System.out.println(list);
    }

    @Test
    public void testSelectByIdsAndNameAndGender(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.selectByIdsAndNameAndGender(Arrays.asList(1), "xjs", 1);
        System.out.println(list);
    }

}
