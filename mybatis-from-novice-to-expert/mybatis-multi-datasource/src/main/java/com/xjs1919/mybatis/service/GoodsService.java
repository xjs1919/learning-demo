package com.xjs1919.mybatis.service;

import com.xjs1919.mybatis.dao.ds1.UserMapper;
import com.xjs1919.mybatis.dao.ds2.GoodsMapper;
import com.xjs1919.mybatis.domain.Goods;
import com.xjs1919.mybatis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author jiashuai.xujs
 * @date 2022/3/31 10:53
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserMapper userMapper;

    public Goods selectById(Integer id){
        return goodsMapper.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int transaction(){
        User u = new User();
        u.setId(1);
        u.setName("XXX");
        userMapper.update(u);

        Goods g = new Goods();
        g.setId(1);
        g.setName("XXX");
        goodsMapper.update(g);

        System.out.println(1/0);
        return 1;
    }

//    @Autowired
//    private DataSourceTransactionManager transactionManager1;
//    @Autowired
//    private DataSourceTransactionManager transactionManager2;
//    public int transaction2(){
//        TransactionStatus status1 = transactionManager1.getTransaction(new DefaultTransactionDefinition());
//        TransactionStatus status2 = transactionManager2.getTransaction(new DefaultTransactionDefinition());
//        try{
//            User u = new User();
//            u.setId(1);
//            u.setName("XXX");
//            userMapper.update(u);
//            Goods g = new Goods();
//            g.setId(1);
//            g.setName("XXX");
//            goodsMapper.update(g);
//            System.out.println(1/0);
//            transactionManager2.commit(status2);
//            transactionManager1.commit(status1);
//            return 1;
//        }catch(Throwable e){
//            transactionManager2.rollback(status2);
//            transactionManager1.rollback(status1);
//            throw new RuntimeException(e);
//        }
//    }
}
