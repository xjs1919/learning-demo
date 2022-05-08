package com.xjs1919.mybatis.controller;

import com.xjs1919.mybatis.domain.Employee;
import com.xjs1919.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiashuai.xujs
 * @date 2022/3/31 10:15
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("selectById")
    public Employee userSelectById(@RequestParam("id")Integer id){
        return employeeService.selectById(id);
    }

    @GetMapping("update")
    public boolean update(){
        return employeeService.update();
    }

}
