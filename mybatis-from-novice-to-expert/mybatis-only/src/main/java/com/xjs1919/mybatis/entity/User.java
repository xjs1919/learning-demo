package com.xjs1919.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 若鱼1919
 * @date 2022/01/10 19:50
 */
public class User implements Serializable{

    private int id;
    private String name;
    private int gender;
    private Date birth;
    private Integer version;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name, int gender) {
        this.name = name;
        this.gender = gender;
    }

    public User(int id, String name, int gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public User(int id, String name, int gender, Date birth) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
    }

    public User(int id, String name, int gender, int version) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.version = version;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", birth=" + birth +
                // ", version=" + version +
                '}';
    }
}
