package com.xjs1919.mybatis.chapter_2_1;

import com.xjs1919.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
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

    @Test
    public void testSelectById() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        User u = sqlSession.selectOne("userMapper.selectById", 1);
        System.out.println(u);
        sqlSession.close();
    }


















    private SqlSession sqlSession;

    @Before
    public void before() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
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
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        users = userMapper.selectAll();
//        System.out.println(users);
    }

    @Test
    public void testInsert() {
        User u = new User();
        u.setId(6);
        u.setGender(1);
        u.setName("6666");
        u.setBirth(new Date());
        sqlSession.insert("userMapper.insert", u);
        sqlSession.commit();
    }

    @Test
    public void testUpdate() {
        User u = new User();
        u.setId(5);
        u.setGender(0);
        u.setName("xxx");
        u.setBirth(new Date());
        sqlSession.update("userMapper.update", u);
        sqlSession.commit();
    }

    @Test
    public void testDelete() {
        User u = new User();
        u.setId(5);
        sqlSession.delete("userMapper.delete", u);
        sqlSession.commit();
    }

    @Test
    public void testSelectUseIn() throws Exception {
        List<User> users = sqlSession.selectList("userMapper.selectUseIn", Arrays.asList(1,2,3));
        System.out.println(users);
    }

    @Test
    public void testSelectUseLike() throws Exception {
        List<User> users = sqlSession.selectList("userMapper.selectUseLike", "xjs");
        System.out.println(users);
    }

    @Test
    public void testSelectUseLike2() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xjs");
        List<User> users = sqlSession.selectList("userMapper.selectUseLike", map);
        System.out.println(users);
    }

    @Test
    public void testSelectUseLike3() throws Exception {
        User u = new User();
        u.setName("xjs");
        List<User> users = sqlSession.selectList("userMapper.selectUseLike", u);
        System.out.println(users);
    }

    /**
     * 如果想用回填id，ExecutorType != ExecutorType.BATCH
     * 如果不回填id还是可以用的
     * */
    @Test
    public void testInsertBatch() throws Exception {
        List<User> users = users = new ArrayList<User>();
        User u = new User("xjs", 1);
        users.add(u);
        u = new User("hello", 2);
        users.add(u);
        u = new User("world", 1);
        users.add(u);
        int ret = sqlSession.insert("userMapper.insertBatch", users);
        System.out.println(ret);
        System.out.println(users);
        sqlSession.commit();

    }

    /**
     * allowMultiQueries
     * */
    @Test
    public void testUpdateBatch() throws Exception {
        List<User> users = users = new ArrayList<User>();
        User u = new User(1, "xjs", 0);
        users.add(u);
        u = new User(2, "hello", 0);
        users.add(u);
        u = new User(3, "world", 0);
        users.add(u);
        u = new User(4, "444", 0);
        users.add(u);
        int ret = sqlSession.update("userMapper.updateBatch", users);
        System.out.println(ret);
        sqlSession.flushStatements();
        sqlSession.commit();
    }

    @Test
    public void testUpdateBatch2() throws Exception {
        List<User> users = users = new ArrayList<User>();
        User u = new User(1, "xjs-2", 0);
        users.add(u);
        u = new User(2, "hello-2", 0);
        users.add(u);
        int ret = sqlSession.update("userMapper.updateBatch2", users);
        System.out.println(ret);
        sqlSession.commit();
    }

    @Test
    public void testUpdateBatch3() throws Exception {
        List<User> users = users = new ArrayList<User>();
        User u = new User(1, "xjs-3", 0);
        users.add(u);
        u = new User(2, "hello-3", 0);
        users.add(u);
        int ret = sqlSession.update("userMapper.updateBatch3", users);
        System.out.println(ret);
        sqlSession.commit();
    }

    /**
     * rewriteBatchedStatements
     * */
    @Test
    public void testUpdateBatch4() throws Exception {
        List<User> users = users = new ArrayList<User>();
        User u = new User(1, "xjs-4", 0);
        users.add(u);
        u = new User(2, "hello-4", 0);
        users.add(u);
        u = new User(3, "world-4", 0);
        users.add(u);
        u = new User(4, "4444-4", 0);
        users.add(u);
        for(User user : users){
            int ret = sqlSession.update("userMapper.update", user);
            System.out.println(ret);
        }
        sqlSession.flushStatements();
        sqlSession.commit();
    }

    /**
     * rewriteBatchedStatements
     * */
    @Test
    public void testInsertBatch2() throws Exception {
        List<User> users = users = new ArrayList<User>();
        User u = new User( "xjs-5", 0);
        users.add(u);
        u = new User( "hello-5", 0);
        users.add(u);
        u = new User( "world-5", 0);
        users.add(u);
        for(User user : users){
            int ret = sqlSession.insert("userMapper.insert", user);
            System.out.println(ret);
        }
        sqlSession.flushStatements();
        sqlSession.commit();
        System.out.println(users);
    }
}

/*
对于批量的insert:
    普通模式
        insert values()
    BATCH模式
        for循环insert    rewriteBatchedStatements启用

对于批量update
    普通模式
            allowMultiQueries
    BATCH模式
         for循环update  rewriteBatchedStatements启用，数量大于3个
*/



