package com.github.xjs.mybatisparam.mapper;

import com.github.xjs.mybatisparam.entity.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeptMapperTest {
    @Autowired
    private DeptMapper deptMapper;
    @Test
    public void testSelectByNameAndCode() {
        List<Dept> depts = deptMapper.selectByNameAndCode("学工部", "XUEGONG");
        System.out.println(depts);
    }
}