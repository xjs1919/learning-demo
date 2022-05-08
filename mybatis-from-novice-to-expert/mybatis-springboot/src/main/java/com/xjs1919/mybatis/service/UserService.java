package com.xjs1919.mybatis.service;

import com.xjs1919.mybatis.dao.UserMapper;
import com.xjs1919.mybatis.domain.User;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiashuai.xujs
 * @date 2022/3/24 18:06
 */
@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Autowired
    private SqlSession sqlSession;
    public User selectByIdUseSqlSession(Integer id) {
        return sqlSession.getMapper(UserMapper.class).selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int saveUseTransactional(){
        User user = new User("xxx", 0);
        userMapper.insert(user);
        System.out.println(1/0);
        user.setName("xxx-new");
        userMapper.update(user);
        return user.getId();
    }

    @Resource(name = "transactionManager")
    private PlatformTransactionManager transactionManager;
    public int saveManualUseTransactionManager(){
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            User user = new User("xxx", 0);
            userMapper.insert(user);
            System.out.println(1/0);
            user.setName("xxx-new");
            userMapper.update(user);
            transactionManager.commit(txStatus);
            return user.getId();
        } catch (Exception e) {
            transactionManager.rollback(txStatus);
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Transactional
    public boolean batchUpdate(){
        List<User> users = getUpdateUsers();
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(sqlSessionFactory, ExecutorType.BATCH, null);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        for(User u : users){
            userMapper.update(u);
        }
        SqlSessionUtils.closeSqlSession(sqlSession, sqlSessionFactory);
        User user = new User("xxx", 0);
        userMapper.insert(user);
        System.out.println(1/0);
        return true;
    }

    private List<User> getUpdateUsers(){
        List<User> users = new ArrayList<User>();
        User u = new User(1, "xjs-new", 1);
        users.add(u);
        u = new User(2,"hello-new", 1);
        users.add(u);
        u = new User(3,"world-new", 1);
        users.add(u);
        return users;
    }

}
