package com.github.xjs.spdemo.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.xjs.spdemo.dao.Employee;
import com.github.xjs.spdemo.dao.EmployeeDao;
import com.github.xjs.spdemo.dto.DayDto;
import com.github.xjs.spdemo.enums.WeekDay;

import springfox.documentation.annotations.ApiIgnore;


/**
 * @author xujs@inspur.com
 *
 * @date 2017年9月14日 下午4:32:17
 */
@ApiIgnore
@RestController
@RequestMapping(value = "/demo")
public class DemoController{
	
	private static Logger log = LoggerFactory.getLogger(DemoController.class);
	
	@RequestMapping(value="/wd", method=RequestMethod.GET)
	@ResponseBody
	public WeekDay wd(WeekDay weekDay) throws Exception{
		log.info(weekDay.toString());
		return weekDay;
	}
	@RequestMapping(value="/dto1", method=RequestMethod.POST)
	 @ResponseBody
	public DayDto dto1(DayDto dto) throws Exception{
		log.info(dto.toString());
		return dto;
	}
	@RequestMapping(value="/dto2", method=RequestMethod.POST)
	 @ResponseBody
	public DayDto dto2(@RequestBody DayDto dto) throws Exception{
		log.info(dto.toString());
		return dto;
	}
	
	@Autowired
	EmployeeDao employeeDao;
	
	@RequestMapping(value="/db_init", method=RequestMethod.GET)
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
	
	@RequestMapping(value="/db_insert", method=RequestMethod.GET)
	@ResponseBody
	public boolean insert() {
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("若鱼1919");
		emp.setSalary(100);
		int ret = employeeDao.insert(emp);
		return ret > 0;
	}
	
	@RequestMapping(value="/db_query", method=RequestMethod.GET)
	@ResponseBody
	public List<Employee> query() {
		List<Employee> emps= employeeDao.listAll();
		return emps;
	}
	
	
	
}