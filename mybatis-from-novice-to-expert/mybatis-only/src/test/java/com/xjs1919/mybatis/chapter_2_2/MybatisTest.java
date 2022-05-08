package com.xjs1919.mybatis.chapter_2_2;

import com.xjs1919.mybatis.entity.User;
import com.xjs1919.mybatis.mapper.chapter_2_2.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.*;

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

    @Test
    public void testSelectAll() throws Exception {
        List<User> users = sqlSession.selectList("userMapper.selectAll");
        System.out.println(users);
    }

    @Test
    public void testSelectById() throws Exception {
        User u = sqlSession.selectOne("userMapper.selectById", 1);
        System.out.println(u);
    }

    @Test
    public void testSelectByMap() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "xjs");
        params.put("gender", 1);
        List<User> users = sqlSession.selectList("userMapper.selectByParams", params);
        System.out.println(users);
    }

    @Test
    public void testSelectByBean() throws Exception {
        User param = new User();
        param.setName("xjs");
        param.setGender(1);
        List<User> users = sqlSession.selectList("userMapper.selectByParams", param);
        System.out.println(users);
    }


    @Test
    public void testSelectUseIn() throws Exception {
        List<User> users = sqlSession.selectList("userMapper.selectUseIn", Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    @Test
    public void testSelectUseLike() throws Exception {
        List<User> users = sqlSession.selectList("userMapper.selectUseLike", "xjs");
        System.out.println(users);
        //select * from users where name like concat('%', ?, '%')
    }

    @Test
    public void testSelectUseLike2() throws Exception {
        List<User> users = sqlSession.selectList("userMapper.selectUseLike2", "xjs");
        System.out.println(users);
        //select * from users where name like '%xjs%'
    }

    @Test
    public void testSelectUseMapper() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User u = userMapper.selectById(1);
        System.out.println(u);
    }

    @Test
    public void testSelectUseMapperParam() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.selectByNameAndGender("xjs", 1);
        System.out.println(list);
    }

    @Test
    public void testSelectUseInMapper() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.selectUseIn(Arrays.asList(1,2,3));
        System.out.println(list);
    }

    @Test
    public void testInsert() {
        User u = new User();
        u.setGender(1);
        u.setName("6666");
        u.setBirth(new Date());
        sqlSession.insert("userMapper.insert", u);
        sqlSession.commit();
    }

    @Test
    public void testUpdate() {
        User u = new User();
        u.setId(15);
        u.setGender(0);
        u.setName("xxx");
        u.setBirth(new Date());
        sqlSession.update("userMapper.update", u);
        sqlSession.commit();
    }

    @Test
    public void testDelete() {
        User u = new User();
        u.setId(1);
        sqlSession.delete("userMapper.delete", u);
        sqlSession.commit();
    }

    @Test
    public void testTransaction() {
        try{
            User u = new User();
            u.setGender(1);
            u.setName("6666");
            u.setBirth(new Date());
            sqlSession.insert("userMapper.insert", u);

            System.out.println(1/0);

            u.setId(2);
            sqlSession.delete("userMapper.delete", u);

            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
    }
}

