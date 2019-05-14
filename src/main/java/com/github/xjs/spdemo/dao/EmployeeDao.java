package com.github.xjs.spdemo.dao;

import java.util.List;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月14日 下午2:00:52<br/>
 */
public interface EmployeeDao {
	public int isTableExist();
	public void createTable();
	public void dropTable();
	public int insert(Employee emp);
	public List<Employee> listAll();
}
