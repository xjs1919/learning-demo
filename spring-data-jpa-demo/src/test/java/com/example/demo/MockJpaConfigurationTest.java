package com.example.demo;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 若鱼1919
 * @date 2022/5/8 0008 10:03
 */
@SpringJUnitConfig(classes = MockJpaConfiguration.class)
public class MockJpaConfigurationTest {
    @Autowired
    private CustomerRepository repository;
    @Test
    @Transactional(readOnly = true)
    public void testFindById(){
        Customer customer = repository.findById(1L).orElse(null);
        System.out.println(customer);
    }
}
