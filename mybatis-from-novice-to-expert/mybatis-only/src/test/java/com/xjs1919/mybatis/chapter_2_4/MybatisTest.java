package com.xjs1919.mybatis.chapter_2_4;

import com.xjs1919.mybatis.entity.User;
import com.xjs1919.mybatis.mapper.chapter_2_4.UserMapper;
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
import java.util.List;

/**
 * @author 若鱼1919
 * @date 2022/02/10 19:49
 */
public class MybatisTest {

    private SqlSession sqlSession;
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void after() throws Exception {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    @Test
    public void testIf(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectByNameAndGender("xjs", 1);
        System.out.println(users);
        users = userMapper.selectByNameAndGender(null, 1);
        System.out.println(users);
    }

    @Test
    public void testChoose(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectByNameThenGender(null, 1);
        System.out.println(users);
    }

    @Test
    public void testForEach(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectByIds(Arrays.asList(1,2,3));
        System.out.println(users);
    }

    @Test
    public void testWhere(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectByNameAndGenderUsWhere(null, 1);
        System.out.println(users);
    }

    @Test
    public void testSet(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User u = new User();
        u.setId(1);
        u.setName("xjs-new");
        userMapper.update(u);
        System.out.println(userMapper.selectByIds(Arrays.asList(1)));
    }

    @Test
    public void testTrim(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User u = new User();
        u.setName("xxx");
        u.setGender(1);
        u.setBirth(new Date());
        userMapper.insert(u);
        System.out.println(userMapper.selectByIds(Arrays.asList(u.getId())));
    }

    @Test
    public void testTrimInsteadOfWhere(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectByNameAndGenderUsTrimInsteadOfWhere(null, null);
        System.out.println(users);
    }

    @Test
    public void testTrimInsteadOfSet(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User u = new User();
        u.setId(1);
        u.setGender(100);
        userMapper.updateUsTrimInsteadOfSet(u);
        System.out.println(userMapper.selectByIds(Arrays.asList(1)));
    }
}
