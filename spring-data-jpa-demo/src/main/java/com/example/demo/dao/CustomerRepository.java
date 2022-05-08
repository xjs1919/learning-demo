package com.example.demo.dao;

import com.example.demo.entity.Customer;
import com.example.demo.mock.MockRepository;
import org.springframework.data.repository.CrudRepository;


/**
 * @author 若鱼1919
 * @date 2022/5/7 0007 10:08
 */
@MockRepository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
