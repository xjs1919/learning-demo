package com.github.xjs.controller;

import com.github.xjs.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/register")
    public Long register(@RequestParam("name") String name,
                         @RequestParam("email") String email)throws Exception{
        return employeeService.register2(name, email);
    }

}
