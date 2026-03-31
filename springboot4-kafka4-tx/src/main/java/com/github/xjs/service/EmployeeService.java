package com.github.xjs.service;

import com.github.xjs.entity.Employee;
import com.github.xjs.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public Long register(String name, String email) {
        // 发送kafka消息
        kafkaTemplate.send("employee", "employee registed:" + name);
        // 保存到数据库
        Employee employee = new Employee(name, email);
        employeeRepository.save(employee);
        if(name.equals("admin")){
            throw new RuntimeException("不允许使用admin作为名称");
        }
        return employee.getId();
    }

    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public Long register2(String name, String email) {
        // 发送kafka消息
        sendKafka(name);
        // 保存到数据库
        Employee employee = new Employee(name, email);
        employeeRepository.save(employee);
        if(name.equals("admin")){
            throw new RuntimeException("不允许使用admin作为名称");
        }
        return employee.getId();
    }

    @Transactional(rollbackFor = Exception.class, transactionManager = "kafkaTransactionManager")
    public void sendKafka(String name){
        // 发送kafka消息
        kafkaTemplate.send("employee", "employee registed:" + name);
    }

}
