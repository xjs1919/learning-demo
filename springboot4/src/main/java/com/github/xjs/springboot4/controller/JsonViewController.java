package com.github.xjs.springboot4.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/json-view/")
public class JsonViewController {

    public interface FrontUserData{}

    @Data
    public static class User{

        @JsonView(FrontUserData.class)
        private Integer id;

        @JsonView(FrontUserData.class)
        private String name;

        @JsonView(FrontUserData.class)
        private Integer age;

        private String password;
    }

    /**
     * $ curl localhost:8080/api/json-view/user
     * {"age":30,"id":1,"name":"zhangsan"}
     * */
    @GetMapping(path = "/user")
    @JsonView(FrontUserData.class)
    public User user() throws Exception{
        return getUser();
    }
    /**
     * $ curl localhost:8080/api/json-view/admin/user
     * {"age":30,"id":1,"name":"zhangsan","password":"123456"}
     * */
    @GetMapping(path = "/admin/user")
    public User adminUser() throws Exception{
        return getUser();
    }

    private User getUser() {
        User user = new User();
        user.setId(1);
        user.setName("zhangsan");
        user.setAge(30);
        user.setPassword("123456");
        return user;
    }

}
