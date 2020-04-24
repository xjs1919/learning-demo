package com.github.xjs.importdemo;

import com.github.xjs.importdemo.service.Service1;
import com.github.xjs.importdemo.service.Service2;
import com.github.xjs.importdemo.service.Service3;
import com.github.xjs.importdemo.service.Service4;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ImportDemoApplicationTests {

    @Autowired
    private Service1 service1;

    @Autowired
    private Service2 service2;

    @Autowired
    private Service3 service3;

    @Autowired
    private Service4 service4;


    @Test
    public void test(){
        System.out.println(service1);
        System.out.println(service2);
        System.out.println(service3);
        System.out.println(service4);
    }

}
