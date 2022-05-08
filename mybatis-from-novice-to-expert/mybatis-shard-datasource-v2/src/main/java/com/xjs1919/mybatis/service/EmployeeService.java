package com.xjs1919.mybatis.service;

import com.xjs1919.mybatis.dao.EmployeeMapper;
import com.xjs1919.mybatis.domain.Employee;
import com.xjs1919.mybatis.shard.TenantHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jiashuai.xujs
 * @date 2022/3/31 10:52
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee selectById(Integer id){
        return employeeMapper.selectById(id, TenantHolder.getTenantId());
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean update(){
        Employee employee = selectById(1);
        employee.setName("xxx");
        int ret = employeeMapper.update(employee);
        System.out.println(1/0);
        return ret > 0;
    }

}
