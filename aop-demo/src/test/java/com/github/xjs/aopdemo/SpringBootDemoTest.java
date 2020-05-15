package com.github.xjs.aopdemo;

import com.github.xjs.aopdemo.service.IService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootDemoTest {

    @Autowired
    IService service;

    @Test
    public void testAop(){
        System.out.println(service.getClass());
        //class com.github.xjs.aopdemo.service.Service1$$EnhancerBySpringCGLIB$$f107fe5e
    }
}
