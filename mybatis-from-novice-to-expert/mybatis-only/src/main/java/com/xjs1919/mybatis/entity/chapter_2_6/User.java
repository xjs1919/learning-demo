package com.xjs1919.mybatis.entity.chapter_2_6;

/**
 * @author jiashuai.xujs
 * @date 2022/3/7 10:15
 */
public class User {
    private Integer id;
    private String name;
    private Gender gender;
    private String userName;

    public User() {
    }

    public User(Integer id, String name, Gender gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public User(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", userName='" + userName + '\'' +
                '}';
    }
}
