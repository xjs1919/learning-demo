package com.xjs1919.mybatis.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.xjs1919.mybatis.dao.UserMapper;
import com.xjs1919.mybatis.domain.User;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiashuai.xujs
 * @date 2022/3/24 18:06
 */
@Service
public class UserService extends SqlSessionDaoSupport{

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public User selectById(Integer id) {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(sqlSessionFactory);
        User u = sqlSession.selectOne("com.xjs1919.mybatis.dao.UserMapper.selectById", id);
        System.out.println(u);
        u = sqlSession.getMapper(UserMapper.class).selectById(1);
        System.out.println(u);
        SqlSessionUtils.closeSqlSession(sqlSession, sqlSessionFactory);
        return u;
    }

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    public User selectByIdUseTemplate(Integer id) {
        User u = sqlSessionTemplate.selectOne("com.xjs1919.mybatis.dao.UserMapper.selectById", id);
        System.out.println(u);
        u = sqlSessionTemplate.getMapper(UserMapper.class).selectById(id);
        System.out.println(u);
        return u;
    }

    /**要想使用SqlSessionDaoSupport，必须要注入sqlSessionFactory或者sqlSessionTemplate*/
    @PostConstruct
    public void postConstruct(){
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    public User selectByIdUseDaoSupport(Integer id) {
        User u = getSqlSession().selectOne("com.xjs1919.mybatis.dao.UserMapper.selectById", id);
        return u;
    }

    @Autowired
    private UserMapper userMapper;

    public User selectByIdUseSingleMapper(Integer id) {
        return userMapper.selectById(id);
    }

    public User selectByIdUseMapperScan(Integer id) {
        return userMapper.selectById(id);
    }

    @Transactional
    public User selectByIdUseTransaction(Integer id) {
        User user = userMapper.selectById(id);
        System.out.println(user);
        user = userMapper.selectById(id);
        System.out.println(user);
        return user;
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

    public int saveManualUseTransactionTemplate(){
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        return transactionTemplate.execute((txStatus)->{
            User user = new User("xxx", 0);
            userMapper.insert(user);
            System.out.println(1/0);
            user.setName("xxx-new");
            userMapper.update(user);
            return user.getId();
        });
    }

    @Autowired
    private DataSource dataSource;
    public boolean batchUpdate(){
        List<User> users = getUpdateUsers();
        PersistenceExceptionTranslator exceptionTranslator = new MyBatisExceptionTranslator(dataSource, true);
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            SqlSession sqlSession = SqlSessionUtils.getSqlSession(sqlSessionFactory, ExecutorType.BATCH, exceptionTranslator);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            for(User u : users){
                userMapper.update(u);
            }
            SqlSessionUtils.closeSqlSession(sqlSession, sqlSessionFactory);
            transactionManager.commit(status);
            return true;
        }catch(Exception e){
            transactionManager.rollback(status);
            return false;
        }
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
