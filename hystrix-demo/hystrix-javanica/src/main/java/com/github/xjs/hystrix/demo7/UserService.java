package com.github.xjs.hystrix.demo7;

import com.github.xjs.hystrix.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class UserService {
    @HystrixCollapser(batchMethod = "getUserByIds")
    public User getUserById(String id) {
        return null;
    }
    @HystrixCommand
    public List<User> getUserByIds(List<String> ids) {
        List<User> users = new ArrayList<User>();
        for (String id : ids) {
            users.add(new User(id, "name: " + id));
        }
        return users;
    }
}
