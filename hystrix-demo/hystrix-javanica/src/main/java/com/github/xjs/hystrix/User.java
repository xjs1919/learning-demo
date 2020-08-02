package com.github.xjs.hystrix;

public class User {
    private String id;
    private String name;
    private Profile profile;

    public User() {
        System.out.println("construct new user()");
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("construct user: id:"+id+",name:"+name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
