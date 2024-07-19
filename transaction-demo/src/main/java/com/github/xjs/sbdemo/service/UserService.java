package com.github.xjs.sbdemo.service;

import com.github.xjs.sbdemo.entity.User;
import com.github.xjs.sbdemo.event.UserRegisterEvent;
import com.github.xjs.sbdemo.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {

    private static Logger log = LoggerFactory.getLogger(UserService.class);

    private AtomicInteger id = new AtomicInteger(0);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void postConstruct() {
        log.info("init table start...");
        boolean exist = (userMapper.isTableExist() > 0);
        if(!exist) {
            userMapper.createTable();
        }else {
            userMapper.dropTable();
            userMapper.createTable();
        }
        log.info("init table over!!!");
    }

    @Autowired
    PlatformTransactionManager txManager;

    @Transactional(rollbackFor = Exception.class)
    public int register() {
        // 注册用户
        User emp  = new User(id.incrementAndGet(), "若鱼1919", 1);
        int result  = userMapper.insert(emp);
        // 异步发送邮件
        applicationContext.publishEvent(new UserRegisterEvent(this, emp.getId()));
        //emailService.sendEmail(emp.getId());
        // 模拟代码执行受塞
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 编程式事务处理
     * */
    public int register2() {
        User emp = null;
        int result = 0;
        // 手动开启事务
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transaction = txManager.getTransaction(transactionDefinition);
        try{
            // 注册用户
            emp = new User(id.incrementAndGet(), "若鱼1919", 1);
            result = userMapper.insert(emp);
            txManager.commit(transaction);
        }catch(Exception e){
            txManager.rollback(transaction);
            throw new RuntimeException(e);
        }
        // 异步发送邮件
        log.info("准备发送邮件");
        // applicationContext.publishEvent(new UserRegisterEvent(this, emp.getId()));
        emailService.sendEmail(emp.getId());
        // 模拟代码执行受塞
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public List<User> all() {
        return userMapper.listAll();
    }
}
