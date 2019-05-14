package com.github.xjs.spdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.xjs.spdemo.dao.Employee;
import com.github.xjs.spdemo.dao.EmployeeDao;
import com.github.xjs.spdemo.enums.Gender;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月14日 下午2:29:29<br/>
 */
@RestController
@RequestMapping(value = "/db")
public class DBController {
	@Autowired
	EmployeeDao employeeDao;
	
	@RequestMapping(value="/init", method=RequestMethod.GET)
	@ResponseBody
	public boolean initTable() {
		boolean exist = (employeeDao.isTableExist() > 0);
		if(!exist) {
			employeeDao.createTable();
		}else {
			employeeDao.dropTable();
			employeeDao.createTable();
		}
		return true;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	@ResponseBody
	public boolean insert() {
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("若鱼1919");
		emp.setGender(Gender.MALE);
		int ret = employeeDao.insert(emp);
		return ret > 0;
	}
	
	@RequestMapping(value="/query", method=RequestMethod.GET)
	@ResponseBody
	public List<Employee> query() {
		List<Employee> emps= employeeDao.listAll();
		return emps;
	}
}
