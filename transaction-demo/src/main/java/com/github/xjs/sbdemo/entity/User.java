package com.github.xjs.sbdemo.entity;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月14日 下午2:01:55<br/>
 */
public class User {
	private int id;
	private String name;
	private Integer gender;

	public User() {
	}

	public User(int id, String name, Integer gender) {
		this.id = id;
		this.name = name;
		this.gender = gender;
	}

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

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
}
