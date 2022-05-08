package com.xjs1919.mybatis.chapter_2_5;

import com.xjs1919.mybatis.entity.User;
import com.xjs1919.mybatis.mapper.chapter_2_5.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * @author 若鱼1919
 * @date 2022/02/10 19:49
 */
public class MybatisTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    @After
    public void after() throws Exception {

    }

    private List<User> getInsertUsers(){
        List<User> users = new ArrayList<User>();
        User u = new User("xjs", 1);
        users.add(u);
        u = new User("hello", 2);
        users.add(u);
        u = new User("world", 1);
        users.add(u);
        return users;
    }

    private List<User> getUpdateUsers(){
        List<User> users = new ArrayList<User>();
        User u = new User(1, null, 0);
        users.add(u);
        u = new User(2,"hello-222", 0 );
        users.add(u);
        u = new User(3,null, 0);
        users.add(u);
        u = new User(4,null, 0);
        users.add(u);
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
        return users;
    }


    @Test
    public void testBatchInsert(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = getInsertUsers();
        userMapper.insertBatch(users);
        sqlSession.commit();
        System.out.println(users);
        sqlSession.close();
    }

    @Test
    public void testBatchInsertUseJDBC()throws Exception{
        List<User> users = getInsertUsers();
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mybatis_from_novice_to_expert?useSSL=false&serverTimezone=Asia/Shanghai",
                "root",
                "123456");
        PreparedStatement stmt = conn.prepareStatement("insert into users(name,gender) values(?,?)", Statement.RETURN_GENERATED_KEYS);
        for(User u : users){
            stmt.setString(1, u.getName());
            stmt.setInt(2, u.getGender());
            stmt.addBatch();
        }
        stmt.execute();
        ResultSet rs = stmt.getGeneratedKeys();
        int i = 0;
        while(rs.next()){
            int id = rs.getInt(1);
            users.get(i++).setId(id);
        }
        System.out.println(users);
        rs.close();
        stmt.close();
        conn.close();
    }

    @Test
    public void testBatchUpdateUseJDBC()throws Exception{
        List<User> users = getUpdateUsers();
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mybatis_from_novice_to_expert?rewriteBatchedStatements=true&useSSL=false&serverTimezone=Asia/Shanghai",
                "root",
                "123456");
        PreparedStatement stmt = conn.prepareStatement("update users set name = ? , gender = ?");
        for(User u : users){
            stmt.setString(1, u.getName());
            stmt.setInt(2, u.getGender());
            stmt.addBatch();
        }
        stmt.executeBatch();
        stmt.close();
        conn.close();
    }

    /**
     * wireshark
     * rewriteBatchedStatements
     * */
    @Test
    public void testBatchInsertUseBatchMode(){
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = getInsertUsers();
        for(int i=0; i<users.size(); i++){
            User user = users.get(i);
            userMapper.insert(user);
            System.out.println(user.getId());
        }
        sqlSession.commit();
        sqlSession.close();
        System.out.println(users);
    }

    /**
     * allowMultiQueries
     * */
    @Test
    public void testBatchUpdate(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = getUpdateUsers();
        userMapper.updateBatch(users);
        sqlSession.commit();
        System.out.println(userMapper.selectByIds(Arrays.asList(1,2,3)));
    }

    @Test
    public void testBatchUpdateUseBatchMode(){
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = getUpdateUsers();
        for(User u : users){
            userMapper.update(u);
        }
        sqlSession.commit();
        System.out.println(userMapper.selectByIds(Arrays.asList(1,2,3)));
    }

    /**
      update users a, ( select 1 as id, 'xjs-new' as name, 0 as gender )b
      set a.name = b.name
      where a.id = b.id
     * */
    @Test
    public void testBatchUpdateUseUpdateMultiTables(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = getUpdateUsers();
        userMapper.batchUpdateUseUpdateMultiTables(users);
        sqlSession.commit();
        System.out.println(userMapper.selectByIds(Arrays.asList(1,2,3)));
    }

    /**
      update users
      set name = case id when 1 then 'xjs-new'
                         when 2 then 'hello-new'
                         when 3 then 'world'
                         else name
                  end,
       gender = case id when 1 then 1,
                        when 2 then 2,
                        when 3 then 3
                        else gender
      where id in(1,2,3)
     * */
    @Test
    public void testUpdateBatchUseCaseWhen() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = getUpdateUsers();
        userMapper.updateBatchUseCaseWhen(users);
        sqlSession.commit();
        System.out.println(userMapper.selectByIds(Arrays.asList(1,2,3)));
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
}
