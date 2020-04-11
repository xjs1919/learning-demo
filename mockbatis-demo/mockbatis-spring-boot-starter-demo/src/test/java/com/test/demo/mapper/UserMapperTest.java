package com.test.demo.mapper;

import com.test.demo.DemoApplicationTest;
import com.test.demo.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends DemoApplicationTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void testSelectById(){
       User user = mapper.selectById(1);
       System.out.println(user);
    }
}
