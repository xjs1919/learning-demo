package com.xjs1919.mybatis;

import com.xjs1919.mybatis.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author 若鱼1919
 * @date 2022/3/25 0025 17:04
 */
@SpringJUnitConfig(classes = SpringContextConfig.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

//    @Test
//    public void testSave(){
//        Assertions.assertThrows(ArithmeticException.class,
//                ()->userService.save());
//    }
}
