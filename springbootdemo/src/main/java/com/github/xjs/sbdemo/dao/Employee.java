package com.github.xjs.sbdemo.dao;

import com.github.xjs.sbdemo.enums.Gender;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月14日 下午2:01:55<br/>
 */
public class Employee {
	private int id;
	private String name;
	private Gender gender;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
