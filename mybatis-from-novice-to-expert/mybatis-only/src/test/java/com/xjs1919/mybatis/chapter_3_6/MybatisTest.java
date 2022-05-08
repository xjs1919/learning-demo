package com.xjs1919.mybatis.chapter_3_6;

import com.xjs1919.mybatis.entity.User;
import com.xjs1919.mybatis.mapper.chapter_3_6.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 若鱼1919
 * @date 2022/02/10 19:49
 */
public class MybatisTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("chapter_3_6/mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    private List<User> getUpdateUsers(){
        List<User> users = new ArrayList<User>();
        User u = new User(1, "xjs-new", 0);
        users.add(u);
        u = new User(2,"hello-new", 0 );
        users.add(u);
        u = new User(3,"world-new", 0);
        users.add(u);
//        u = new User(4,"Joshua-new", 0);
//        users.add(u);
        return users;
    }

    private List<User> getUpdateUsersForReset(){
        List<User> users = new ArrayList<User>();
        User u = new User(1, "xjs", 1);
        users.add(u);
        u = new User(2,"hello", 1);
        users.add(u);
        u = new User(3,"world", 1);
        users.add(u);
        u = new User(4, "Joshua", 1);
        users.add(u);
        return users;
    }

    @Test
    public void testUpdateReset(){
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = getUpdateUsersForReset();
        for(User u : users){
            userMapper.update(u);
        }
        sqlSession.commit();
        System.out.println(userMapper.selectByIds(Arrays.asList(1,2,3)));
    }

    @Test
    public void testUpdateBatchUseCaseWhen() throws Exception {
        List<User> users = getUpdateUsers();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateBatchUseCaseWhen(users);
        sqlSession.commit();
        System.out.println(userMapper.selectByIds(Arrays.asList(1,2,3)));
    }

    @Test
    public void testBatchUpdateUseBatchMode(){
        // ExecutorType.BATCH -> BatchExecutor
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        List<User> users = getUpdateUsers();
        for(User u : users){
            // stmt.addBatch();
            sqlSession.update("com.xjs1919.mybatis.mapper.chapter_3_6.UserMapper.update", u);
        }
        // stmt.executeBatch();
        sqlSession.commit();
    }
}
