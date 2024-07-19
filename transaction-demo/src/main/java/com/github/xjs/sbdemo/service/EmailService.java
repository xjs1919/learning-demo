package com.github.xjs.sbdemo.service;

import com.github.xjs.sbdemo.entity.User;
import com.github.xjs.sbdemo.event.UserRegisterEvent;
import com.github.xjs.sbdemo.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EmailService {

    private static Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private UserMapper userMapper;

    @Async
    public void sendEmail(int userId) {
        User user = userMapper.selectById(userId);
        if(user == null){
            throw new RuntimeException("user信息不存在");
        }
        logger.info("向id是{}的用户{}发送邮件成功", userId, user.getName());
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = UserRegisterEvent.class)
    public void onRegisterEvent(UserRegisterEvent event) {
        sendEmail(event.getUserId());
    }
}
